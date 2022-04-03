package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.request.FollowRequest;
import edu.byu.cs.shared.model.net.request.UnfollowRequest;
import edu.byu.cs.shared.model.net.response.FollowResponse;
import edu.byu.cs.shared.model.net.response.UnfollowResponse;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that removes a following relationship between two users.
 */
public class UnfollowTask extends AuthenticatedTask {

    private static final String URL_PATH = "/unfollow";
    private ServerFacade serverFacade;
    private static final String LOG_TAG = "UnfollowTask";
    protected AuthToken authToken;
    private final User followee;

    public UnfollowTask(AuthToken authToken, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.followee = followee;
        this.authToken = authToken;
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
            UnfollowRequest request = new UnfollowRequest(authToken, followee, Cache.getInstance().getCurrUser());
            UnfollowResponse response = getServerFacade().unfollow(request, URL_PATH);

            if(response.isSuccess()) {
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception ex) {
            Log.e(LOG_TAG, "failed to unfollow user", ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
    }


}
