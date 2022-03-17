package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.service.FollowService;
import edu.byu.cs.shared.model.net.request.FollowerCountRequest;
import edu.byu.cs.shared.model.net.response.FollowerCountResponse;

public class GetFollowerCountHandler implements RequestHandler<FollowerCountRequest, FollowerCountResponse> {
    @Override
    public FollowerCountResponse handleRequest(FollowerCountRequest input, Context context) {
        FollowService service = new FollowService();
        return service.getFollowerCount(input);
    }
}
