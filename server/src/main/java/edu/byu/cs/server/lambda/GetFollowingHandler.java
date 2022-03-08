package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.service.FollowService;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.response.FollowingResponse;

/**
 * An AWS lambda function that returns the users a user is following.
 */
public class GetFollowingHandler implements RequestHandler<FollowingRequest, FollowingResponse> {

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param input contains the data required to fulfill the request.
     * @param context the lambda context.
     * @return the followees.
     */

    @Override
    public FollowingResponse handleRequest(FollowingRequest input, com.amazonaws.services.lambda.runtime.Context context) {
        FollowService service = new FollowService();
        return service.getFollowees(input);
    }
}
