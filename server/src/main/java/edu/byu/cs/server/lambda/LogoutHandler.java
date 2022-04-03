package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.service.UserService;
import edu.byu.cs.shared.model.net.request.LogoutRequest;
import edu.byu.cs.shared.model.net.response.LogoutResponse;

public class LogoutHandler implements RequestHandler<LogoutRequest, LogoutResponse> {
    @Override
    public LogoutResponse handleRequest(LogoutRequest input, Context context) {
        UserService userService = new UserService(new DaoProvider());
        return userService.logout(input);
    }
}
