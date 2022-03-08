package edu.byu.cs.tweeter.client.model.service.backgroundTask.observer;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;

public interface AuthNotificationObserver extends ServiceObserver{
    void handleSuccess(AuthToken auth, User user);
}
