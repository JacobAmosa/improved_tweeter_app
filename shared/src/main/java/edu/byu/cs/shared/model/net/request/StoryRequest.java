package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class StoryRequest {
    private AuthToken authToken;
    private String user;
    private int limit;
    private String lastStatus;

    public StoryRequest(AuthToken authToken, String user, int limit, String lastStatus) {
        this.authToken = authToken;
        this.user = user;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }

    public StoryRequest() {
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }
}
