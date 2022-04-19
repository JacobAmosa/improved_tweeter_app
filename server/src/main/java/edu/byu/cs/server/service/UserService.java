package edu.byu.cs.server.service;

import java.util.Calendar;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.dao.FactoryInterface;
import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.request.LoginRequest;
import edu.byu.cs.shared.model.net.request.LogoutRequest;
import edu.byu.cs.shared.model.net.request.RegisterRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.GetUserResponse;
import edu.byu.cs.shared.model.net.response.LoginResponse;
import edu.byu.cs.shared.model.net.response.LogoutResponse;
import edu.byu.cs.shared.model.net.response.RegisterResponse;

public class UserService {
    private Long tenHourValidation = Long.valueOf(14400000);
    private FactoryInterface dao;

    public UserService(FactoryInterface dao) {
        this.dao = dao;
    }

    public LoginResponse login(LoginRequest request) {
        if(request.getUsername() == null){
            throw new RuntimeException("[BadRequest] Missing a username");
        } else if(request.getPassword() == null) {
            throw new RuntimeException("[BadRequest] Missing a password");
        }
        LoginResponse loginResponse = dao.getUserDAO().login(request);

        AuthToken token = new AuthToken();
        if (loginResponse.isSuccess()) {
            dao.getAuthDAO().addToken(loginResponse.getAuthToken().getToken(),
                    loginResponse.getAuthToken().getDatetime());
        }
        return loginResponse;
    }

    public LogoutResponse logout(LogoutRequest request) {
        if(request.getAuthToken() == null){
            throw new RuntimeException("[BadRequest] Missing authToken");
        }
//        else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new LogoutResponse(false, "Your session has timed out.");
//        }
        return dao.getUserDAO().logout(request);
    }

    public RegisterResponse register(RegisterRequest request) {
        if(request.getFirstName() == null){
            throw new RuntimeException("[BadRequest] Missing a first name");
        } else if(request.getLastName() == null) {
            throw new RuntimeException("[BadRequest] Missing a last name");
        } else if(request.getUsername() == null) {
            throw new RuntimeException("[BadRequest] Missing a username");
        } else if(request.getPassword() == null) {
            throw new RuntimeException("[BadRequest] Missing a password");
        } else if(request.getImage() == null) {
            throw new RuntimeException("[BadRequest] Missing an image");
        }
//        String url = dao.getS3ImageDAO().uploadImage(request);
//        request.setImage(url);
        RegisterResponse registerResponse = new DaoProvider().getUserDAO().register(request);
        dao.getAuthDAO().addToken(registerResponse.getAuthToken().getToken(), registerResponse.getAuthToken().getDatetime());
        return registerResponse;
    }

    public GetUserResponse getUser(GetUserRequest request) {
        if(request.getUserAlias() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a follower alias");
        }
//        else if(request.getAuthToken() == null) {
//            throw new RuntimeException("[BadRequest] Missing an authToken");
//        } else if (new DaoProvider().getAuthDAO().validateToken(request.getAuthToken().getToken()) == null) {
//            throw new RuntimeException("[BadRequest] invalid authToken");
//        }
//        Long validToken = Long.parseLong(request.getAuthToken().getDatetime()) - tenHourValidation;
//        if (validToken < 0) {
//            return new GetUserResponse("Your session has timed out.");
//        }
        return dao.getUserDAO().getUser(request);
    }

}
