import android.content.Context;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.byu.cs.server.dao.AuthDAO;
import edu.byu.cs.server.dao.DaoProvider;
import edu.byu.cs.server.dao.FollowDAO;
import edu.byu.cs.server.dao.StatusDAO;
import edu.byu.cs.server.dao.UserDAO;
import edu.byu.cs.server.lambda.GetStoryHandler;
import edu.byu.cs.server.service.StatusService;
import edu.byu.cs.server.service.UserService;
import edu.byu.cs.shared.model.domain.AuthToken;
import edu.byu.cs.shared.model.domain.User;
import edu.byu.cs.shared.model.net.request.CreateStatusRequest;
import edu.byu.cs.shared.model.net.request.FeedRequest;
import edu.byu.cs.shared.model.net.request.FollowRequest;
import edu.byu.cs.shared.model.net.request.FollowerCountRequest;
import edu.byu.cs.shared.model.net.request.FollowingCountRequest;
import edu.byu.cs.shared.model.net.request.FollowingRequest;
import edu.byu.cs.shared.model.net.request.GetUserRequest;
import edu.byu.cs.shared.model.net.request.IsFollowerRequest;
import edu.byu.cs.shared.model.net.request.LoginRequest;
import edu.byu.cs.shared.model.net.request.RegisterRequest;
import edu.byu.cs.shared.model.net.request.StoryRequest;
import edu.byu.cs.shared.model.net.request.UnfollowRequest;
import edu.byu.cs.shared.model.net.response.FeedResponse;
import edu.byu.cs.shared.model.net.response.FollowResponse;
import edu.byu.cs.shared.model.net.response.FollowerCountResponse;
import edu.byu.cs.shared.model.net.response.FollowingCountResponse;
import edu.byu.cs.shared.model.net.response.FollowingResponse;
import edu.byu.cs.shared.model.net.response.GetUserResponse;
import edu.byu.cs.shared.model.net.response.IsFollowerResponse;
import edu.byu.cs.shared.model.net.response.LoginResponse;
import edu.byu.cs.shared.model.net.response.RegisterResponse;
import edu.byu.cs.shared.model.net.response.StoryResponse;
import edu.byu.cs.shared.model.net.response.UnfollowResponse;

public class Debug {

