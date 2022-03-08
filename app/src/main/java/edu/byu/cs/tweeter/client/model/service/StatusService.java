package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetStoryTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.PostStatusTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.PagedNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.SimpleNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.UserNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.UserNotificationObserver;

public class StatusService {

    //    *****************************   MainActivity createStatus ***************************************

    public void createStatusTask(AuthToken currUserAuthToken, Status newStatus, SimpleNotificationObserver getCreateStatusObserver) {
        PostStatusTask statusTask = new PostStatusTask(currUserAuthToken,
                newStatus, new SimpleNotificationHandler(getCreateStatusObserver));
        new Service(statusTask);
    }

    //******************************** FeedPresenter getUserStatus **************************************************

    public void getStatusUser(AuthToken currUserAuthToken, String userAlias, UserNotificationObserver getStatusUserObserver) {
        GetUserTask getUserTask = new GetUserTask(currUserAuthToken,
                userAlias, new UserNotificationHandler(getStatusUserObserver));
        new Service(getUserTask);
    }


    //******************************** Feed Presenter *******************************************************

    public void getFeed(AuthToken currUserAuthToken, User user, int pageSize, Status lastStatus, PagedNotificationObserver getFeedObserver) {
        GetFeedTask getFeedTask = new GetFeedTask(currUserAuthToken,
                user, pageSize, lastStatus, new PagedNotificationHandler<Status>(getFeedObserver));
        new Service(getFeedTask);
    }

    //******************************** StoryPresenter **************************************************

    public void getUserStory(AuthToken currUserAuthToken, User user, int pageSize, Status lastStatus, PagedNotificationObserver getStoryObserver) {
        GetStoryTask getStoryTask = new GetStoryTask(currUserAuthToken,
                user, pageSize, lastStatus, new PagedNotificationHandler<Status>(getStoryObserver));
        new Service(getStoryTask);
    }


}
