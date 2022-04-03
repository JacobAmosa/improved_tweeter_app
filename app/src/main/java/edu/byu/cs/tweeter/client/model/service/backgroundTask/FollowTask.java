package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.request.FollowRequest;
import edu.byu.cs.shared.model.net.response.FollowResponse;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that establishes a following relationship between two users.
 */
public class FollowTask extends AuthenticatedTask {

    private static final String URL_PATH = "/follow";
    private ServerFacade serverFacade;
    private static final String LOG_TAG = "FollowTask";
    protected AuthToken authToken;
    private final User followee;

    ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return serverFacade;
    }

    public FollowTask(AuthToken authToken, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.followee = followee;
        this.authToken = authToken;
    }

    @Override
    protected void runTask() {
        try {
            FollowRequest request = new FollowRequest(authToken, followee, Cache.getInstance().getCurrUser());
            FollowResponse response = getServerFacade().follow(request, URL_PATH);

            if(response.isSuccess()) {
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception ex) {
            Log.e(LOG_TAG, "failed to follow user", ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
    }

}
