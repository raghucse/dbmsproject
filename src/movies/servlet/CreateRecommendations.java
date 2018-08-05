package movies.servlet;


import movies.dal.MoviesDao;
import movies.dal.RecommendationsDao;
import movies.dal.TheatreDao;
import movies.dal.UsersDao;
import movies.model.Movies;
import movies.model.Recommendations;
import movies.model.Theatre;
import movies.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createrecommendations")
public class CreateRecommendations extends HttpServlet {

    protected RecommendationsDao recommendationsDao;

    @Override
    public void init() throws ServletException {
        recommendationsDao = RecommendationsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/CreateRecommendations.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String recommendationid = req.getParameter("recommendationid");
        if (recommendationid == null || recommendationid.trim().isEmpty()) {
            messages.put("success", "Invalid recommendationid");
        } else {
            // Create the BlogUser.
            String username = req.getParameter("username");
            String movieid = req.getParameter("movieid");
            UsersDao usersDao = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();

            try {

                Users user = usersDao.getUserFromUserName(username);
                Movies movies = moviesDao.getMovieById(Integer.parseInt(movieid));
                Recommendations recommendation = new Recommendations(Integer.parseInt(recommendationid), user, movies);
                recommendation = recommendationsDao.create(recommendation);
                messages.put("success", "Successfully created " + recommendationid);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CreateRecommmendations.jsp").forward(req, resp);
    }
}
