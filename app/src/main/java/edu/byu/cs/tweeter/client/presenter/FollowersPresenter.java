package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.FollowService;

public class FollowersPresenter extends PagedPresenter<User>{
    private FollowService followService = new FollowService();

    public FollowersPresenter(View view) {
        super(view);
    }

    @Override
    void getItems(AuthToken authToken, User user, int pageSize, User lastItem, GetItemObserver getItemObserver) {
        followService.getFollowers(authToken, user, pageSize, lastItem, new GetItemObserver());
    }
}

