package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.RegisterTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.AuthNotificationObserver;

public class AuthNotificationHandler extends BackgroundTaskHandler<AuthNotificationObserver>{

    public AuthNotificationHandler(AuthNotificationObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccess(Bundle data, AuthNotificationObserver observer) {
        observer.handleSuccess((AuthToken) data.getSerializable(RegisterTask.AUTH_TOKEN_KEY),
                (User) data.getSerializable(RegisterTask.USER_KEY));
    }
}
