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
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;
import edu.byu.cs.shared.util.Pair;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that retrieves a page of statuses from a user's story.
 */
public class GetStoryTask extends PagedStatusTask {
    private static final String LOG_TAG = "GetStoryTask";
    public static final String MORE_PAGES_KEY = "more-pages";
    private ServerFacade serverFacade;
    static final String URL_PATH = "/getstory";
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
    private List<Status> story;
    /**
     * If there are more pages, returned by the server.
     */
    private boolean hasMorePages;

    public GetStoryTask(AuthToken authToken, User targetUser, int limit, Status lastStatus,
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

    @Override
    protected Pair<List<Status>, Boolean> getItems() {
        // don't need anymore.
        return null;
    }

    protected void runTask() throws IOException {
        try {
            String targetUserAlias = targetUser == null ? null : targetUser.getAlias();
            String lastStatusMessage = lastStatus == null ? null : lastStatus.getPost();

            StoryRequest request = new StoryRequest(authToken, targetUserAlias, limit, lastStatusMessage);
            StoryResponse response = getServerFacade().getStory(request, URL_PATH);

            if(response.isSuccess()) {
                this.story = response.getStory();
                this.hasMorePages = response.getHasMorePages();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to get story", ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(ITEMS_KEY, (Serializable) this.story);
        msgBundle.putBoolean(MORE_PAGES_KEY, this.hasMorePages);
    }
}
