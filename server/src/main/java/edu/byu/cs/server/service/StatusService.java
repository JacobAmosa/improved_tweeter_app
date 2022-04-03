package edu.byu.cs.server.service;

import java.util.Calendar;
import java.util.List;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.dao.FactoryInterface;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.FeedResponse;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;

public class StatusService {
    private FactoryInterface dao;
    private Long tenHourValidation = Long.valueOf(14400000);

    public StatusService(FactoryInterface dao) {
        this.dao = dao;
    }

    public CreateStatusResponse createStatus(CreateStatusRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request is missing an alias");
        } else if(request.getNewStatus().length() == 0) {
            throw new RuntimeException("[BadRequest] Request needs to have one or more characters.");
        } else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
            throw new RuntimeException("[BadRequest] invalid authToken");
        }
        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
        if (validToken < 0) {
            return new CreateStatusResponse(false, "Your session has timed out.");
        }
        List<String> aliases = new DaoProvider().getFollowDAO().getFollowerAliases(request.getUser().getAlias());

        Long date = Calendar.getInstance().getTimeInMillis();

        return new DaoProvider().getStatusDAO().createStatus(request, aliases, String.valueOf(date));
    }

    public FeedResponse getFeed(FeedRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        } else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
            throw new RuntimeException("[BadRequest] invalid authToken");
        }
        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
        if (validToken < 0) {
            return new FeedResponse("Your session has timed out.");
        }
        return new DaoProvider().getStatusDAO().getFeed(request);
    }

    public StoryResponse getStory(StoryRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        } else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
            throw new RuntimeException("[BadRequest] invalid authToken");
        }
        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
        if (validToken < 0) {
            return new StoryResponse("Your session has timed out.");
        }
        return new DaoProvider().getStatusDAO().getStory(request);
    }

}
