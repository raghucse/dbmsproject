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

@WebServlet("/deleterecommendations")
public class DeleteRecommendations extends HttpServlet {

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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Recommendation");
        req.getRequestDispatcher("/DeleteRecommendations.jsp").forward(req, resp);
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
            messages.put("title", "Invalid recommendationid");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the BlogUser.
            Recommendations recommendations = new Recommendations(Integer.parseInt(recommendationid));
            try {
                recommendations = recommendationsDao.delete(recommendations);
                // Update the message.
                if (recommendations == null) {
                    messages.put("title", "Successfully deleted " + recommendationid);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + recommendationid);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/DeleteRecommendations.jsp").forward(req, resp);
    }
}
