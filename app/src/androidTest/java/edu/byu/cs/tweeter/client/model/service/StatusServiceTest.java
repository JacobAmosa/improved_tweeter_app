package edu.byu.cs.tweeter.client.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.Status;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.util.FakeData;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedNotificationObserver;

public class StatusServiceTest {

    private User currentUser;
    private AuthToken currentAuthToken;
    private StatusService StoryServiceSpy;
    private StoryServiceObserver observer;
    private CountDownLatch countDownLatch;

    /**
     * Create a FollowService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @Before
    public void setup() {
        currentUser = new User("FirstName", "LastName", null);
        currentAuthToken = new AuthToken();

        StoryServiceSpy = Mockito.spy(new StatusService());

        // Setup an observer for the FollowService
        observer = new StoryServiceObserver();

        // Prepare the countdown latch
        resetCountDownLatch();
    }

    private void resetCountDownLatch() {
        countDownLatch = new CountDownLatch(1);
    }

    private void awaitCountDownLatch() throws InterruptedException {
        countDownLatch.await();
        resetCountDownLatch();
    }

    /**
     * A implementation that can be used to get the values
     * eventually returned by an asynchronous call on the {@link FollowService}. Counts down
     * on the countDownLatch so tests can wait for the background thread to call a method on the
     * observer.
     */
    private class StoryServiceObserver implements PagedNotificationObserver<Status>{

        private boolean success;
        private String message;
        private List<Status> story;
        private boolean hasMorePages;
        private String exception = null;

        @Override
        public void handleSuccess(List<Status> story, boolean hasMorePages) {
            this.success = true;
            this.message = null;
            this.story = story;
            this.hasMorePages = hasMorePages;
            countDownLatch.countDown();
        }

        @Override
        public void handleFailure(String message) {

        }

        @Override
        public void handleException(Exception exception) {

        }

        public String getException() { return exception; }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public List<Status> getStory() {
            return story;
        }

        public boolean getHasMorePages() {
            return hasMorePages;
        }
    }

    @Test
    public void testGetUserStory_validRequest_correctResponse() throws InterruptedException {
        StoryServiceSpy.getUserStory(currentAuthToken, currentUser, 3, null, observer);
        awaitCountDownLatch();

        List<Status> expectedStatuses = new FakeData().getFakeStatuses().subList(0, 3);
        Assert.assertTrue(observer.isSuccess());
        Assert.assertNull(observer.getMessage());
        Assert.assertTrue(observer.getHasMorePages());
        Assert.assertNull(observer.getException());
    }


}
