package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.service.StatusService;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.response.FeedResponse;

public class GetFeedHandler implements RequestHandler<FeedRequest, FeedResponse> {
    @Override
    public FeedResponse handleRequest(FeedRequest input, Context context) {
        StatusService service = new StatusService();
        return service.getFeed(input);
    }
}
