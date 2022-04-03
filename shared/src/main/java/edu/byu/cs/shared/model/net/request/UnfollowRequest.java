package edu.byu.cs.shared.model.net.request;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;

public class UnfollowRequest {
    private AuthToken authToken;
    private User followee;
    private User follower;

    public UnfollowRequest(AuthToken authToken, User followee, User follower) {
        this.authToken = authToken;
        this.followee = followee;
        this.follower = follower;
    }

    public UnfollowRequest() {
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public User getFollowee() {
        return followee;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
