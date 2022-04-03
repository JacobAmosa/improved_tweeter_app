package edu.byu.cs.server.dao;

public interface FactoryInterface {
    StatusInterface getStatusDAO();
    FollowInterface getFollowDAO();
    UserInterface getUserDAO();
    AuthInterface getAuthDAO();
    S3ImageInterface getS3ImageDAO();
}
