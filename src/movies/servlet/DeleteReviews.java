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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/deletereviews")
public class DeleteReviews extends HttpServlet {

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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Review");
        req.getRequestDispatcher("/DeleteReviews.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reviewid = req.getParameter("reviewid");
        if (reviewid == null || reviewid.trim().isEmpty()) {
            messages.put("title", "Invalid reviewid");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the BlogUser.
            Reviews reviews = new Reviews(Integer.parseInt(reviewid));
            try {
                reviews = reviewsDao.delete(reviews);
                // Update the message.
                if (reviews == null) {
                    messages.put("title", "Successfully deleted " + reviewid);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + reviewid);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/DeleteReviews.jsp").forward(req, resp);
    }
}
