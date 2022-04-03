package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.service.FollowService;
import edu.byu.cs.shared.model.net.request.UnfollowRequest;
import edu.byu.cs.shared.model.net.response.UnfollowResponse;

public class UnfollowHandler implements RequestHandler<UnfollowRequest, UnfollowResponse> {
    @Override
    public UnfollowResponse handleRequest(UnfollowRequest input, Context context) {
        FollowService userService = new FollowService(new DaoProvider());
        return userService.unfollow(input);
    }
}
