package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.request.LoginRequest;
import edu.byu.cs.shared.model.net.response.LoginResponse;
import edu.byu.cs.shared.util.Pair;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that logs in a user (i.e., starts a session).
 */
public class LoginTask extends AuthenticateTask {

    private static final String URL_PATH = "/login";

    private ServerFacade serverFacade;

    private static final String LOG_TAG = "LoginTask";

    public static final String USER_KEY = "user";
    public static final String AUTH_TOKEN_KEY = "auth-token";

    /**
     * The user's username (or "alias" or "handle"). E.g., "@susan".
     */
    private String username;
    /**
     * The user's password.
     */
    private String password;

    /**
     * The logged-in user returned by the server.
     */
    protected User user;

    /**
     * The auth token returned by the server.
     */
    protected AuthToken authToken;

    ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return serverFacade;
    }

    public LoginTask(String username, String password, Handler messageHandler) {
        super(messageHandler, username, password);

        this.username = username;
        this.password = password;
    }

    protected void runTask() {
        try {
            LoginRequest request = new LoginRequest(username, password);
            LoginResponse response = getServerFacade().login(request, URL_PATH);

            if(response.isSuccess()) {
                this.user = response.getUser();
                this.authToken = response.getAuthToken();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, this.user);
        msgBundle.putSerializable(AUTH_TOKEN_KEY, this.authToken);
    }

    @Override
    protected Pair<User, AuthToken> runAuthenticationTask() {
        User loggedInUser = getFakeData().getFirstUser();
        AuthToken authToken = getFakeData().getAuthToken();
        return new Pair<>(loggedInUser, authToken);
    }
}
