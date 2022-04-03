package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;

public class CreateStatusRequest {

    private AuthToken authToken;
    private String newStatus;
    private User user;

    public CreateStatusRequest(AuthToken authToken, String newStatus, User user) {
        this.authToken = authToken;
        this.newStatus = newStatus;
        this.user = user;
    }

    public CreateStatusRequest () {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
