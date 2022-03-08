package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.StatusService;

public class StoryPresenter extends PagedPresenter<Status>{
    private StatusService statusService = new StatusService();

    public StoryPresenter(View view) {
        super(view);
    }

    @Override
    void getItems(AuthToken authToken, User user, int pageSize, Status lastItem, GetItemObserver getItemObserver) {
        statusService.getUserStory(authToken, user, pageSize, lastItem, new GetItemObserver());
    }
}
