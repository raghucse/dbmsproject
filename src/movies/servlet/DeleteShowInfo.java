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


@WebServlet("/deleteshowinfo")
public class DeleteShowInfo extends HttpServlet {

    protected ShowInfoDao showInfoDao;

    @Override
    public void init() throws ServletException {
        showInfoDao = ShowInfoDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete User");
        req.getRequestDispatcher("/DeleteShowInfo.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String showinfoid = req.getParameter("showinfoid");
        if (showinfoid == null || showinfoid.trim().isEmpty()) {
            messages.put("title", "Invalid movieid");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the BlogUser.
            ShowInfo showInfo = new ShowInfo(Integer.parseInt(showinfoid));
            try {
                showInfo = showInfoDao.delete(showInfo);
                // Update the message.
                if (showInfo == null) {
                    messages.put("title", "Successfully deleted " + showinfoid);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + showinfoid);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/DeleteShowInfo.jsp").forward(req, resp);
    }
}