    public static void main(String[] args) {
        AuthToken authToken = new AuthToken("6fbacf9c-007e-4db5-bce7-df796a0f1bc4", "1648229616175");
//
        User user1 = new User("mallory", "romine", "@mallory","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user2 = new User("joseph", "amosa", "@joseph","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        User user3 = new User("ally", "vigh", "@ally","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user4 = new User("jacob", "amosa", "@jacob","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        User user5 = new User("daniel", "amosa", "@daniel","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        User user6 = new User("kiki", "saifoloi", "@kiki","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user7 = new User("rebekah", "amosa", "@rebekah","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user8 = new User("rachel", "elena", "@rachel","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user9 = new User("sarah", "fain", "@sarah","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user10 = new User("luca", "amosa", "@luca","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        User user11 = new User("maria", "fain", "@maria","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user12 = new User("ben", "amosa", "@ben","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        User user13 = new User("parker", "garris", "@parker","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        User user14 = new User("morgan", "weight", "@morgan","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
//        User user15 = new User("kirk", "garrard", "@kirk","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        User user16 = new User("jimmy", "nuetron", "@jimmy","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//
//        //USERS
//        List<User> users = new ArrayList<>();
//        users.add(new User("mallory", "romine", "@mallory","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("joseph", "amosa", "@joseph","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        users.add(new User("ally", "vigh", "@ally","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("jacob", "amosa", "@jacob","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        users.add(new User("daniel", "amosa", "@daniel","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        users.add(new User("kiki", "saifoloi", "@kiki","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("rebekah", "amosa", "@rebekah","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("rachel", "elena", "@rachel","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("sarah", "fain", "@sarah","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("luca", "amosa", "@luca","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        users.add(new User("maria", "fain", "@maria","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("ben", "amosa", "@ben","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        users.add(new User("parker", "garris", "@parker","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        users.add(new User("morgan", "weight", "@morgan","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        users.add(new User("kirk", "garrard", "@kirk","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        users.add(new User("jimmy", "nuetron", "@jimmy","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//
//        //REGISTERREQUESTS
//        List<RegisterRequest> regReq = new ArrayList<>();
//        regReq.add(new RegisterRequest("mallory", "romine", "@mallory","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("joseph", "amosa", "@joseph","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        regReq.add(new RegisterRequest("ally", "vigh", "@ally","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("jacob", "amosa", "@jacob","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        regReq.add(new RegisterRequest("daniel", "amosa", "@daniel","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        regReq.add(new RegisterRequest("kiki", "saifoloi", "@kiki","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("rebekah", "amosa", "@rebekah","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("rachel", "elena", "@rachel","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("sarah", "fain", "@sarah","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("luca", "amosa", "@luca","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        regReq.add(new RegisterRequest("maria", "fain", "@maria","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("ben", "amosa", "@ben","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        regReq.add(new RegisterRequest("parker", "garris", "@parker","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        regReq.add(new RegisterRequest("morgan", "weight", "@morgan","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png"));
//        regReq.add(new RegisterRequest("kirk", "garrard", "@kirk","password","https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//
//
//        //FOLLOWS
//        List<FollowRequest> followRequests = new ArrayList<>();
//        followRequests.add(new FollowRequest(authToken, user1, user2));
//        followRequests.add(new FollowRequest(authToken, user1, user3));
//        followRequests.add(new FollowRequest(authToken, user1, user4));
//        followRequests.add(new FollowRequest(authToken, user1, user5));
//        followRequests.add(new FollowRequest(authToken, user2, user6));
//        followRequests.add(new FollowRequest(authToken, user2, user7));
//        followRequests.add(new FollowRequest(authToken, user2, user8));
//        followRequests.add(new FollowRequest(authToken, user2, user9));
//        followRequests.add(new FollowRequest(authToken, user10, user1));
//        followRequests.add(new FollowRequest(authToken, user11, user4));
//        followRequests.add(new FollowRequest(authToken, user4, user2));
//        followRequests.add(new FollowRequest(authToken, user15, user3));
//        followRequests.add(new FollowRequest(authToken, user1, user14));
//        followRequests.add(new FollowRequest(authToken, user7, user5));
//        followRequests.add(new FollowRequest(authToken, user11, user2));
//
//        //STATUSES
//        List<CreateStatusRequest> statusRequests = new ArrayList<>();
//        statusRequests.add(new CreateStatusRequest(authToken, "message 1", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 2", user2));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 3", user3));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 4", user4));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 5", user5));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 6", user6));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 7", user7));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 8", user8));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 9", user9));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 10", user10));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 11", user11));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 12", user12));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 13", user13));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 14", user14));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 15", user15));

//        List<CreateStatusRequest> statusRequests = new ArrayList<>();
//        statusRequests.add(new CreateStatusRequest(authToken, "message 1", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 2", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 3", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 4", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 5", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 6", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 7", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 8", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 9", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 10", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 11", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 12", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 13", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 14", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 15", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 10", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 11", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 12", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 13", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 14", user1));
//        statusRequests.add(new CreateStatusRequest(authToken, "message 15", user1));


        FollowDAO fDao = new FollowDAO();
//        FollowingResponse response = fDao.getFollowing(new FollowingRequest(authToken, "@mallory", 10, null));
//        int j = 0;
//
//        FollowingResponse response2 = fDao.getFollowers(new FollowingRequest(authToken, "@mallory", 10, null));
//        int k = 0;

//        FollowingCountResponse res = fDao.getFollowingCount(new FollowingCountRequest(authToken, "@mallory"));
//        int w = 0;

//        FollowerCountResponse res = fDao.getFollowerCount(new FollowerCountRequest(authToken, "@mallory"));
//        int w = 0;

//        IsFollowerResponse res2 = fDao.isFollower(new IsFollowerRequest(authToken, "@mallory", "@kirk"));
//        int p = 0;

//        FollowResponse res2 = fDao.follow(new FollowRequest(authToken, user13, user10));
//        int p = 0;

//        UnfollowResponse res3 = fDao.unfollow(new UnfollowRequest(authToken, user13, user10));
//        int e = 0;

        StatusDAO sDao = new StatusDAO();
//        List<String> aliases = fDao.getFollowerAliases("@mallory");
//        int pp = 0;
//
//        Long time = Calendar.getInstance().getTimeInMillis();
//
//        sDao.createStatus(new CreateStatusRequest(authToken, "5 times in feed and 1 in story", user1), aliases, String.valueOf(time));
//        int yy = 0;

//        FeedResponse feed = sDao.getFeed(new FeedRequest(authToken, "@mallory", 10, null));
//        int kk = 9;
//        StatusService statusService = new StatusService();
//        FeedResponse feed = statusService.getFeed(new FeedRequest(authToken, "@mallory", 10, null));
//        int j = 0;
//
//        StoryResponse story = sDao.getStory(new StoryRequest(authToken, "@mallory", 10, null));
//        int yy = 9;

        UserDAO uDao = new UserDAO();

//        RegisterResponse reg = uDao.register(new RegisterRequest("mason", "bf", "@mason", "password", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        int h = 0;

//        UserService userService = new UserService();
//        userService.register(new RegisterRequest("ruben", "amosa", "@ruben", "password", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png"));
//        int kkk = 0;

//        LoginResponse res = uDao.login(new LoginRequest("@mallory", "pwwssword"));
//        int y = 0;

//        UserService userService = new UserService();
//        LoginResponse l = userService.login(new LoginRequest("@mallory", "password"));

//        GetUserResponse res = uDao.getUser(new GetUserRequest(authToken, "@mallory"));
//        int j = 0;


//        AuthDAO aDao = new AuthDAO();
//        aDao.addToken("testToken", "testTimestamp");
//        int rr = 0;
//
//        String res = aDao.validateToken("testToken");
//        int tt = 0;
//        UserService us = new UserService();
//        for (RegisterRequest r: regReq){
//            us.register(r);
//        }
//        for (FollowRequest f: followRequests){
//            fDao.follow(f);
//        }
//        for (CreateStatusRequest c: statusRequests){
//            List<String> aliases = new DaoProvider().getFollowDAO().getFollowerAliases(c.getUser().getAlias());
//            Long date = Calendar.getInstance().getTimeInMillis();
//            sDao.createStatus(c, aliases, date.toString());
//        }


//        String imageBytesBase64 = Base64.getEncoder().encodeToString("hjkhjkhkj".getBytes());
//        RegisterResponse url = new DaoProvider().getUserDAO().register(new RegisterRequest("steven", "garcia", "@steven","password",imageBytesBase64));
//        int j = 0;


//        UserService userService = new UserService();
//        userService.register(new RegisterRequest("lin", "bin", "@lin","password",imageBytesBase64));
//        int pj = 0;




    }






}
