package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;

import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.UserNotificationObserver;

public class UserNotificationHandler extends BackgroundTaskHandler<UserNotificationObserver> {

    public UserNotificationHandler(UserNotificationObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccess(Bundle data, UserNotificationObserver observer) {
        observer.handleSuccess((User) data.getSerializable(GetUserTask.USER_KEY));
    }
}