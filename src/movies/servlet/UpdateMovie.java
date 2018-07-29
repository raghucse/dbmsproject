package movies.servlet;

import movies.dal.*;
import movies.model.*;

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


@WebServlet("/updatemovie")
public class UpdateMovie extends HttpServlet {

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

        // Retrieve user and validate.
        String movieid = req.getParameter("movieid");

        if (movieid == null || movieid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid movie id.");
        } else {
            try {
                Movies movies = moviesDao.getMovieById(Integer.parseInt(movieid));
                System.out.println(movies);

                if(movies == null) {
                    messages.put("success", "MovieId does not exist.");
                }
                req.setAttribute("movies", movies);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdateMovies.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String movieId = req.getParameter("movieid");
        System.out.println("getting movie id");
        System.out.println(movieId);
        if (movieId == null || movieId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
            try {
                Movies movie = moviesDao.getMovieById(Integer.parseInt(movieId));
                if(movie == null) {
                    messages.put("success", "MovieId does not exist. No update to perform.");
                } else {
                    String newOverview = req.getParameter("overview");
                    if (newOverview == null || newOverview.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid overview.");
                    } else {
                        movie = moviesDao.updateOverview(movie, newOverview);
                        messages.put("success", "Successfully updated " + movieId);
                    }
                }
                req.setAttribute("movie", movie);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
