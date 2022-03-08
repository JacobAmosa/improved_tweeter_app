package edu.byu.cs.tweeter.client.model.service.backgroundTask.observer;

import edu.byu.cs.shared.model.domain.User;

public interface UserNotificationObserver extends ServiceObserver{
    void handleSuccess(User user);
}
