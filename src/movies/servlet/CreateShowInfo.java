package movies.servlet;


import movies.dal.MoviesDao;
import movies.dal.ShowInfoDao;
import movies.dal.TheatreDao;
import movies.model.Movies;
import movies.model.ShowInfo;
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


@WebServlet("/createshowinfo")
public class CreateShowInfo extends HttpServlet {

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
        //Just render the JSP.
        req.getRequestDispatcher("/CreateShowInfo.jsp").forward(req, resp);
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
            messages.put("success", "Invalid show info");
        } else {

            String price = req.getParameter("price");
            String theatreid = req.getParameter("theareid");
            String movieid = req.getParameter("movieid");
            String showtime = req.getParameter("showtime");
            TheatreDao theatreDao = TheatreDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date showtime2 = new Date();
            try {
                showtime2 = dateFormat.parse(showtime);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            try {
                // Exercise: parse the input for StatusLevel.
                Theatre theatre = theatreDao.getTheatreById(Integer.parseInt(theatreid));
                Movies movies = moviesDao.getMovieById(Integer.parseInt(movieid));
                ShowInfo showInfo = new ShowInfo(Integer.parseInt(showinfoid),theatre,movies,Integer.parseInt(price),showtime2);
                showInfo = showInfoDao.create(showInfo);
                messages.put("success", "Successfully created " + showinfoid);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CreateShowInfo.jsp").forward(req, resp);
    }
}
