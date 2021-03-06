package edu.byu.cs.tweeter.client.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.util.FakeData;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedNotificationObserver;

public class FollowServiceTest {

    private User currentUser;
    private AuthToken currentAuthToken;
    private FollowService followServiceSpy;
    private FollowServiceObserver observer;
    private CountDownLatch countDownLatch;

    /**
     * Create a FollowService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @Before
    public void setup() {
        currentUser = new User("FirstName", "LastName", null);
        currentAuthToken = new AuthToken();

        followServiceSpy = Mockito.spy(new FollowService());

        // Setup an observer for the FollowService
        observer = new FollowServiceObserver();

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
    private class FollowServiceObserver implements PagedNotificationObserver<User>{

        private boolean success;
        private String message;
        private List<User> followees;
        private boolean hasMorePages;
        private String exception = null;

        @Override
        public void handleSuccess(List<User> followees, boolean hasMorePages) {
            this.success = true;
            this.message = null;
            this.followees = followees;
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

        public List<User> getFollowees() {
            return followees;
        }

        public boolean getHasMorePages() {
            return hasMorePages;
        }
    }

    @Test
    public void testGetFollowees_validRequest_correctResponse() throws InterruptedException {
        followServiceSpy.getFollowing(currentAuthToken, currentUser, 3, null, observer);
        awaitCountDownLatch();

        List<User> expectedFollowees = new FakeData().getFakeUsers().subList(0, 3);
        Assert.assertTrue(observer.isSuccess());
        Assert.assertNull(observer.getMessage());
        Assert.assertEquals(expectedFollowees, observer.getFollowees());
        Assert.assertTrue(observer.getHasMorePages());
        Assert.assertNull(observer.getException());
    }

    @Test
    public void testGetFollowees_validRequest_loadsProfileImages() throws InterruptedException {
        followServiceSpy.getFollowers(currentAuthToken, currentUser, 3, null, observer);
        awaitCountDownLatch();

        List<User> followees = observer.getFollowees();
        Assert.assertTrue(followees.size() > 0);
    }

}
