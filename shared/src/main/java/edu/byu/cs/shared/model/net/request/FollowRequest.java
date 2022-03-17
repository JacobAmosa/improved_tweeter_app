package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class FollowRequest {
    private AuthToken authToken;
    private String followee;

    public FollowRequest(AuthToken authToken, String followee) {
        this.authToken = authToken;
        this.followee = followee;
    }

    public FollowRequest() {
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getFollowee() {
        return followee;
    }

    public void setFollowee(String followee) {
        this.followee = followee;
    }
}
