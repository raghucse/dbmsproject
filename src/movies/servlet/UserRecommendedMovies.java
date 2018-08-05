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

@WebServlet("/usermovies")
public class UserRecommendedMovies extends HttpServlet{

    protected RecommendationsDao recommendationsDao;
    protected MoviesDao moviesDao;

    @Override
    public void init() throws ServletException {
        recommendationsDao = RecommendationsDao.getInstance();
        moviesDao = MoviesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
        String username = req.getParameter("username");

        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        List<Movies> moviesForGenreFiltered = new ArrayList<>();

        try {
            recommendations = recommendationsDao.getRecommendationsByUserName(username);
            System.out.println(recommendations);
            Map<String,Integer> genereMap= new HashMap<>();

            for (Recommendations item:recommendations) {
                if(genereMap.containsKey(item.getMovies().getGenre())){
                    genereMap.put(item.getMovies().getGenre(), genereMap.get(item.getMovies().getGenre())+1);
                }
                else
                {
                    genereMap.put(item.getMovies().getGenre(),0);
                }
            }
            Map<String, Integer> sortedGenre = Helper.sortByValue(genereMap);
            String mainGenre = "";
            for(Map.Entry<String, Integer> entry:sortedGenre.entrySet()){

                mainGenre =entry.getKey();
                System.out.println(mainGenre);

            }
            float highestRating = 0;
            for (Recommendations item:recommendations) {
                String currentGenre = item.getMovies().getGenre();
                if(currentGenre.equals(mainGenre)){
                    if(highestRating < item.getMovies().getAvg_rating()){
                        highestRating = item.getMovies().getAvg_rating();
                    }
                }
            }

            // fetching the movies based on the Genre and HighestRating
            List<Movies> moviesForGenre = new ArrayList<>();

            moviesForGenre = moviesDao.getMoviesByGenre(mainGenre);

            for (Movies m:moviesForGenre) {
                if(m.getAvg_rating() >= (highestRating-1)){
                    moviesForGenreFiltered.add(m);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.setAttribute("userRecommendedMovies", moviesForGenreFiltered);
        req.setAttribute("username", username);
        req.getRequestDispatcher("/UserRecommendedMovies.jsp").forward(req, resp);
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
