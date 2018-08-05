package movies.servlet;


import movies.dal.ReviewsDao;
import movies.model.Reviews;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/editreview")
public class EditReview extends HttpServlet {

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

        // Retrieve user and validate.
        String reviewid = req.getParameter("reviewid");

        if (reviewid == null || reviewid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid reviewid.");
        } else {
            try {

                System.out.println(reviewid);
                Reviews reviews = reviewsDao.getReviewById(Integer.parseInt(reviewid));

                if(reviews == null) {
                    messages.put("success", "review does not exist.");
                }
                req.setAttribute("reviews", reviews);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/EditReview.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String reviewid = req.getParameter("reviewid");
        System.out.println("getting reviewId");
        System.out.println(reviewid);
        if (reviewid == null || reviewid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid reviewid.");
        } else {
            try {
                Reviews reviews = reviewsDao.getReviewById(Integer.parseInt(reviewid));
                if(reviews == null) {
                    messages.put("success", "Review does not exist. No update to perform.");
                } else {

                    String content = req.getParameter("content");
                    double rating = Double.parseDouble(req.getParameter("rating"));

                    if (content == null || content.trim().isEmpty() || rating > 5 || rating < 0) {
                        messages.put("success", "Please enter valid details.");
                    } else {
                        reviews = reviewsDao.updateReview(reviews, content,rating);
                        messages.put("success", "Successfully updated " + reviewid);
                    }
                }
                req.setAttribute("reviews", reviews);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/EditReview.jsp").forward(req, resp);
    }
}
