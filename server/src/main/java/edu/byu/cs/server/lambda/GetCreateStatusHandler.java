package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.service.StatusService;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;

public class GetCreateStatusHandler implements RequestHandler<CreateStatusRequest, CreateStatusResponse>{
    @Override
    public CreateStatusResponse handleRequest(CreateStatusRequest input, Context context) {
        StatusService service = new StatusService();
        return service.createStatus(input);
    }
}
