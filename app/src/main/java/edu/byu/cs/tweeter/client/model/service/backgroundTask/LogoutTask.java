package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.net.request.LoginRequest;
import edu.byu.cs.shared.model.net.request.LogoutRequest;
import edu.byu.cs.shared.model.net.response.LoginResponse;
import edu.byu.cs.shared.model.net.response.LogoutResponse;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

/**
 * Background task that logs out a user (i.e., ends a session).
 */
public class LogoutTask extends AuthenticatedTask {

    private static final String URL_PATH = "/logout";
    private ServerFacade serverFacade;
    private static final String LOG_TAG = "LogoutTask";
    public static final String AUTH_TOKEN_KEY = "auth-token";
    protected AuthToken authToken;

    public LogoutTask(AuthToken authToken, Handler messageHandler) {
        super(authToken, messageHandler);
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
            LogoutRequest request = new LogoutRequest(authToken);
            LogoutResponse response = getServerFacade().logout(request, URL_PATH);

            if(response.isSuccess()) {
                sendSuccessMessage();
            }
            else {
                sendFailedMessage(response.getMessage());
            }
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex);
            sendExceptionMessage(ex);
        }
    }

    protected void loadSuccessBundle(Bundle msgBundle) {
    }


}
