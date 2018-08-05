package movies.servlet;

import movies.dal.*;
import movies.model.*;
import java.io.IOException;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dashboardsearch")
public class DashBoardSearch extends HttpServlet {

    protected UsersDao usersDao;
    protected FollowersDao followersDao;
    protected FollowingDao followingDao;
    protected MoviesDao moviesDao;

    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
        followersDao = FollowersDao.getInstance();
        followingDao = FollowingDao.getInstance();
        moviesDao = MoviesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Movies> movies = null;
        Users users = null;
        int followers;
        int following;
        String search = req.getParameter("search");
        String selection = req.getParameter("selection");
        
        if(selection.equals("0")) {
        	System.out.println("searching User");
            try {
                users = usersDao.getUserFromUserName(search);
                followers = followersDao.getFollowersByUserName(search).size();
                following = followingDao.getFollowingByUserName(search).size();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            Cookie searchuser = new Cookie("searchuser",
                    users.getUserName());
            resp.addCookie(searchuser);
            req.setAttribute("Users", users);
            req.setAttribute("Following", following);
            req.setAttribute("Followers", followers);
            req.getRequestDispatcher("/searchProfile.jsp").forward(req, resp);
        }else {
        	//code for movie search
            System.out.println("Searching Movies");
            try{
                movies = moviesDao.getMovieByName(search);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            req.setAttribute("movies",movies);
            req.getRequestDispatcher("/SearchMovies.jsp").forward(req, resp);

        }
    }
}
