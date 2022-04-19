package edu.byu.cs.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.byu.cs.server.service.StatusService;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.FeedResponse;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.GetUserResponse;
import edu.byu.cs.shared.model.net.response.RegisterResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;
import edu.byu.cs.shared.util.FakeData;


public class StatusDAO implements StatusInterface{

    private Table storyTable;
    private Table feedTable;

    public void connectToTable(String table) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-west-2")
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);
        if (table.equals("story")) {
            storyTable = dynamoDB.getTable("story");
        }
        else if (table.equals("feed")) {
            feedTable = dynamoDB.getTable("feed");
        }
        else if (table.equals("createStatus")) {
            feedTable = dynamoDB.getTable("feed");
            storyTable = dynamoDB.getTable("story");
        }
    }

    @Override
    public CreateStatusResponse createStatus(CreateStatusRequest request, String date) {
        connectToTable("createStatus");

//        for (String alias: followers) {
//            Item item = new Item()
//                    .withPrimaryKey("receiver_alias", alias)
//                    .withString("time_stamp", date)
//                    .withString("message", request.getNewStatus())
//                    .withString("creator_alias", request.getUser().getAlias());
//            feedTable.putItem(item);
//        }

        Item item = new Item()
                .withPrimaryKey("sender_alias", request.getUser().getAlias())
                .withString("time_stamp", date)
                .withString("message", request.getNewStatus());
        storyTable.putItem(item);

        return new CreateStatusResponse(true, null);
    }

    @Override
    public FeedResponse getFeed(FeedRequest request) {
        connectToTable("feed");
        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#receiver", "receiver_alias");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":receiver_alias", request.getUser());

        List<Status> feed = new ArrayList<>();
        boolean hasMorePages = false;

        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#receiver = :receiver_alias").withNameMap(nameMap)
                .withValueMap(valueMap).withScanIndexForward(true);

            ItemCollection<QueryOutcome> items = null;
            Iterator<Item> iterator = null;
            Item item = null;
            try {
                items = feedTable.query(querySpec);
//                last = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
                iterator = items.iterator();
                while (iterator.hasNext()) {
                    item = iterator.next();
                    // quick fix on getFeed returning null getMentions and getUrls.
                    List<String> urls = new ArrayList<>();
                    DateFormat obj = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
                    Date prettyTime = new Date(Long.parseLong(item.get("time_stamp").toString()));
                    GetUserResponse creator = new DaoProvider().getUserDAO().getUser(new GetUserRequest(item.get("creator_alias").toString()));
                    Status status = new Status(item.get("message").toString(), creator.getUser(), obj.format(prettyTime),
                            urls, urls);
                    feed.add(status);
                    status = null;
                }
            } catch (Exception e) {
                System.err.println("Unable to query feed table.");
                System.err.println(e.getMessage());
            }

        return new FeedResponse(feed, hasMorePages);
    }

    public StoryResponse getStory(StoryRequest request) {
        connectToTable("story");
        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#sender", "sender_alias");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":sender_alias", request.getUser());
        List<Status> story = new ArrayList<>();
        boolean hasMorePages = false;

        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#sender = :sender_alias").withNameMap(nameMap)
                .withValueMap(valueMap).withScanIndexForward(true);

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;
        GetUserResponse creator = new DaoProvider().getUserDAO().getUser(new GetUserRequest(request.getUser()));
        try {
            items = storyTable.query(querySpec);
            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                List<String> urls = new ArrayList<>();
                DateFormat obj = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
                Date prettyTime = new Date(Long.parseLong(item.get("time_stamp").toString()));
                Status status = new Status(item.get("message").toString(), creator.getUser(), obj.format(prettyTime),
                        urls, urls);
                story.add(status);
                status = null;
            }
        }
        catch (Exception e) {
            System.err.println("Unable to query story table.");
            System.err.println(e.getMessage());
        }

        return new StoryResponse(story, hasMorePages);
    }

    @Override
    public void addBatchToFeed(List<String> followerAlias, StatusService.StatusBatchMessage statusBatchMessage) {
        TableWriteItems items = new TableWriteItems("feed");

        for (String alias: followerAlias) {
            Item item = new Item()
                    .withPrimaryKey("receiver_alias", alias)
                    .withString("time_stamp", statusBatchMessage.getStatus().getDatetime())
                    .withString("message", statusBatchMessage.getStatus().getPost())
                    .withString("creator_alias", statusBatchMessage.getStatus().getUser().alias);
            items.addItemToPut(item);
            if (items.getItemsToPut() != null && items.getItemsToPut().size() == 25) {
                loopBatchWrite(items);
                items = new TableWriteItems("feed");
            }
        }

        if (items.getItemsToPut() != null && items.getItemsToPut().size() > 0) {
            loopBatchWrite(items);
        }
    }

    private void loopBatchWrite(TableWriteItems items) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-west-2")
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        // The 'dynamoDB' object is of type DynamoDB and is declared statically in this example
        BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(items);
        //logger.log("Wrote User Batch");

        // Check the outcome for items that didn't make it onto the table
        // If any were not added to the table, try again to write the batch
        while (outcome.getUnprocessedItems().size() > 0) {
            Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
            outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
        //logger.log("Wrote more Users");
        }
    }


}
