package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.FeedResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;
import edu.byu.cs.shared.util.Pair;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that retrieves a page of statuses from a user's feed.
 */
public class GetFeedTask extends PagedStatusTask {
    private static final String LOG_TAG = "GetFeedTask";
    public static final String MORE_PAGES_KEY = "more-pages";
    private ServerFacade serverFacade;
    static final String URL_PATH = "/getfeed";
    /**
     * Auth token for logged-in user.
     */
    protected AuthToken authToken;
    /**
     * The user whose following is being retrieved.
     * (This can be any user, not just the currently logged-in user.)
     */
    protected User targetUser;
    /**
     * Maximum number of followed users to return (i.e., page size).
     */
    protected int limit;

    protected Status lastStatus;
    /**
     * The followee users returned by the server.
     */
    private List<Status> feed;
    /**
     * If there are more pages, returned by the server.
     */
    private boolean hasMorePages;

    public GetFeedTask(AuthToken authToken, User targetUser, int limit, Status lastStatus,
                       Handler messageHandler) {
        super(authToken, targetUser, limit, lastStatus, messageHandler);
        this.authToken = authToken;
        this.targetUser = targetUser;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }

    public ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return new ServerFacade();
    }



    protected void runTask() throws IOException {
        try {
            String targetUserAlias = targetUser == null ? null : targetUser.getAlias();
            String lastStatusMessage = lastStatus == null ? null : lastStatus.getPost();

            FeedRequest request = new FeedRequest(authToken, targetUserAlias, limit, lastStatusMessage);
            FeedResponse response = getServerFacade().getFeed(request, URL_PATH);

            if(response.isSuccess()) {
                this.feed = response.getFeed();
                this.hasMorePages = response.getHasMorePages();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to get feed", ex);
            sendExceptionMessage(ex);
        }
    }

    @Override
    protected Pair<List<Status>, Boolean> getItems() {
        return null;
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(ITEMS_KEY, (Serializable) this.feed);
        msgBundle.putBoolean(MORE_PAGES_KEY, this.hasMorePages);
    }
}
