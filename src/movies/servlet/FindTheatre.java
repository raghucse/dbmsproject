
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


@WebServlet("/findtheatre")
public class FindTheatre extends HttpServlet {

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
        List<Theatre> theatres = new ArrayList<Theatre>();
            try {
                theatres = theatreDao.getAllTheatres();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        req.setAttribute("theatres", theatres);
        req.getRequestDispatcher("/FindTheatre.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Theatre> theatres = new ArrayList<Theatre>();
        String location = req.getParameter("location");
        if (location == null || location.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            // Retrieve BlogUsers, and store as a message.
            try {
                theatres = theatreDao.getTheatreByLocation(location);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + location);
        }
        req.setAttribute("theatres", theatres);
        req.getRequestDispatcher("/FindTheatre.jsp").forward(req, resp);
    }
}
