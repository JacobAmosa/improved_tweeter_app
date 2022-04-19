package edu.byu.cs.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.server.dao.Filler;

public class FillerHandler implements RequestHandler {
    @Override
    public Object handleRequest(Object input, Context context) {
        Filler.fillDatabase();
        return null;
    }
}
