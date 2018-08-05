
package movies.servlet;

import movies.dal.*;
import movies.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/allmoviesuser")
public class AllMoviesUser extends HttpServlet{
    protected MoviesDao moviesDao;
    protected RecommendationsDao recommendationsDao;

    @Override
    public void init() throws ServletException {
        moviesDao = MoviesDao.getInstance();
        recommendationsDao = RecommendationsDao.getInstance();
    }

    public void recommend(HttpServletRequest req, HttpServletResponse resp, Integer movieid, String username){
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Create the BlogUser.
//        String username = req.getParameter("username");
//        String movieid = req.getParameter("movieid");
        UsersDao usersDao = UsersDao.getInstance();
        MoviesDao moviesDao = MoviesDao.getInstance();

        try {

            Users user = usersDao.getUserFromUserName(username);
            Movies movies = moviesDao.getMovieById(movieid);
            Recommendations recommendation = new Recommendations(user, movies);
            recommendation = recommendationsDao.create(recommendation);
            messages.put("success", "Successfully Recommended " + movies.getMoviename() + " by "+ user.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new IOException(e);
            messages.put("Warning", "AlreadyExist" + e.toString());

        }

//        req.getRequestDispatcher("/CreateRecommmendations.jsp").forward(req, resp);
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String movieid = req.getParameter("movieid");

        String username = Helper.getUsernameFromCookie(req);


        if (movieid != null && username!=null){
            recommend(req, resp, Integer.parseInt(movieid), username);
//            req.getRequestDispatcher("/AllMoviesUser.jsp").forward(req, resp);
//            return;
        }

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

//    public void doalert(checkboxElem) {
//        if (checkboxElem.checked) {
//            alert("checked")
//        } else {
//            alert("notchecked")
//        }
//    }



    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getParameter("button1") != null) {
//
        }
//        // Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//        List<Movies> movies = new ArrayList<Movies>();
//
//        String language = req.getParameter("language");
//        if (language == null || language.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid name.");
//        } else {
//            // Retrieve BlogUsers, and store as a message.
//            try {
//                movies = moviesDao.getMoviesByLanguage(language);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new IOException(e);
//            }
//            messages.put("success", "Displaying results for " + language);
//        }
//        req.setAttribute("movies", movies);
//
//        req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
    }

//    public void recommend(HttpServletRequest req){
//        StringBuffer jb = new StringBuffer();
//        String line = null;
//        try {
//            BufferedReader reader = req.getReader();
//            while ((line = reader.readLine()) != null)
//                jb.append(line);
//        } catch (Exception e) { /*report an error*/ }
//
//        try {
//            Object jsonObject =  HTTP.toJSONObject(jb.toString());
//        } catch (Exception e) {
//            // crash and burn
//            throw new IOException("Error parsing JSON request string");
//        }
//    }



}
