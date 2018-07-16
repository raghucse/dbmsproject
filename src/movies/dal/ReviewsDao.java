package movies.dal;

import movies.model.Movies;
import movies.model.Reviews;
import movies.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author nikithanagaraj
 */
public class ReviewsDao {
    private static ReviewsDao instance = null;
    protected ConnectionManager connectionManager;

    protected ReviewsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ReviewsDao getInstance() {
        if (instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    public Reviews create(Reviews reviews) throws SQLException {
        String insertReview =
                "INSERT INTO Reviews(CreatedTime,Content,Rating,UserName,UserName) " +
                        "VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, new Timestamp(reviews.getCreated().getTime()));
            insertStmt.setString(2, reviews.getContent());
            insertStmt.setDouble(3, reviews.getRating());
            insertStmt.setString(4, reviews.getUsers().getUserName());
            insertStmt.setInt(5, reviews.getMovies().getMoviesId());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if (resultKey.next()) {
                reviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            reviews.setReviewsid(reviewId);
            return reviews;
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
    public Reviews delete(Reviews reviews) throws SQLException {
        String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, reviews.getReviewsid());
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
    public Reviews getReviewById(int reviewId) throws SQLException {
        String selectReview =
                "SELECT *" +
                        "FROM Reviews " +
                        "WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            if (results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                String userName = results.getString("UserName");
                int movieid = results.getInt("MovieId");
                Date created = new Date(results.getTimestamp("CreatedTime").getTime());
                String content = results.getString("Content");
                double rating = results.getDouble("Rating");

                Users user = usersDao.getUserFromUserName(userName);
                Movies movies = moviesDao.getMovieById(movieid);
                Reviews review = new Reviews(reviewId, created, content, rating, user, movies);
                return review;
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
    public List<Reviews> getReviewsByMovieId(int movieid) throws SQLException {
        List<Reviews> reshares = new ArrayList<Reviews>();
        String selectReview =
                "SELECT *" +
                        "FROM Reviews " +
                        "WHERE MovieId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, movieid);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            while (results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                String userName = results.getString("UserName");
                int movieId = results.getInt("MovieId");
                Date created = new Date(results.getTimestamp("CreatedTime").getTime());
                String content = results.getString("Content");
                double rating = results.getDouble("Rating");
                Users user = usersDao.getUserFromUserName(userName);
                Movies movies = moviesDao.getMovieById(movieid);
                Reviews review = new Reviews(movieId, created, content, rating, user, movies);
                reshares.add(review);
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
        return reshares;
    }

    /**
     * Get the all the Reshares for a user.
     */
    public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
        List<Reviews> reviews = new ArrayList<Reviews>();
        String selectReview =
                "SELECT * " +
                        "FROM Reviews " +
                        "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            UsersDao users = UsersDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            while (results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                String username = results.getString("UserName");
                int movieId = results.getInt("MovieId");
                Date created = new Date(results.getTimestamp("CreatedTime").getTime());
                String content = results.getString("Content");
                double rating = results.getDouble("Rating");
                Users user = users.getUserFromUserName(username);
                Movies movies = moviesDao.getMovieById(movieId);
                Reviews review = new Reviews(movieId, created, content, rating, user, movies);
                reviews.add(review);
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
        return reviews;
    }
}
