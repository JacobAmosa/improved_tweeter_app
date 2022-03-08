package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.AuthNotificationObserver;

public abstract class AuthPresenter<T> extends Presenter<AuthPresenter.View> {

    public interface View extends Presenter.View{
        void finishAuthentication(User loggedInUser);
    }

    public AuthPresenter(View view) {
        this.view = view;
    }

    public class GetAuthObserver implements AuthNotificationObserver{

        @Override
        public void handleSuccess(AuthToken auth, User user) {
            // Cache user session information
            Cache.getInstance().setCurrUser(user);
            Cache.getInstance().setCurrUserAuthToken(auth);
            view.finishAuthentication(user);
        }

        @Override
        public void handleFailure(String message) {
            view.displayErrorMessage("Failed to authorize: " + message);
        }

        @Override
        public void handleException(Exception exception) {
            view.displayErrorMessage("Failed to login because of exception: " + exception.getMessage());
        }
    }
}
