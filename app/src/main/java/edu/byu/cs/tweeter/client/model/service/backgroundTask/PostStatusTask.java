package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that posts a new status sent by a user.
 */
public class PostStatusTask extends AuthenticatedTask {

    protected AuthToken authToken;
    private ServerFacade serverFacade;
    private boolean hasMorePages;
    private final Status status;
    static final String URL_PATH = "/createstatus";
    public static final String MORE_PAGES_KEY = "more-pages";
    private static final String LOG_TAG = "PostStatusTask";

    /**
     * The new status being sent. Contains all properties of the status,
     * including the identity of the user sending the status.
     */

    public PostStatusTask(AuthToken authToken, Status status, Handler messageHandler) {
        super(authToken, messageHandler);
        this.authToken = authToken;
        this.status = status;
    }

    public ServerFacade getServerFacade() {
        if(serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return new ServerFacade();
    }

    @Override
    protected void runTask() {
        try{
            CreateStatusRequest request = new CreateStatusRequest(authToken, status.getPost());
            CreateStatusResponse response = getServerFacade().createStatus(request, URL_PATH);

            if(response.isSuccess()) {
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }

        } catch (IOException | TweeterRemoteException ex) {
            Log.e(LOG_TAG, "Failed to post status", ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
    }

}
