package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;

public class CreateStatusRequest {

    private AuthToken authToken;
    private String newStatus;

    public CreateStatusRequest(AuthToken authToken, String newStatus) {
        this.authToken = authToken;
        this.newStatus = newStatus;
    }

    public CreateStatusRequest () {
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
