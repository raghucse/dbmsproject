package movies.dal;

import movies.model.Movies;
import movies.model.ShowInfo;
import movies.model.Theatre;
import movies.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
                float avg_rating = results.getFloat("AverageRating");
                Movies movies = new Movies(movieid,name,overview,releasedate,country,language,genre,runtime, avg_rating);
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

    public List<Movies> getMovieByName(String moviename) throws SQLException {
        List<Movies> moviesList = new ArrayList<>();
        String selectmovies = "SELECT * FROM Movie WHERE MovieName = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectmovies);
            selectStmt.setString(1, moviename);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int movieid = results.getInt("MovieId");
                String name = results.getString("MovieName");
                String overview = results.getString("Overview");
                String releasedate = results.getString("ReleaseDate");
                String country = results.getString("Country");
                String language = results.getString("Language");
                String genre = results.getString("Genre");
                int runtime = results.getInt("Runtime");

                float avg_rating = results.getFloat("AverageRating");
                Movies movie = new Movies(movieid, name, overview, releasedate, country, language, genre, runtime, avg_rating);
                moviesList.add(movie);
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
        return moviesList;
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

                float avg_rating = results.getFloat("AverageRating");
                Movies movie = new Movies(movieid,name,overview,releasedate,country,language,genre,runtime, avg_rating);
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

    public List<Movies> getMoviesByGenre(String genre) throws SQLException{
        List<Movies> movies1 = new ArrayList<Movies>();
        String selectmovies = "SELECT * FROM Movie WHERE Genre=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectmovies);
            selectStmt.setString(1, genre);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int movieid= results.getInt("MovieId");
                String name = results.getString("MovieName");
                String overview = results.getString("Overview");
                String releasedate = results.getString("ReleaseDate");
                String country = results.getString("Country");
                String language2 = results.getString("Language");
                String genre1 = results.getString("Genre");
                int runtime = results.getInt("Runtime");
                float avg_rating = results.getFloat("AverageRating");
                Movies movie = new Movies(movieid,name,overview,releasedate,country,language2,genre1,runtime, avg_rating);
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

    public List<Movies> getMoviesByLanguage(String language) throws SQLException {
        List<Movies> movies1 = new ArrayList<Movies>();
        String selectmovies = "SELECT * FROM Movie WHERE Language=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectmovies);
            selectStmt.setString(1, language);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int movieid= results.getInt("MovieId");
                String name = results.getString("MovieName");
                String overview = results.getString("Overview");
                String releasedate = results.getString("ReleaseDate");
                String country = results.getString("Country");
                String language2 = results.getString("Language");
                String genre = results.getString("Genre");
                int runtime = results.getInt("Runtime");
                float avg_rating = results.getFloat("AverageRating");
                Movies movie = new Movies(movieid,name,overview,releasedate,country,language,genre,runtime, avg_rating);
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
    public Movies updateOverview(Movies movie, String overview) throws SQLException {
        String updateMovie = "UPDATE Movie SET Overview=? WHERE MovieId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateMovie);
            updateStmt.setString(1, overview);
            updateStmt.setInt(2, movie.getMoviesId());
            updateStmt.executeUpdate();

            // Update the person param before returning to the caller.
            movie.setOverview(overview);
            return movie;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }
    public List<Movies> getAllMovies() throws SQLException {
        List<Movies> movies = new ArrayList<Movies>();
        String selectmovies = "SELECT * FROM Movie";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectmovies);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int movieid= results.getInt("MovieId");
                String name = results.getString("MovieName");
                String overview = results.getString("Overview");
                String releasedate = results.getString("ReleaseDate");
                String country = results.getString("Country");
                String language2 = results.getString("Language");
                String genre = results.getString("Genre");
                int runtime = results.getInt("Runtime");
                float avg_rating = results.getFloat("AverageRating");
                Movies movie1 = new Movies(movieid,name,overview,releasedate,country,language2,genre,runtime, avg_rating);
                movies.add(movie1);
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
        return movies;
    }

}
