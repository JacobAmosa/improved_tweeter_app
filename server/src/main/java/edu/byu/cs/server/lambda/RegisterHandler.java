package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.service.UserService;
import edu.byu.cs.shared.model.net.request.RegisterRequest;
import edu.byu.cs.shared.model.net.response.RegisterResponse;

public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {
    @Override
    public RegisterResponse handleRequest(RegisterRequest input, Context context) {
        UserService userService = new UserService(new DaoProvider());
        return userService.register(input);
    }
}
