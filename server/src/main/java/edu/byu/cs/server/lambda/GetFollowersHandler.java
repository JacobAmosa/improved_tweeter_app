package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.service.FollowService;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.response.FollowingResponse;

public class GetFollowersHandler implements RequestHandler<FollowingRequest, FollowingResponse> {

    @Override
    public FollowingResponse handleRequest(FollowingRequest input, com.amazonaws.services.lambda.runtime.Context context) {
        FollowService service = new FollowService();
        return service.getFollowees(input);
    }
}
