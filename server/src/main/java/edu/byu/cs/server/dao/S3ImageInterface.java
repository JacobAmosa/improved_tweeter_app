package edu.byu.cs.server.dao;

import edu.byu.cs.shared.model.net.request.RegisterRequest;

public interface S3ImageInterface {
    String uploadImage(RegisterRequest request);
}
