package movies.servlet;


import movies.dal.ReviewsDao;
import movies.model.Recommendations;
import movies.model.Reviews;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/fetchreview")
public class FetchReview extends HttpServlet {
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
        Reviews reviews = null;
        String username = Helper.getUsernameFromCookie(req);
        int movieid = Integer.parseInt(req.getParameter("movieid"));
        try {
            reviews = reviewsDao.getReviewsByUserNameAndMovieId(username,movieid);
            System.out.println(reviews);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/FetchReview.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Reviews> reviews = new ArrayList<Reviews>();

        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            // Retrieve BlogUsers, and store as a message.
            try {
                reviews = reviewsDao.getReviewsByUserName(username);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + username);
        }
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/FetchReview.jsp").forward(req, resp);
    }
}
