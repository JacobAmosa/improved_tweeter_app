package edu.byu.cs.server.service;

import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.FeedResponse;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;
import edu.byu.cs.shared.util.FakeData;

public class StatusService {

    public CreateStatusResponse createStatus(CreateStatusRequest request) {
        //add possible conditions that would throw an exception.
        return new CreateStatusResponse(true, null);
    }

    public FeedResponse getFeed(FeedRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        }
        return new FeedResponse(new FakeData().getFakeStatuses(), true);
    }

    public StoryResponse getStory(StoryRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        }
        return new StoryResponse(new FakeData().getFakeStatuses(), true);
    }

}
