package edu.byu.cs.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.dao.StatusDAO;
import edu.byu.cs.server.service.StatusService;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.response.StoryResponse;

public class StatusServiceTest {
    private StatusDAO mockStatusDao;
    private DaoProvider mockDao;
    private StoryRequest mockStoryRequest;
    private StoryResponse mockStoryResponse;
    private StatusService statusService;

    @Before
    public void setup() {
        mockDao = Mockito.mock(DaoProvider.class);
        mockStatusDao = Mockito.mock(StatusDAO.class);
        mockStoryRequest = Mockito.mock(StoryRequest.class);
        mockStoryResponse = Mockito.mock(StoryResponse.class);
        statusService = new StatusService(mockDao);
        when(mockDao.getStatusDAO()).thenReturn(mockStatusDao);
        when(mockStoryRequest.getUser()).thenReturn("@mallory");
        when(mockStoryRequest.getLimit()).thenReturn(10);
        when(mockStatusDao.getStory(mockStoryRequest)).thenReturn(mockStoryResponse);
    }

    @Test
    public void testServerService_to_returnStories() {
        StoryResponse storyResponse = statusService.getStory(mockStoryRequest);
        Assert.assertEquals(storyResponse, mockStoryResponse);
    }






}
