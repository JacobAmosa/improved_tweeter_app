package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.FollowerCountRequest;
import edu.byu.cs.shared.model.net.response.FollowerCountResponse;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that queries how many followers a user has.
 */
public class GetFollowersCountTask extends GetCountTask {
    private static final String URL_PATH = "/followercount";
    private ServerFacade serverFacade;
    private static final String LOG_TAG = "GetFollowerCountTask";
    protected AuthToken authToken;
    private int count;
    private final User user;

    public GetFollowersCountTask(AuthToken authToken, User targetUser, Handler messageHandler) {
        super(authToken, targetUser, messageHandler);
        this.authToken = authToken;
        this.user = targetUser;
    }

    ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return serverFacade;
    }

    @Override
    protected void runTask() {
        try {
            FollowerCountRequest request = new FollowerCountRequest(authToken, user.getAlias());
            FollowerCountResponse response = getServerFacade().getFollowerCount(request, URL_PATH);

            if(response.isSuccess()) {
                this.count = response.getCount();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to get follower count", ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putInt(COUNT_KEY, this.count);
    }

}
