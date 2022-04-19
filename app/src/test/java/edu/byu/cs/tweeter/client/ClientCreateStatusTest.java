package edu.byu.cs.tweeter.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.LoginRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.CreateStatusResponse;
import edu.byu.cs.shared.model.net.response.LoginResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

public class ClientCreateStatusTest {
    User user = new User("mallory", "romine", "@mallory","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
    AuthToken authToken = new AuthToken("6fbacf9c-007e-4db5-bce7-df796a0f1bc4", "1648229616175");
    private ServerFacade serverFacade;

    @Before
    public void setup() {
        serverFacade = new ServerFacade();
    }

    @Test
    public void testCreateStatus() throws IOException, TweeterRemoteException {
        String newPost = "hfjssjdk";
        LoginResponse loginResponse = serverFacade.login(new LoginRequest("@mallory", "password"), "/login");
        Assert.assertEquals("mallory", loginResponse.getUser().getFirstName());
        Assert.assertEquals("amosa", loginResponse.getUser().getLastName());

        CreateStatusRequest status = new CreateStatusRequest(loginResponse.getAuthToken(), newPost, loginResponse.getUser());
        CreateStatusResponse createStatusResponse = serverFacade.createStatus(status, "/createstatus");
        Assert.assertEquals(true ,createStatusResponse.isSuccess());

        StoryResponse storyResponse = serverFacade.getStory(new StoryRequest(authToken, "@mallory", 10, ""), "/getstory");
        int count = 0;
        for (Status s: storyResponse.getStory()) {
            if (s.getPost().equals(newPost)) {
                count++;
            }
        }
        Assert.assertEquals(1, count);
    }


}
