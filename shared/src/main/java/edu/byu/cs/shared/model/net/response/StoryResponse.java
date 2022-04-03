package edu.byu.cs.shared.model.net.response;

import java.util.List;
import java.util.Objects;

import edu.byu.cs.shared.model.domain.Status;

public class StoryResponse extends PagedResponse{
    private List<Status> story;

    public StoryResponse(boolean success, boolean hasMorePages) {
        super(success, hasMorePages);
    }

    public StoryResponse(List<Status> story, boolean hasMorePages) {
        super(true, hasMorePages);
        this.story = story;
    }

    public StoryResponse(String message) {
        super(true, false);
    }

    public List<Status> getStory() {
        return story;
    }

    public void setStory(List<Status> story) {
        this.story = story;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryResponse that = (StoryResponse) o;
        return Objects.equals(story, that.story);
    }

    @Override
    public int hashCode() {
        return Objects.hash(story);
    }
}
