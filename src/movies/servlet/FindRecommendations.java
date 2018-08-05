
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


@WebServlet("/findrecommendations")
public class FindRecommendations extends HttpServlet {

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
        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        try {
            recommendations = recommendationsDao.getAllRecommendations();
            System.out.println(recommendations);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("recommendations", recommendations);
        req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Recommendations> recommendations = new ArrayList<Recommendations>();

        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            // Retrieve BlogUsers, and store as a message.
            try {
                recommendations = recommendationsDao.getRecommendationsByUserName(username);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + username);
        }
        req.setAttribute("recommendations", recommendations);
        req.getRequestDispatcher("/FindRecommendations.jsp").forward(req, resp);
    }
}
