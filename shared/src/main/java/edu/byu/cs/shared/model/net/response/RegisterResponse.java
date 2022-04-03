package edu.byu.cs.shared.model.net.response;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;

public class RegisterResponse {
    private boolean success;
    private String message;
    private User user;
    private AuthToken authToken;

    public RegisterResponse(boolean success, String message, User user, AuthToken token) {
        this.success = success;
        this.message = message;
        this.user = user;
        this.authToken = token;
    }

    public RegisterResponse() {
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
