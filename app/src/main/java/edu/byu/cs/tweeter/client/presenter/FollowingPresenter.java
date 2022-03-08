package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.FollowService;

public class FollowingPresenter extends PagedPresenter<User>{

    private final FollowService followService = new FollowService();

    public FollowingPresenter(View view) {
        super(view);
    }

    @Override
    void getItems(AuthToken authToken, User user, int pageSize, User lastItem, GetItemObserver getItemObserver) {
        followService.getFollowing(authToken, user, pageSize, lastItem, new GetItemObserver());
    }

}
