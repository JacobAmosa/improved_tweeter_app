package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.LoginTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.LogoutTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.RegisterTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.AuthNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.SimpleNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.UserNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.AuthNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.UserNotificationObserver;

public class UserService {

    //******************************** Followers Presenter **************************************************

    public void getFollowerUser(AuthToken currUserAuthToken, String userAlias, UserNotificationObserver getFollowerUserObserver) {
        GetUserTask getUserTask = new GetUserTask(currUserAuthToken,
                userAlias , new UserNotificationHandler(getFollowerUserObserver));
        new Service(getUserTask);
    }

    //******************************** FollowingPresenter **************************************************

    public void getFollowingUser(AuthToken currUserAuthToken, String userAlias, UserNotificationObserver GetFollowingUserObserver) {
        GetUserTask getUserTask = new GetUserTask(currUserAuthToken,
                userAlias, new UserNotificationHandler(GetFollowingUserObserver));
        new Service(getUserTask);
    }

    //******************************** StoryPresenter **************************************************

    public void getStoryUser(AuthToken currUserAuthToken, String userAlias, UserNotificationObserver getStoryUserObserver) {
        GetUserTask getUserTask = new GetUserTask(currUserAuthToken,
                userAlias, new UserNotificationHandler(getStoryUserObserver));
        new Service(getUserTask);
    }

    public void getStoryClickable(AuthToken currUserAuthToken, String clickable, UserNotificationObserver getStoryUserObserver) {
        GetUserTask getUserTask = new GetUserTask(currUserAuthToken,
                clickable, new UserNotificationHandler(getStoryUserObserver));
        new Service(getUserTask);
    }

    //******************************** Login Task **************************************************

    public void loginUserTask(String userAlias, String password, AuthNotificationObserver getLoginUserObserver) {
        LoginTask loginTask = new LoginTask(userAlias, password,
                new AuthNotificationHandler(getLoginUserObserver));
        new Service(loginTask);
    }

    //    *****************************   MainActivity logout ***************************************

    public void logoutUserTask(AuthToken currUserAuthToken, SimpleNotificationObserver getLogoutObserver) {
        LogoutTask logoutTask = new LogoutTask(currUserAuthToken, new SimpleNotificationHandler(getLogoutObserver));
        new Service(logoutTask);
    }

    //******************************** Register Task **************************************************

    public void registerUserTask(String firstName, String lastName, String userAlias, String password,
                                 String img, AuthNotificationObserver getRegisterUserObserver) {
        RegisterTask registerTask = new RegisterTask(firstName, lastName,
                userAlias, password, img, new AuthNotificationHandler(getRegisterUserObserver));
        new Service(registerTask);
    }

}
