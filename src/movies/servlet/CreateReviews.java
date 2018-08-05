package movies.servlet;

import movies.dal.MoviesDao;
import movies.dal.UsersDao;
import movies.model.Movies;
import movies.model.Users;
import movies.model.Reviews;
import movies.dal.ReviewsDao;
import movies.servlet.Helper;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/createreviews")
public class CreateReviews extends HttpServlet {

    protected ReviewsDao reviewsDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/CreateReviews.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
//        String reviewId = req.getParameter("reviewid");
        String content = req.getParameter("content");
        double rating = Double.parseDouble(req.getParameter("rating"));
        String username = req.getParameter("username");
        int movieid = Integer.parseInt(req.getParameter("movieid"));

        if (content == null || content.trim().isEmpty() || rating > 5 || rating < 0 ) {
            messages.put("success", "Invalid Details");
        } else {
            // Create the BlogUser.


            try {
                // Exercise: parse the input for StatusLevel.
                UsersDao usersDao = UsersDao.getInstance();
                MoviesDao moviesDao = MoviesDao.getInstance();
                Users user = usersDao.getUserFromUserName(username);
                Movies movie = moviesDao.getMovieById(movieid);
                Reviews review = new Reviews(content, rating, user,movie);
                Reviews nReview = reviewsDao.create(review);
                messages.put("success", "Successfully created Review no. " + nReview.getReviewsid());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CreateReviews.jsp").forward(req, resp);
    }


}
