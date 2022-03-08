package edu.byu.cs.tweeter.client.model.service;

import java.util.concurrent.ExecutorService;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.FollowTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowersCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowersTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowingCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowingTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.IsFollowerTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.UnfollowTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.BooleanNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.CountNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.PagedNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.SimpleNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.BooleanNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.CountNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleNotificationObserver;

public class FollowService {

    //    *****************************   FollowingPresenter ***************************************

    public void getFollowing(AuthToken currUserAuthToken, User user, int pageSize, User lastFollowee, PagedNotificationObserver getFollowingObserver) {
        GetFollowingTask getFollowingTask = new GetFollowingTask(currUserAuthToken,
                user, pageSize, lastFollowee, new PagedNotificationHandler<User>(getFollowingObserver));
        new Service(getFollowingTask);
    }

    //    *****************************   FollowersPresenter ***************************************

    public void getFollowers(AuthToken currUserAuthToken, User user, int pageSize, User lastFollower, PagedNotificationObserver getFollowersObserver) {
        GetFollowersTask getFollowersTask = new GetFollowersTask(currUserAuthToken,
                user, pageSize, lastFollower, new PagedNotificationHandler<User>(getFollowersObserver));
        new Service(getFollowersTask);
    }

    //    *****************************   MainActivity IsFollower ***************************************

    public void isFollowerTask(AuthToken currUserAuthToken, User currUser, User selectedUser, BooleanNotificationObserver getIsFollowerObserver) {
        IsFollowerTask isFollowerTask = new IsFollowerTask(currUserAuthToken,
                currUser, selectedUser, new BooleanNotificationHandler(getIsFollowerObserver));
        new Service(isFollowerTask);
    }


    //    *****************************   MainActivity unFollow ***************************************

    public void unfollowUserTask(AuthToken currUserAuthToken, User selectedUser, SimpleNotificationObserver GetMainUnfollowObserver) {
        UnfollowTask unfollowTask = new UnfollowTask(currUserAuthToken,
                selectedUser, new SimpleNotificationHandler(GetMainUnfollowObserver));
        new Service(unfollowTask);
    }


    //    *****************************   MainActivity follow ***************************************

    public void followUserTask(AuthToken currUserAuthToken, User selectedUser, SimpleNotificationObserver getMainFollowObserver) {
        FollowTask followTask = new FollowTask(currUserAuthToken,
                selectedUser, new SimpleNotificationHandler(getMainFollowObserver));
        new Service(followTask);
    }

    //    *****************************   MainActivity GetFollowersCount ***************************************

    public void getFollowersCountTask(AuthToken currUserAuthToken, User selectedUser, ExecutorService executor, CountNotificationObserver getFollowersCountObserver) {
        GetFollowersCountTask followersCountTask = new GetFollowersCountTask(currUserAuthToken,
                selectedUser, new CountNotificationHandler(getFollowersCountObserver));
        new Service(followersCountTask);
    }

    //    *****************************   MainActivity GetFollowingCount ***************************************

    public void getFollowingCountTask(AuthToken currUserAuthToken, User selectedUser, ExecutorService executor, CountNotificationObserver getFollowingCountObserver) {
        GetFollowingCountTask followingCountTask = new GetFollowingCountTask(currUserAuthToken,
                selectedUser, new CountNotificationHandler(getFollowingCountObserver));
        new Service(followingCountTask);
    }


}
