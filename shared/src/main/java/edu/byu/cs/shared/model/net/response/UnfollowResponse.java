package edu.byu.cs.shared.model.net.response;

public class UnfollowResponse {
    private boolean success;
    private String message;

    public UnfollowResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UnfollowResponse() {
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
