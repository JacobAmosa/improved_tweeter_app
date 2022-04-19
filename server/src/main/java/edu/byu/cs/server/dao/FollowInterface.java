package edu.byu.cs.server.dao;

import java.util.List;

import edu.byu.cs.shared.model.net.request.FollowRequest;
import edu.byu.cs.shared.model.net.request.FollowerCountRequest;
import edu.byu.cs.shared.model.net.request.FollowingCountRequest;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.IsFollowerRequest;
import edu.byu.cs.shared.model.net.request.UnfollowRequest;
import edu.byu.cs.shared.model.net.response.FollowResponse;
import edu.byu.cs.shared.model.net.response.FollowerCountResponse;
import edu.byu.cs.shared.model.net.response.FollowingCountResponse;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.IsFollowerResponse;
import edu.byu.cs.shared.model.net.response.UnfollowResponse;

public interface FollowInterface {
    FollowingResponse getFollowing(FollowingRequest request);
    FollowingResponse getFollowers(FollowingRequest request);
    FollowResponse follow(FollowRequest request);
    UnfollowResponse unfollow(UnfollowRequest request);
    FollowingCountResponse getFollowingCount(FollowingCountRequest request);
    FollowerCountResponse getFollowerCount(FollowerCountRequest request);
    IsFollowerResponse isFollower(IsFollowerRequest request);
    List<String> getFollowerAliases(String alias);
    void addFollowersBatch(List<String> followers, String followTarget);
}
