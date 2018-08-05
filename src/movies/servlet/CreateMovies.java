package movies.servlet;


import movies.dal.MoviesDao;
import movies.dal.TheatreDao;
import movies.model.Movies;
import movies.model.Theatre;

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


@WebServlet("/createmovies")
public class CreateMovies extends HttpServlet {

    protected MoviesDao moviesDao;

    @Override
    public void init() throws ServletException {
        moviesDao = MoviesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/CreateMovies.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String movieid = req.getParameter("movieid");
        if (movieid == null || movieid.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            // Create the BlogUser.

            String moviename = req.getParameter("moviename");
            String overview = req.getParameter("overview");
            String releasedate = req.getParameter("releasedate");
            String country = req.getParameter("country");
            String language = req.getParameter("language");
            String genre = req.getParameter("genre");
            String runtime = req.getParameter("runtime");

            try {
                // Exercise: parse the input for StatusLevel.
                Movies movies = new Movies(Integer.parseInt(movieid), moviename,overview,releasedate,country,language,genre,Integer.parseInt(runtime));
                movies = moviesDao.create(movies);
                messages.put("success", "Successfully created " + movieid);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CreateMovies.jsp").forward(req, resp);
    }
}
