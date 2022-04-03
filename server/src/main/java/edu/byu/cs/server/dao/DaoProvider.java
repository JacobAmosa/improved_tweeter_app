package edu.byu.cs.server.dao;

public class DaoProvider implements FactoryInterface{
    @Override
    public StatusInterface getStatusDAO() {
        return new StatusDAO();
    }

    @Override
    public FollowInterface getFollowDAO() {
        return new FollowDAO();
    }

    @Override
    public UserInterface getUserDAO() {
        return new UserDAO();
    }

    @Override
    public AuthInterface getAuthDAO() {
        return new AuthDAO();
    }

    @Override
    public S3ImageInterface getS3ImageDAO() {
        return new S3ImageDAO();
    }
}
