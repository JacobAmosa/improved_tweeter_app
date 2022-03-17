package edu.byu.cs.shared.model.net.response;

public class FollowResponse {
    private boolean success;
    private String message;

    public FollowResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public FollowResponse() {
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
