package movies.dal;

import movies.model.Movies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoviesDao {
    // Single pattern: instantiation is limited to one object.
    private static MoviesDao instance = null;
    protected ConnectionManager connectionManager;

    protected MoviesDao() {
        connectionManager = new ConnectionManager();
    }

    public static MoviesDao getInstance() {
        if (instance == null) {
            instance = new MoviesDao();
        }
        return instance;
    }

    /**
     * Save the Persons instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Movies create(Movies movies) throws SQLException {
        String insertMovies = "INSERT INTO Movie(MovieName,Overview, ReleaseDate, Country, Language, Genre, Runtime,MovieId)" +
                " VALUES(?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertMovies);
            insertStmt.setString(1, movies.getMoviename());
            insertStmt.setString(2, movies.getOverview());
            insertStmt.setString(3, movies.getReleasedate());
            insertStmt.setString(4, movies.getCountry());
            insertStmt.setString(5, movies.getLanguage());
            insertStmt.setString(6, movies.getGenre());
            insertStmt.setInt(7, movies.getRuntime());
            insertStmt.setInt(8, movies.getMoviesId());
            insertStmt.executeUpdate();
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }


    /**
     * Delete the Persons instance.
     * This runs a DELETE statement.
     */
    public Movies delete(Movies movies) throws SQLException {
        String deleteMovie = "DELETE FROM Movie WHERE MovieId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteMovie);
            deleteStmt.setInt(1, movies.getMoviesId());
            deleteStmt.executeUpdate();
            // Return null so the caller can no longer operate on the Persons instance.
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
     * Get the Persons record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Persons instance.
     */
    public Movies getMovieById(int movieId) throws SQLException {
        String selectRestaurant = "SELECT * FROM Movie WHERE MovieId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurant);
            selectStmt.setInt(1, movieId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int movieid= results.getInt("MovieId");
                String name = results.getString("MovieName");
                String overview = results.getString("Overview");
                String releasedate = results.getString("ReleaseDate");
                String country = results.getString("Country");
                String language = results.getString("Language");
                String genre = results.getString("Genre");
                int runtime = results.getInt("Runtime");
                Movies movies = new Movies(movieid,name,overview,releasedate,country,language,genre,runtime);
                return movies;
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

    public List<Movies> getMoviesByCountry(Movies movies) throws SQLException {
        List<Movies> movies1 = new ArrayList<Movies>();
        String selectmovies =
                "SELECT * FROM Movie WHERE Country=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectmovies);
            selectStmt.setString(1, movies.getCountry());
            results = selectStmt.executeQuery();
            while (results.next()) {
                int movieid= results.getInt("MovieId");
                String name = results.getString("MovieName");
                String overview = results.getString("Overview");
                String releasedate = results.getString("ReleaseDate");
                String country = results.getString("Country");
                String language = results.getString("Language");
                String genre = results.getString("Genre");
                int runtime = results.getInt("Runtime");
                Movies movie = new Movies(movieid,name,overview,releasedate,country,language,genre,runtime);
                movies1.add(movie);
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
        return movies1;
    }

    public List<Movies> getMoviesByLanguage(Movies movies) throws SQLException {
        List<Movies> movies1 = new ArrayList<Movies>();
        String selectmovies = "SELECT * FROM Movie WHERE Language=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectmovies);
            selectStmt.setString(1, movies.getLanguage());
            results = selectStmt.executeQuery();
            while (results.next()) {
                int movieid= results.getInt("MovieId");
                String name = results.getString("MovieName");
                String overview = results.getString("Overview");
                String releasedate = results.getString("ReleaseDate");
                String country = results.getString("Country");
                String language = results.getString("Language");
                String genre = results.getString("Genre");
                int runtime = results.getInt("Runtime");
                Movies movie = new Movies(movieid,name,overview,releasedate,country,language,genre,runtime);
                movies1.add(movie);
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
        return movies1;
    }
}
