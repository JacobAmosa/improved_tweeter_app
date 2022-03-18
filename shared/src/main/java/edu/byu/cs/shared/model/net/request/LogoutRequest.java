package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class LogoutRequest {
    private AuthToken authToken;

    public LogoutRequest(AuthToken authToken) {
        this.authToken = authToken;
    }

    public LogoutRequest() {
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
