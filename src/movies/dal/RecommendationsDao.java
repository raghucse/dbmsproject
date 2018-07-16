package movies.dal;

import movies.dal.ConnectionManager;
import movies.dal.MoviesDao;
import movies.dal.UsersDao;
import movies.model.Movies;
import movies.model.Recommendations;
import movies.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author nikithanagaraj
 */
public class RecommendationsDao {
    private static RecommendationsDao instance = null;
    protected ConnectionManager connectionManager;

    protected RecommendationsDao() {
        connectionManager = new ConnectionManager();
    }

    public static RecommendationsDao getInstance() {
        if (instance == null) {
            instance = new RecommendationsDao();
        }
        return instance;
    }

    public Recommendations create(Recommendations recommendations) throws SQLException {
        String insertRecommendation =
                "INSERT INTO Recommendations(UserName,MovieId) " +
                        "VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, recommendations.getUsers().getUserName());
            insertStmt.setInt(2, recommendations.getMovies().getMoviesId());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int recommendationId = -1;
            if (resultKey.next()) {
                recommendationId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            recommendations.setRecommendationid(recommendationId);
            return recommendations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }

    /**
     * Delete the Reshares instance.
     * This runs a DELETE statement.
     */
    public Recommendations delete(Recommendations recommendations) throws SQLException {
        String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setInt(1, recommendations.getRecommendationid());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    /**
     * Get the Reshares record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Reshares instance.
     * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
     * BlogPosts and BlogUsers instances.
     * One alternative (possibly more efficient) is using a single SELECT statement
     * to join the Reshares, BlogPosts, BlogUsers tables and then build each object.
     */
    public Recommendations getRecommendationById(int recommendationId) throws SQLException {
        String selectRecommendation =
                "SELECT RecommendationId,UserName,RestaurantId " +
                        "FROM Recommendations " +
                        "WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setInt(1, recommendationId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            if (results.next()) {
                int resultRecommendationId = results.getInt("RecommendationId");
                String userName = results.getString("UserName");
                int movieid = results.getInt("MovieId");
                Users user = usersDao.getUserFromUserName(userName);
                Movies movies = moviesDao.getMovieById(movieid);
                Recommendations recommendation = new Recommendations(resultRecommendationId, user, movies);
                return recommendation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    /**
     * Get the all the Reshares for a user.
     */
    public List<Recommendations> getRecommendationsByMovieId(int movieId) throws SQLException {
        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        String selectRecommendation =
                "SELECT RecommendationId,UserName,RestaurantId " +
                        "FROM Recommendations " +
                        "WHERE MovieId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setInt(1, movieId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            while (results.next()) {
                int recommendationid = results.getInt("RecommendationId");
                String username = results.getString("UserName");
                int moviesid = results.getInt("MovieId");
                Users user = usersDao.getUserFromUserName(username);
                Movies movies = moviesDao.getMovieById(movieId);
                Recommendations recommendations1 = new Recommendations(recommendationid, user, movies);
                recommendations.add(recommendations1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return recommendations;
    }

    /**
     * Get the all the Reshares for a user.
     */
    public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException {
        List<Recommendations> recommendations = new ArrayList<Recommendations>();
        String selectRecommendation =
                "SELECT RecommendationId,UserName,RestaurantId " +
                        "FROM Recommendations " +
                        "WHERE MovieId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            while (results.next()) {
                int recommendationid = results.getInt("RecommendationId");
                String username = results.getString("UserName");
                int movieid = results.getInt("RestaurantId");
                Users user = usersDao.getUserFromUserName(username);
                Movies movies = moviesDao.getMovieById(movieid);
                Recommendations recommendation = new Recommendations(recommendationid, user, movies);
                recommendations.add(recommendation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return recommendations;
    }

}
