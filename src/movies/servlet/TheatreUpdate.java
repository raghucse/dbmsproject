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


@WebServlet("/theatreupdate")
public class TheatreUpdate extends HttpServlet {

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

        // Retrieve user and validate.
        String theatreid = req.getParameter("theatreid");
        if (theatreid == null || theatreid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
            try {
                Theatre theatre = theatreDao.getTheatreById(Integer.parseInt(theatreid));
                if(theatre == null) {
                    messages.put("success", "UserName does not exist.");
                }
                req.setAttribute("theatre", theatre);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/TheatreUpdate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String theatreid = req.getParameter("theatreid");
        if (theatreid == null || theatreid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
            try {
                Theatre theatre = theatreDao.getTheatreById(Integer.parseInt(theatreid));
                if(theatre == null) {
                    messages.put("success", "UserName does not exist. No update to perform.");
                } else {
                    String newlocation = req.getParameter("location");
                    if (newlocation == null || newlocation.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid LastName.");
                    } else {
                        theatre = theatreDao.updateTheatreLocation(theatre,newlocation);
                        messages.put("success", "Successfully updated " + theatreid);
                    }
                }
                req.setAttribute("theatre", theatre);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/TheatreUpdate.jsp").forward(req, resp);
    }
}
