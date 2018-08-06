package movies.servlet;

import movies.dal.MoviesDao;
import movies.dal.ShowInfoDao;
import movies.dal.TheatreDao;
import movies.model.ShowInfo;
import movies.model.Theatre;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/fetchshowtime")
public class FetchShowtime extends HttpServlet {

    protected ShowInfoDao showInfoDao;

    @Override
    public void init() throws ServletException {
        showInfoDao = ShowInfoDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        System.out.println("inside get");
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<ShowInfo> showInfos = new ArrayList<ShowInfo>();
        int movieid = Integer.parseInt(req.getParameter("movieid"));
        try{
            showInfos = showInfoDao.getShowInfoByMovie(movieid);
//            moviesDao
            System.out.println(showInfos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("showinfo", showInfos);
        req.getRequestDispatcher("/FetchShowtime.jsp").forward(req, resp);
    }




}
