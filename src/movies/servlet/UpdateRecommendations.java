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


@WebServlet("/updaterecommendations")
public class UpdateRecommendations extends HttpServlet {

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

        // Retrieve user and validate.
        String recommendationid = req.getParameter("recommendationid");

        if (recommendationid == null || recommendationid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid recommendationid.");
        } else {
            try {
                Recommendations recommendations = recommendationsDao.getRecommendationById(Integer.parseInt(recommendationid));
                System.out.println(recommendations);

                if(recommendations == null) {
                    messages.put("success", "recommendationid does not exist.");
                }
                req.setAttribute("recommendations", recommendations);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdateRecommendations.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String recommendationid = req.getParameter("recommendationid");
        System.out.println("getting recoommendationid");
        System.out.println(recommendationid);
        if (recommendationid == null || recommendationid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid recommendationid.");
        } else {
            try {
                Recommendations recommendations = recommendationsDao.getRecommendationById(Integer.parseInt(recommendationid));
                if(recommendations == null) {
                    messages.put("success", "MovieId does not exist. No update to perform.");
                } else {
                    String newusername = req.getParameter("username");
                    if (newusername == null || newusername.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid overview.");
                    } else {
                        recommendations = recommendationsDao.updateUsername(recommendations, newusername);
                        messages.put("success", "Successfully updated " + recommendationid);
                    }
                }
                req.setAttribute("recommendations", recommendations);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdateRecommendations.jsp").forward(req, resp);
    }
}
