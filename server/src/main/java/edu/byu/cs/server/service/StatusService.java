package edu.byu.cs.server.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.dao.FactoryInterface;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.FeedResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;

public class StatusService {
    private final FactoryInterface dao;
    private Long tenHourValidation = Long.valueOf(14400000);
    private String POST_STATUS_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/302126991095/postStatusQueue";
    private String UPDATE_FEED_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/302126991095/updateFeedQueue";
    private int BATCH_SIZE = 25;
    private int FOLLOWER_PAGE_SIZE = 500;


    public StatusService(FactoryInterface dao) {
        this.dao = dao;
    }

    public CreateStatusResponse createStatus(CreateStatusRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request is missing an alias");
        } else if(request.getNewStatus().length() == 0) {
            throw new RuntimeException("[BadRequest] Request needs to have one or more characters.");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new CreateStatusResponse(false, "Your session has timed out.");
//        }
//        List<String> aliases = new DaoProvider().getFollowDAO().getFollowerAliases(request.getUser().getAlias());
        Long date = Calendar.getInstance().getTimeInMillis();
        Status status = new Status(request.getNewStatus(), request.getUser(), request.getAuthToken().datetime);

        addStatusToFeedQueue(status);

//        return  new CreateStatusResponse(true, null);
        return dao.getStatusDAO().createStatus(request, String.valueOf(date));
    }

    private void addStatusToFeedQueue(Status status) {
        addObjectToQueue(POST_STATUS_QUEUE_URL, new StatusMessage(status));
    }

    private void addObjectToQueue(String queueUrl, Object object) {
        Gson gson = new Gson();
        String messageBody = gson.toJson(object);

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody);

        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);

        String id = sendMessageResult.getMessageId();
        System.out.println("Message Id: " + id);
    }

    public class StatusMessage{
        Status status;

        public StatusMessage() {
        }

        public StatusMessage(Status status) {
            this.status = status;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }

    public void processStatusBatch(String requestBody){
        Gson gson = new Gson();
        StatusBatchMessage statusBatchMessage = gson.fromJson(requestBody, StatusBatchMessage.class);
        dao.getStatusDAO().addBatchToFeed(statusBatchMessage.getFollowerAlias(), statusBatchMessage);
    }

    private void addBatchToBatchQueue(Status status, List<String> followerAliases){
        addObjectToQueue(UPDATE_FEED_QUEUE_URL, new StatusBatchMessage(status, followerAliases));
    }

    public class StatusBatchMessage {
        public Status status;
        public List<String> followerAlias;

        public StatusBatchMessage() {
        }

        public StatusBatchMessage(Status status, List<String> followerAlias) {
            this.status = status;
            this.followerAlias = followerAlias;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public List<String> getFollowerAlias() {
            return followerAlias;
        }

        public void setFollowerAlias(List<String> followerAlias) {
            this.followerAlias = followerAlias;
        }
    }

    public FeedResponse getFeed(FeedRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new FeedResponse("Your session has timed out.");
//        }
        return dao.getStatusDAO().getFeed(request);
    }

    public StoryResponse getStory(StoryRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new StoryResponse("Your session has timed out.");
//        }
        return dao.getStatusDAO().getStory(request);
    }

    public void createStatusBatches(String requestBody) {
        Gson gson = new Gson();
        StatusMessage statusMessage = gson.fromJson(requestBody, StatusMessage.class);
        List<String> followerAliases = dao.getFollowDAO().getFollowerAliases(statusMessage.status.getUser().getAlias());
        ArrayList<String> batchAliases = new ArrayList<>();
        for (int i = 0; i < followerAliases.size(); i++) {
            batchAliases.add(followerAliases.get(i));
            if (batchAliases.size() >= BATCH_SIZE || i == (followerAliases.size() - 1)) {
                addBatchToBatchQueue(statusMessage.status, batchAliases);
                batchAliases.clear();
            }
        }
    }

}
