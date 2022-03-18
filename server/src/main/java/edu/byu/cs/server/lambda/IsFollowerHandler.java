package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.service.FollowService;
import edu.byu.cs.shared.model.net.request.IsFollowerRequest;
import edu.byu.cs.shared.model.net.response.IsFollowerResponse;

public class IsFollowerHandler implements RequestHandler<IsFollowerRequest, IsFollowerResponse> {
    @Override
    public IsFollowerResponse handleRequest(IsFollowerRequest input, Context context) {
        FollowService service = new FollowService();
        return service.isFollower(input);
    }
}
