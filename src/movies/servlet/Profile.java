package movies.servlet;

import movies.dal.*;
import movies.model.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/profile")
public class Profile extends HttpServlet {

    protected UsersDao usersDao;
    protected FollowersDao followersDao;
    protected FollowingDao followingDao;

    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
        followersDao = FollowersDao.getInstance();
        followingDao = FollowingDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Cookie[] cookies = req.getCookies();
        String userName = null;
        Users user = null;
        int followers;
        int following;
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            if(name.equals("user")){
                userName = cookies[i].getValue();
            }
        }
        try {
            user = usersDao.getUserFromUserName(userName);
            followers = followersDao.getFollowersByUserName(user.getUserName()).size();
            following = followingDao.getFollowingByUserName(user.getUserName()).size();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("user", user);
        req.setAttribute("Following", following);
        req.setAttribute("Followers", followers);
        req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
    }

}
