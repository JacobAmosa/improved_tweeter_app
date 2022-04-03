package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.service.FollowService;
import edu.byu.cs.shared.model.net.request.FollowRequest;
import edu.byu.cs.shared.model.net.response.FollowResponse;

public class FollowHandler implements RequestHandler<FollowRequest, FollowResponse> {
    @Override
    public FollowResponse handleRequest(FollowRequest input, Context context) {
        FollowService followService = new FollowService(new DaoProvider());
        return followService.follow(input);
    }
}
