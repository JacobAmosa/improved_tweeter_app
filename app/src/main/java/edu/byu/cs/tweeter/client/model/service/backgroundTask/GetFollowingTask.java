package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.util.Pair;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that retrieves a page of other users being followed by a specified user.
 */
public class GetFollowingTask extends PagedUserTask {

    private static final String LOG_TAG = "GetFollowingTask";

    public static final String FOLLOWEES_KEY = "followees";
    public static final String MORE_PAGES_KEY = "more-pages";


    private ServerFacade serverFacade;

    static final String URL_PATH = "/getfollowing";

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
    /**
     * The last person being followed returned in the previous page of results (can be null).
     * This allows the new page to begin where the previous page ended.
     */
    protected User lastFollowee;
    /**
     * The followee users returned by the server.
     */
    private List<User> followees;
    /**
     * If there are more pages, returned by the server.
     */
    private boolean hasMorePages;
    public GetFollowingTask(AuthToken authToken, User targetUser, int limit, User lastFollowee,
                            Handler messageHandler) {
        super(authToken, targetUser, limit, lastFollowee, messageHandler);
        this.authToken = authToken;
        this.targetUser = targetUser;
        this.limit = limit;
        this.lastFollowee = lastFollowee;
    }

    public ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return new ServerFacade();
    }

    protected void runTask() {
        try {
            String targetUserAlias = targetUser == null ? null : targetUser.getAlias();
            String lastFolloweeAlias = lastFollowee == null ? null : lastFollowee.getAlias();

            FollowingRequest request = new FollowingRequest(authToken, targetUserAlias, limit, lastFolloweeAlias);
            FollowingResponse response = getServerFacade().getFollowees(request, URL_PATH);

            if(response.isSuccess()) {
                this.followees = response.getFollowees();
                this.hasMorePages = response.getHasMorePages();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to get followees", ex);
            sendExceptionMessage(ex);
        }
    }

    @Override
    protected Pair<List<User>, Boolean> getItems() {
        return null;
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(ITEMS_KEY, (Serializable) this.followees);
        msgBundle.putBoolean(MORE_PAGES_KEY, this.hasMorePages);
    }


}
