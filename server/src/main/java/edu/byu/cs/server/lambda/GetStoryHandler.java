package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.service.StatusService;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.StoryResponse;

public class GetStoryHandler implements RequestHandler<StoryRequest, StoryResponse> {
    @Override
    public StoryResponse handleRequest(StoryRequest input, Context context) {
        StatusService service = new StatusService();
        return service.getStory(input);
    }
}
