package edu.byu.cs.server.dao;

public interface AuthInterface {
    void addToken(String token, String timeStamp);
    String validateToken(String token);
}
