package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class GetUserRequest {
    private AuthToken authToken;
    private String userAlias;

    public GetUserRequest(AuthToken authToken, String userAlias) {
        this.authToken = authToken;
        this.userAlias = userAlias;
    }

    public GetUserRequest() {
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
