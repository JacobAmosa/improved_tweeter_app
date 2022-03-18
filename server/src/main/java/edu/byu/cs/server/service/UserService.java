package edu.byu.cs.server.service;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.request.LoginRequest;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.GetUserResponse;
import edu.byu.cs.shared.model.net.response.LoginResponse;
import edu.byu.cs.shared.util.FakeData;

public class UserService {

    public LoginResponse login(LoginRequest request) {
        if(request.getUsername() == null){
            throw new RuntimeException("[BadRequest] Missing a username");
        } else if(request.getPassword() == null) {
            throw new RuntimeException("[BadRequest] Missing a password");
        }

        // TODO: Generates dummy data. Replace with a real implementation.
        User user = getDummyUser();
        AuthToken authToken = getDummyAuthToken();
        return new LoginResponse(user, authToken);
    }

    public GetUserResponse getUser(GetUserRequest request) {
        if(request.getUserAlias() == null) {
            throw new RuntimeException("[BadRequest] Request needs to have a follower alias");
        }
        return new GetUserResponse(getFakeData().findUserByAlias(request.getUserAlias()));
    }

    /**
     * Returns the dummy user to be returned by the login operation.
     * This is written as a separate method to allow mocking of the dummy user.
     *
     * @return a dummy user.
     */
    User getDummyUser() {
        return getFakeData().getFirstUser();
    }

    /**
     * Returns the dummy auth token to be returned by the login operation.
     * This is written as a separate method to allow mocking of the dummy auth token.
     *
     * @return a dummy auth token.
     */
    AuthToken getDummyAuthToken() {
        return getFakeData().getAuthToken();
    }

    /**
     * Returns the {@link FakeData} object used to generate dummy users and auth tokens.
     * This is written as a separate method to allow mocking of the {@link FakeData}.
     *
     * @return a {@link FakeData} instance.
     */
    FakeData getFakeData() {
        return new FakeData();
    }
}
