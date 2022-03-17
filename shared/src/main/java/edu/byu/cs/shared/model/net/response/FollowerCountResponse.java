package edu.byu.cs.shared.model.net.response;

public class FollowerCountResponse {
    private boolean success;
    private String message;
    private int count;

    public FollowerCountResponse(boolean success, String message, int count) {
        this.success = success;
        this.message = message;
        this.count = count;
    }

    public FollowerCountResponse() {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
