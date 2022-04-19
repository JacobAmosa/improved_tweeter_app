package edu.byu.cs.server.dao;

import java.util.List;

import edu.byu.cs.server.service.StatusService;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.FeedResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;

public interface StatusInterface {
    CreateStatusResponse createStatus(CreateStatusRequest request, String date);
    FeedResponse getFeed(FeedRequest request);
    StoryResponse getStory(StoryRequest request);
    void addBatchToFeed(List<String> followerAlias, StatusService.StatusBatchMessage statusBatchMessage);
}
