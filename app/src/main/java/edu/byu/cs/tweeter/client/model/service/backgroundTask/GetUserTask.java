package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.GetUserResponse;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that returns the profile for a specified user.
 */
public class GetUserTask extends AuthenticatedTask {
    private static final String LOG_TAG = "GetUserTask";
    public static final String USER_KEY = "user";
    private ServerFacade serverFacade;

    static final String URL_PATH = "/getuser";
    /**
     * Alias (or handle) for user whose profile is being retrieved.
     */
    private final String alias;
    private User user;
    protected AuthToken authToken;

    public GetUserTask(AuthToken authToken, String alias, Handler messageHandler) {
        super(authToken, messageHandler);
        this.alias = alias;
        this.authToken = authToken;
    }

    public ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return new ServerFacade();
    }

    @Override
    protected void runTask() {
        try {
            GetUserRequest request = new GetUserRequest(authToken, alias);
            GetUserResponse response = getServerFacade().getUser(request, URL_PATH);

            if(response.isSuccess()) {
                this.user = response.getUser();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to get user", ex);
            sendExceptionMessage(ex);
        }
    }

    @Override
    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, user);
    }

}
