package edu.byu.cs.tweeter.client.model.service.backgroundTask.observer;

public interface BooleanNotificationObserver extends ServiceObserver{
    void handleSuccess(boolean value);
}
