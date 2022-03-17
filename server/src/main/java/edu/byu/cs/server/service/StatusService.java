package edu.byu.cs.server.service;

import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;

public class StatusService {

    public CreateStatusResponse createStatus(CreateStatusRequest request) {
        //add possible conditions that would throw an exception.
        return new CreateStatusResponse(true, null);
    }

}
