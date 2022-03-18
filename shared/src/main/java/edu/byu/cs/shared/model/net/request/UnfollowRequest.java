package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class UnfollowRequest {
    private AuthToken authToken;
    private String followee;

    public UnfollowRequest(AuthToken authToken, String followee) {
        this.authToken = authToken;
        this.followee = followee;
    }

    public UnfollowRequest() {
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
