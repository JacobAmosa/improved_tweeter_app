package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.service.StatusService;

public class UpdateFeedsHandler implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        StatusService statusService = new StatusService(new DaoProvider());
        for(int i = 0; i < event.getRecords().size(); i++) {
            statusService.processStatusBatch(event.getRecords().get(i).getBody());
        }
        return null;
    }
}
