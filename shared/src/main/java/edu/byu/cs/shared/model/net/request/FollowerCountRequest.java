package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class FollowerCountRequest {
    private AuthToken authToken;
    private String user;

    public FollowerCountRequest(AuthToken authToken, String user) {
        this.authToken = authToken;
        this.user = user;
    }

    public FollowerCountRequest() {
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
