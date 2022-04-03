package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.service.UserService;
import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.response.GetUserResponse;

public class GetUserHandler implements RequestHandler<GetUserRequest, GetUserResponse> {
    @Override
    public GetUserResponse handleRequest(GetUserRequest input, Context context) {
        UserService service = new UserService(new DaoProvider());
        return service.getUser(input);
    }
}
