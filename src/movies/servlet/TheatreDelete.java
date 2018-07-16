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


@WebServlet("/theatredelete")
public class TheatreDelete extends HttpServlet {

    protected TheatreDao theatreDao;

    @Override
    public void init() throws ServletException {
        theatreDao = TheatreDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete User");
        req.getRequestDispatcher("/TheateDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String theatreid = req.getParameter("theatreid");
        if (theatreid == null || theatreid.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the BlogUser.
            Theatre theatre = new Theatre(Integer.parseInt(theatreid));
            try {
                theatre = theatreDao.delete(theatre);
                // Update the message.
                if (theatre == null) {
                    messages.put("title", "Successfully deleted " + theatreid);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + theatreid);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/TheatreDelete.jsp").forward(req, resp);
    }
}
