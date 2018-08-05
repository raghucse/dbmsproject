package movies.servlet;

import movies.dal.*;
import movies.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/followUser")
public class FollowUser extends HttpServlet {

    protected FollowersDao followersDao;
    protected FollowingDao followingDao;

    @Override
    public void init() throws ServletException {
        followersDao = FollowersDao.getInstance();
        followingDao = FollowingDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Cookie[] cookies = req.getCookies();
        String user = null;
        String searchUser = null;
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            if(name.equals("user")){
                user = cookies[i].getValue();
            }
            if(name.equals("searchuser")){
                searchUser = cookies[i].getValue();
            }
        }
            try {
                if(!followersDao.checkEntry(searchUser, user)) {
	                Followers followers = new Followers(searchUser, user);
	                Followers createdFollower = followersDao.create(followers);
	
	                Following following = new Following(user,searchUser);
	                Following createdFollowing = followingDao.create(following);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Following " + searchUser);
         req.getRequestDispatcher("/searchProfile.jsp").forward(req, resp);
        }
}

