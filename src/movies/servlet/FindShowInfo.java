
package movies.servlet;

import movies.dal.*;
import movies.model.*;

import java.awt.*;
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


@WebServlet("/findshowinfo")
public class FindShowInfo extends HttpServlet {

    protected ShowInfoDao showInfoDao;
    protected MoviesDao moviesDao;

    @Override
    public void init() throws ServletException {
        showInfoDao = ShowInfoDao.getInstance();
        moviesDao = MoviesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        System.out.println("inside get");
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<ShowInfo> showInfos = new ArrayList<ShowInfo>();
        try{
            showInfos = showInfoDao.getAllShowInfo();
//            moviesDao
            System.out.println(showInfos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("showinfo", showInfos);
        req.getRequestDispatcher("/FindShowInfo.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        // Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//        List<ShowInfo> showInfos = new ArrayList<ShowInfo>();
//
//        if (language == null || language.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid name.");
//        } else {
//            // Retrieve BlogUsers, and store as a message.
//            try {
//                movies = moviesDao.getMoviesByLanguage(language);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new IOException(e);
//            }
//            messages.put("success", "Displaying results for " + language);
//        }
//        req.setAttribute("movies", movies);
//
//        req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
    }
}
