package edu.byu.cs.shared.model.net.response;

import edu.byu.cs.shared.model.domain.User;

public class GetUserResponse extends Response{
    private User user;

    public GetUserResponse(String message) {
        super(false, message);
    }

    public GetUserResponse(User user) {
        super(true, null);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
