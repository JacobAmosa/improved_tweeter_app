package edu.byu.cs.shared.model.net.response;

public class IsFollowerResponse extends Response{
    private Boolean isFollower;

    public IsFollowerResponse(String message) {
        super(false, message);
    }

    public IsFollowerResponse(Boolean value) {
        super(true, null);
        this.isFollower = value;
    }

    public Boolean getIsFollower() {
        return isFollower;
    }
}
