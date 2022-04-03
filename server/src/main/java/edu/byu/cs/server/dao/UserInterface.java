package edu.byu.cs.server.dao;

import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.request.LoginRequest;
import edu.byu.cs.shared.model.net.request.LogoutRequest;
import edu.byu.cs.shared.model.net.request.RegisterRequest;
import edu.byu.cs.shared.model.net.response.GetUserResponse;
import edu.byu.cs.shared.model.net.response.LoginResponse;
import edu.byu.cs.shared.model.net.response.LogoutResponse;
import edu.byu.cs.shared.model.net.response.RegisterResponse;

public interface UserInterface {
    LoginResponse login(LoginRequest request);
    LogoutResponse logout(LogoutRequest request);
    RegisterResponse register(RegisterRequest request);
    GetUserResponse getUser(GetUserRequest request);
}
