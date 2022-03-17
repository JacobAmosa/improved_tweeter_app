package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class FollowingCountRequest {
    private AuthToken authToken;
    private String user;

    public FollowingCountRequest(AuthToken authToken, String user) {
        this.authToken = authToken;
        this.user = user;
    }

    public FollowingCountRequest() {
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
