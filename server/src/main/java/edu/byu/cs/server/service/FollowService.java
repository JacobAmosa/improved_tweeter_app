package edu.byu.cs.server.service;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.dao.FactoryInterface;
import edu.byu.cs.server.dao.FollowDAO;
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

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowService {
    private Long tenHourValidation = Long.valueOf(14400000);
    private FactoryInterface dao;

    public FollowService(DaoProvider dao) {
        this.dao = dao;
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link FollowDAO} to
     * get the followees.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowingResponse getFollowing(FollowingRequest request) {
        if(request.getFollowerAlias() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a follower alias");
        } else if(request.getLimit() <= 0) {
            throw new RuntimeException("[BadRequest] Request needs to have a positive limit");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new FollowingResponse("Your session has timed out.");
//        }
        return dao.getFollowDAO().getFollowing(request);
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
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new FollowingResponse("Your session has timed out.");
//        }
        return dao.getFollowDAO().getFollowers(request);
    }

    public FollowResponse follow(FollowRequest request) {
        if(request.getFollowee() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a followee alias");
        } else if(new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a valid auth token");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new FollowResponse(false, "Your session has timed out.");
//        }
        return dao.getFollowDAO().follow(request);
    }

    public UnfollowResponse unfollow(UnfollowRequest request) {
        if(request.getFollowee() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a followee alias");
        }
//        else if(new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] Request needs to have a valid auth token");
//        } else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new UnfollowResponse(false, "Your session has timed out.");
//        }
        return dao.getFollowDAO().unfollow(request);
    }

    public FollowingCountResponse getFollowingCount(FollowingCountRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new FollowingCountResponse(false, "Your session has timed out.", 0);
//        }
        return dao.getFollowDAO().getFollowingCount(request);
    }

    public FollowerCountResponse getFollowerCount(FollowerCountRequest request) {
        if(request.getUser() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a user alias");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new FollowerCountResponse(false, "Your session has timed out.", 0);
//        }
        return dao.getFollowDAO().getFollowerCount(request);
    }

    public IsFollowerResponse isFollower(IsFollowerRequest request) {
        if(request.getFollower() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a follower alias");
        } else if(request.getFollowee() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a followee alias");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new IsFollowerResponse("Your session has timed out");
//        }
        return dao.getFollowDAO().isFollower(request);
    }

}
