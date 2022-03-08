package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.IsFollowerTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.BooleanNotificationObserver;

public class BooleanNotificationHandler extends BackgroundTaskHandler<BooleanNotificationObserver>{

    public BooleanNotificationHandler(BooleanNotificationObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccess(Bundle data, BooleanNotificationObserver observer) {
        observer.handleSuccess(data.getBoolean(IsFollowerTask.IS_FOLLOWER_KEY));
    }
}
