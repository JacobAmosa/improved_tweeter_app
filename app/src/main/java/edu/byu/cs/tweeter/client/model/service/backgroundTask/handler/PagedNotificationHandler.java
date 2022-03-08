package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;

import java.util.List;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.PagedTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedNotificationObserver;

public class PagedNotificationHandler<T> extends BackgroundTaskHandler<PagedNotificationObserver> {

    public PagedNotificationHandler(PagedNotificationObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccess(Bundle data, PagedNotificationObserver observer) {
        observer.handleSuccess((List<T>) data.getSerializable(PagedTask.ITEMS_KEY) ,
                data.getBoolean(GetFeedTask.MORE_PAGES_KEY));
    }


}
