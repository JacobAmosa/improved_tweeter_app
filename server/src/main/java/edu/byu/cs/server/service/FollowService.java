package edu.byu.cs.server.service;

import java.util.Random;

import edu.byu.cs.server.dao.FollowDAO;
import edu.byu.cs.shared.model.net.request.FollowRequest;
import edu.byu.cs.shared.model.net.request.FollowerCountRequest;
import edu.byu.cs.shared.model.net.request.FollowingCountRequest;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.IsFollowerRequest;
import edu.byu.cs.shared.model.net.response.FollowResponse;
import edu.byu.cs.shared.model.net.response.FollowerCountResponse;
import edu.byu.cs.shared.model.net.response.FollowingCountResponse;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.IsFollowerResponse;

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowService {

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link FollowDAO} to
     * get the followees.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowingResponse getFollowees(FollowingRequest request) {
        if(request.getFollowerAlias() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a follower alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        }
        return getFollowingDAO().getFollowees(request);
    }

    /**
     * Returns the users that are following the specified user. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. Uses the {@link FollowDAO} to
     * get the followers.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followers.
     */
    public FollowingResponse getFollowers(FollowingRequest request) {
        if(request.getFollowerAlias() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a follower alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        }
        return getFollowingDAO().getFollowees(request);
    }

    public FollowResponse follow(FollowRequest request) {
        return new FollowResponse(true, null);
    }

    public FollowingCountResponse getFollowingCount(FollowingCountRequest request) {
        return new FollowingCountResponse(true, null, 20);
    }

    public FollowerCountResponse getFollowerCount(FollowerCountRequest request) {
        return new FollowerCountResponse(true, null, 20);
    }

    public IsFollowerResponse isFollower(IsFollowerRequest request) {
        if(request.getFollower() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a follower alias");
        } else if(request.getFollowee() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a followee alias");
        }
        return new IsFollowerResponse(new Random().nextInt() > 0);
    }

    /**
     * Returns an instance of {@link FollowDAO}. Allows mocking of the FollowDAO class
     * for testing purposes. All usages of FollowDAO should get their FollowDAO
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public FollowDAO getFollowingDAO() {
        return new FollowDAO();
    }
}
