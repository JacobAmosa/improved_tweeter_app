package edu.byu.cs.shared.model.net.response;

import edu.byu.cs.shared.model.domain.User;

public class LogoutResponse {
    private boolean success;
    private String message;
    private User user;

    public LogoutResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LogoutResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public LogoutResponse() {
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
