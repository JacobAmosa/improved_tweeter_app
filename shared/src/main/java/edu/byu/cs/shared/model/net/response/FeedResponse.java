package edu.byu.cs.shared.model.net.response;

import java.util.List;
import java.util.Objects;

import edu.byu.cs.shared.model.domain.Status;

public class FeedResponse extends PagedResponse{
    private List<Status> feed;

    public FeedResponse(boolean success, boolean hasMorePages) {
        super(success, hasMorePages);
    }

    public FeedResponse(List<Status> feed, boolean hasMorePages) {
        super(true, hasMorePages);
        this.feed = feed;
    }

    public List<Status> getFeed() {
        return feed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedResponse that = (FeedResponse) o;
        return Objects.equals(feed, that.feed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feed);
    }
}
