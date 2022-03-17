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
 * Background task that retrieves a page of followers.
 */
public class GetFollowersTask extends PagedUserTask {
    private static final String LOG_TAG = "GetFollowersTask";

    public static final String FOLLOWEES_KEY = "followers";
    public static final String MORE_PAGES_KEY = "more-pages";

    private ServerFacade serverFacade;

    static final String URL_PATH = "/getfollowers";

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
    protected User lastFollower;
    /**
     * The followee users returned by the server.
     */
    private List<User> followers;
    /**
     * If there are more pages, returned by the server.
     */
    private boolean hasMorePages;

    public GetFollowersTask(AuthToken authToken, User targetUser, int limit, User lastFollower,
                            Handler messageHandler) {
        super(authToken, targetUser, limit, lastFollower, messageHandler);
        this.authToken = authToken;
        this.targetUser = targetUser;
        this.limit = limit;
        this.lastFollower = lastFollower;
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
            String lastFollowerAlias = lastFollower == null ? null : lastFollower.getAlias();

            FollowingRequest request = new FollowingRequest(authToken, targetUserAlias, limit, lastFollowerAlias);
            FollowingResponse response = getServerFacade().getFollowers(request, URL_PATH);

            if(response.isSuccess()) {
                this.followers = response.getFollowees();
                this.hasMorePages = response.getHasMorePages();
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to get followers", ex);
            sendExceptionMessage(ex);
        }
    }

    @Override
    protected Pair<List<User>, Boolean> getItems() {
        return getFakeData().getPageOfUsers(getLastItem(), getLimit(), getTargetUser());
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(ITEMS_KEY, (Serializable) this.followers);
        msgBundle.putBoolean(MORE_PAGES_KEY, this.hasMorePages);
    }
}
