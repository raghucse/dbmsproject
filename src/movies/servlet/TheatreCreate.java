package movies.servlet;


import movies.dal.TheatreDao;
import movies.model.Theatre;

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


@WebServlet("/theatrecreate")
public class TheatreCreate extends HttpServlet {

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
        //Just render the JSP.
        req.getRequestDispatcher("/TheatreCreate.jsp").forward(req, resp);
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
            messages.put("success", "Invalid UserName");
        } else {
            // Create the BlogUser.
            String theatrename = req.getParameter("theatrename");
            String location = req.getParameter("location");
            try {
                // Exercise: parse the input for StatusLevel.
                Theatre theatre = new Theatre(Integer.parseInt(theatreid), theatrename, location, Theatre.TheatreType.STANDARD);
                theatre = theatreDao.create(theatre);
                messages.put("success", "Successfully created " + theatre);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/TheatreCreate.jsp").forward(req, resp);
    }
}
