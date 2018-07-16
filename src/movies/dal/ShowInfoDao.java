package movies.dal;

import movies.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowInfoDao {
    // Single pattern: instantiation is limited to one object.
    private static ShowInfoDao instance = null;
    protected ConnectionManager connectionManager;

    protected ShowInfoDao() {
        connectionManager = new ConnectionManager();
    }

    public static ShowInfoDao getInstance() {
        if (instance == null) {
            instance = new ShowInfoDao();
        }
        return instance;
    }

    public ShowInfo create(ShowInfo showInfo) throws SQLException {
        String insertShowinfo =
                "INSERT INTO ShowInfo(TheatreId,MovieId, Price, ShowTime) " +
                        "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertShowinfo, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setInt(1, showInfo.getTheatre().getTheatreid());
            insertStmt.setInt(2,showInfo.getMovies().getMoviesId());
            insertStmt.setInt(3, showInfo.getPrice());
            insertStmt.setTimestamp(4, new Timestamp(showInfo.getShowtime().getTime()));
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if (resultKey.next()) {
                reviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            showInfo.setShowinfoid(reviewId);
            return showInfo;
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


    public ShowInfo delete(ShowInfo showInfo) throws SQLException {
        String deleteshowinfo = "DELETE FROM ShowInfo WHERE ShowInfoId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteshowinfo);
            deleteStmt.setInt(1, showInfo.getShowinfoid());
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


    public ShowInfo getShowInfoById(int showinfoid) throws SQLException {
        String selectshowinfo =
                "SELECT *" +
                        "FROM ShowInfo " +
                        "WHERE ShowInfoId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectshowinfo);
            selectStmt.setInt(1, showinfoid);
            results = selectStmt.executeQuery();
            TheatreDao theatreDao = TheatreDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            if (results.next()) {
                int resultsshowinfoid = results.getInt("ShowInfoId");
                int price = results.getInt("Price");
                int theatreid = results.getInt("TheatreId");
                int movieid = results.getInt("MovieId");
                Date showtime = results.getDate("ShowTime");
                Theatre theatre = theatreDao.getTheatreById(theatreid);
                Movies movies = moviesDao.getMovieById(movieid);
                ShowInfo showInfo = new ShowInfo(resultsshowinfoid,theatre,movies,price,showtime);
                return showInfo;
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
    public List<ShowInfo> getShowInfoByTheatreId(int theatreId) throws SQLException {
        List<ShowInfo> showInfos = new ArrayList<ShowInfo>();
        String selectReview =
                "SELECT *" +
                        "FROM ShowInfo " +
                        "WHERE TheatreId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, theatreId);
            results = selectStmt.executeQuery();
            TheatreDao theatreDao = TheatreDao.getInstance();
            MoviesDao moviesDao = MoviesDao.getInstance();
            while (results.next()) {
                int resultsshowinfoid = results.getInt("ShowInfoId");
                int price = results.getInt("Price");
                int theatreid = results.getInt("TheatreId");
                int movieid = results.getInt("MovieId");
                Date showtime = results.getDate("ShowTime");
                Theatre theatre = theatreDao.getTheatreById(theatreid);
                Movies movies = moviesDao.getMovieById(movieid);
                ShowInfo showInfo = new ShowInfo(resultsshowinfoid,theatre,movies,price,showtime);
                showInfos.add(showInfo);
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
        return showInfos;
    }


}
