package edu.byu.cs.tweeter.client.presenter;

import java.util.List;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedNotificationObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.UserNotificationObserver;

public abstract class PagedPresenter<T> extends Presenter<PagedPresenter.View>{
    private static final int PAGE_SIZE = 10;
    private User targetUser;
    private AuthToken authToken;
    private T lastItem;
    private boolean hasMorePages;
    private boolean isLoading = false;

    public interface View<T> extends Presenter.View{
        void setLoadingStatus(boolean value);
        void addItems(List<T> items);
        void getUser(User user);

    }

    public PagedPresenter(View view) {
        this.view = view;
    }

    public boolean hasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void loadMoreItems(User user){
        if (!isLoading) {   // This guard is important for avoiding a race condition in the scrolling code.
            isLoading = true;
            view.setLoadingStatus(true);
            getItems(Cache.getInstance().getCurrUserAuthToken(), user, PAGE_SIZE, lastItem, new GetItemObserver());
        }
    }

    abstract void getItems(AuthToken authToken, User user, int pageSize, T lastItem, GetItemObserver getItemObserver);

    public void getUser(String alias){
        new UserService().getFollowerUser(Cache.getInstance().getCurrUserAuthToken(), alias, new GetUserObserver());
    }

    public class GetItemObserver implements PagedNotificationObserver<T> {

        @Override
        public void handleSuccess(List<T> list, boolean hasMorePages) {
            isLoading = false;
            view.setLoadingStatus(false);
            lastItem = (list.size() > 0) ? list.get(list.size() - 1) : null;
            setHasMorePages(hasMorePages);
            view.addItems(list);
        }

        @Override
        public void handleFailure(String message) {
            isLoading = false;
            view.setLoadingStatus(false);
            view.displayErrorMessage("Failed to get items: " + message);
        }

        @Override
        public void handleException(Exception exception) {
            isLoading = false;
            view.setLoadingStatus(false);
            view.displayErrorMessage("Failed to get items because of exception: " + exception.getMessage());
        }
    }

    public class GetUserObserver implements UserNotificationObserver {
        @Override
        public void handleSuccess(User user) {
            isLoading = false;
            view.setLoadingStatus(false);
            setHasMorePages(hasMorePages);
            view.getUser(user);
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to get user's profile: " + message);
        }

        @Override
        public void handleException(Exception exception) {
            view.displayErrorMessage("Failed to get user's profile because of exception: " + exception.getMessage());
        }
    }











}
