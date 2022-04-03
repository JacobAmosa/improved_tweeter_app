package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.RegisterRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.RegisterResponse;
import edu.byu.cs.shared.util.Pair;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that creates a new user account and logs in the new user (i.e., starts a session).
 */
public class RegisterTask extends AuthenticateTask {

    private final String firstName;
    private final String lastName;
    private final String image;
    private final String username;
    private final String password;
    private User user;
    protected AuthToken authToken;
    private ServerFacade serverFacade;
    static final String URL_PATH = "/register";
    private static final String LOG_TAG = "RegisterTask";

    public RegisterTask(String firstName, String lastName, String username, String password,
                        String image, Handler messageHandler) {
        super(messageHandler, username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.username = username;
        this.password = password;
    }

    public ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return new ServerFacade();
    }

    protected void runTask() {
        try{
            RegisterRequest request = new RegisterRequest(firstName, lastName, username, password, image);
            RegisterResponse response = getServerFacade().register(request, URL_PATH);

            if(response.isSuccess()) {
                user = response.getUser();
                Cache.getInstance().setCurrUser(user);
                Cache.getInstance().setCurrUserAuthToken(response.getAuthToken());
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }

        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to register user", ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, user);
    }

    @Override
    protected Pair<User, AuthToken> runAuthenticationTask() {
        User registeredUser = getFakeData().getFirstUser();
        AuthToken authToken = getFakeData().getAuthToken();
        return new Pair<>(registeredUser, authToken);
    }
}
