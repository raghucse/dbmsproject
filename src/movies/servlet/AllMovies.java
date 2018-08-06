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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/allmovies")
public class AllMovies extends HttpServlet {

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
        //List<Users> users = new ArrayList<Users>();
        List<Movies> movies = new ArrayList<Movies>();
        try {
            movies = moviesDao.getAllMovies();
            System.out.println(movies);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
//            messages.put("success", "Displaying results for " + );
//            // Save the previous search term, so it can be used as the default
//            // in the input box when rendering FindUsers.jsp.
        //  messages.put("previousFirstName", language);
        req.setAttribute("movies", movies);

        req.getRequestDispatcher("/AllMoviesUser.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Movies> movies = new ArrayList<Movies>();

        String language = req.getParameter("language");
        if (language == null || language.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            // Retrieve BlogUsers, and store as a message.
            try {
                movies = moviesDao.getMoviesByLanguage(language);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + language);
        }
        req.setAttribute("movies", movies);

        req.getRequestDispatcher("/AllMoviesUser.jsp").forward(req, resp);
    }
}
