package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.request.IsFollowerRequest;
import edu.byu.cs.shared.model.net.response.GetUserResponse;
import edu.byu.cs.shared.model.net.response.IsFollowerResponse;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that determines if one user is following another.
 */
public class IsFollowerTask extends AuthenticatedTask {
    private static final String LOG_TAG = "IsFollowerTask";
    public static final String IS_FOLLOWER_KEY = "is-follower";
    private ServerFacade serverFacade;
    static final String URL_PATH = "/isfollower";
    protected AuthToken authToken;
    private final User follower;
    private final User followee;
    private boolean isFollower;

    public IsFollowerTask(AuthToken authToken, User follower, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.follower = follower;
        this.followee = followee;
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
            IsFollowerRequest request = new IsFollowerRequest(authToken, follower.getAlias(), followee.getAlias());
            IsFollowerResponse response = getServerFacade().isFollower(request, URL_PATH);

            if(response.isSuccess()) {
                this.isFollower = response.getIsFollower();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to very if is Follower", ex);
            sendExceptionMessage(ex);
        }
    }

    @Override
    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putBoolean(IS_FOLLOWER_KEY, isFollower);
    }
}
