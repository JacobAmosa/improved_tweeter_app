package edu.byu.cs.tweeter.client.presenter;

import java.util.concurrent.ExecutorService;

import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.FollowService;
import edu.byu.cs.tweeter.client.model.service.StatusService;
import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.BooleanNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.CountNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleNotificationObserver;

public class MainActivityPresenter extends Presenter<MainActivityPresenter.View> {
    private FollowService followService;
    private StatusService statusService;
    private UserService userService;

    public interface View extends Presenter.View{
        void setFollowButton(boolean isFollower);
        void updateUserInfo(boolean follow);
        void finishStatusCreation(String message);
        void finishLogout(String message);
        void setFollowerCount(int count);
        void setFolloweeCount(int count);
    }

    public MainActivityPresenter(View view){
        this.view = view;
        this.followService = new FollowService();
    }

    protected StatusService getStatusService(){
        if (statusService == null){
            return new StatusService();
        }
        return statusService;
    }

    protected UserService getUserService(){
        if (userService == null){
            return new UserService();
        }
        return userService;
    }

    public void isFollower(User selectedUser) {
        followService.isFollowerTask(Cache.getInstance().getCurrUserAuthToken(), Cache.getInstance().getCurrUser(), selectedUser, new GetIsFollowerObserver());
    }

    public void unfollowUser(User selectedUser) {
        followService.unfollowUserTask(Cache.getInstance().getCurrUserAuthToken(), selectedUser, new GetMainUnfollowObserver());
    }

    public void followUser(User selectedUser) {
        followService.followUserTask(Cache.getInstance().getCurrUserAuthToken(), selectedUser, new GetMainFollowObserver());
    }

    public void logoutUser() {
        getUserService().logoutUserTask(Cache.getInstance().getCurrUserAuthToken(), new GetLogoutObserver());
    }

    public void createStatus(Status newStatus) {
        getStatusService().createStatusTask(Cache.getInstance().getCurrUserAuthToken(), newStatus, new GetCreateStatusObserver());
    }

    public void getFollowersCount(User selectedUser, ExecutorService executor) {
        followService.getFollowersCountTask(Cache.getInstance().getCurrUserAuthToken(), selectedUser, executor, new GetFollowersCountObserver());
    }

    public void getFollowingCount(User selectedUser, ExecutorService executor) {
        followService.getFollowingCountTask(Cache.getInstance().getCurrUserAuthToken(), selectedUser, executor, new GetFollowingCountObserver());
    }

    public class GetFollowingCountObserver implements CountNotificationObserver{
        @Override
        public void handleSuccess(int count) {
            view.setFolloweeCount(count);
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to get following count: " + message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayErrorMessage("Failed to get following count because of exception: " + ex.getMessage());

        }
    }

    public class GetFollowersCountObserver implements CountNotificationObserver {
        @Override
        public void handleSuccess(int count) {
            view.setFollowerCount(count);
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to get followers count: " + message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayErrorMessage("Failed to get followers count because of exception: " + ex.getMessage());
        }
    }


    public class GetCreateStatusObserver implements SimpleNotificationObserver{
        @Override
        public void handleSuccess() {
            view.finishStatusCreation("Successfully Posted!");
        }

        @Override
        public void handleFailure(String message) {
//            view.displayErrorMessage("Failed to post status: " + message);
        }

        @Override
        public void handleException(Exception ex) {
//            view.displayErrorMessage("Failed to post status because of exception: " + ex.getMessage());
        }
    }

    public class GetLogoutObserver implements SimpleNotificationObserver {

        @Override
        public void handleSuccess() {
            view.finishLogout("Logging Out...");
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to logout: " + message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayErrorMessage("Failed to logout because of exception: " + ex.getMessage());
        }
    }

    public class GetIsFollowerObserver implements BooleanNotificationObserver {
        @Override
        public void handleSuccess(boolean isFollower) {
            view.setFollowButton(isFollower);
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to determine following relationship: " + message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayErrorMessage("Failed to determine following relationship because of exception: " + ex.getMessage());
        }
    }

    public class GetMainUnfollowObserver implements SimpleNotificationObserver {
        @Override
        public void handleSuccess() {
            view.updateUserInfo(true);
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to unfollow: " + message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayErrorMessage("Failed to unfollow because of exception: " + ex.getMessage());
        }
    }

    public class GetMainFollowObserver implements SimpleNotificationObserver{
        @Override
        public void handleSuccess() {
            view.updateUserInfo(false);
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to follow: " + message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayErrorMessage("Failed to follow because of exception: " + ex.getMessage());
        }
    }



}
