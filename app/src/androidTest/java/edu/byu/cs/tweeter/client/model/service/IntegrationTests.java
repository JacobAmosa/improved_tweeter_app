package edu.byu.cs.tweeter.client.model.service;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.net.TweeterRemoteException;
import edu.byu.cs.shared.model.net.request.FollowerCountRequest;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.RegisterRequest;
import edu.byu.cs.shared.model.net.response.FollowerCountResponse;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.RegisterResponse;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;

public class IntegrationTests {

    @Test
    public void testRegister() throws IOException, TweeterRemoteException {
        RegisterRequest request = new RegisterRequest("jacob", "amosa", "@mamosa", "password!!", "blah");
        ServerFacade server = new ServerFacade();
        RegisterResponse response = server.register(request, "/register");

        Assert.assertTrue(response.isSuccess());
        Assert.assertNull(response.getMessage());
    }

    @Test
    public void testGetFollowers() throws IOException, TweeterRemoteException {
        FollowingRequest request = new FollowingRequest(new AuthToken("sdfdsf", "Dfsfds"), "@amosa", 10, "@last");
        ServerFacade server = new ServerFacade();
        FollowingResponse response = server.getFollowers(request, "/getfollowers");

        Assert.assertEquals(10, response.getFollowees().size());
        Assert.assertTrue(response.isSuccess());
        Assert.assertNull(response.getMessage());
    }

    @Test
    public void testGetFollowersCount() throws IOException, TweeterRemoteException {
        FollowerCountRequest request = new FollowerCountRequest(new AuthToken("sdfdsf", "Dfsfds"), "@jacob");
        ServerFacade server = new ServerFacade();
        FollowerCountResponse response = server.getFollowerCount(request, "/followercount");

        Assert.assertEquals(20, response.getCount());
        Assert.assertTrue(response.isSuccess());
        Assert.assertNull(response.getMessage());
    }









}
