package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.service.FollowService;
import edu.byu.cs.shared.model.net.request.FollowingCountRequest;
import edu.byu.cs.shared.model.net.response.FollowingCountResponse;

public class GetFollowingCountHandler  implements RequestHandler<FollowingCountRequest, FollowingCountResponse> {
    @Override
    public FollowingCountResponse handleRequest(FollowingCountRequest input, Context context) {
        FollowService service = new FollowService(new DaoProvider());
        return service.getFollowingCount(input);
    }
}
