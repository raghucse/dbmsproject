package movies.dal;

import movies.model.Theatre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheatreDao {
    // Single pattern: instantiation is limited to one object.
    private static TheatreDao instance = null;
    protected ConnectionManager connectionManager;

    protected TheatreDao() {
        connectionManager = new ConnectionManager();
    }

    public static TheatreDao getInstance() {
        if (instance == null) {
            instance = new TheatreDao();
        }
        return instance;
    }

    /**
     * Save the Persons instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Theatre create(Theatre theatre) throws SQLException {
        String insertTheatre = "INSERT INTO INSERT INTO Theatre(TheatreId,TheatreName,Location,TheatreType)" +
                " VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTheatre);
            insertStmt.setInt(1, theatre.getTheatreid());
            insertStmt.setString(2, theatre.getTheatrename());
            insertStmt.setString(3, theatre.getLocation());
            insertStmt.setString(4, theatre.getTheatreType().name());
            insertStmt.executeUpdate();
            return theatre;
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
    public Theatre delete(Theatre theatre) throws SQLException {
        String deleteTheatre = "DELETE FROM Theatre WHERE TheatreId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteTheatre);
            deleteStmt.setInt(1, theatre.getTheatreid());
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
     * Delete the Persons instance.
     * This runs a DELETE statement.
     */
    public Theatre updateTheatreLocation(Theatre theatre,String newlocation) throws SQLException {
        String updatethreatre = "UPDATE Theatre SET Location=? WHERE TheatreId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatethreatre);
            updateStmt.setString(1, newlocation);
            updateStmt.setInt(2, theatre.getTheatreid());
            updateStmt.executeUpdate();

            theatre.setLocation(newlocation);
            return theatre;
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

    /**
     * Get the Persons record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Persons instance.
     */
    public Theatre getTheatreById(int theatreId) throws SQLException {
        String selectTheatre = "SELECT * FROM Theatre WHERE TheatreId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTheatre);
            selectStmt.setInt(1, theatreId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int theatreid = results.getInt("TheatreId");
                String name = results.getString("TheatreName");
                String location = results.getString("Location");
                Theatre.TheatreType theatreType = Theatre.TheatreType.valueOf(results.getString("TheatreType"));
                Theatre theatre = new Theatre(theatreid, name, location, theatreType);
                return theatre;
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
     * Get the matching Persons records by fetching from your MySQL instance.
     * This runs a SELECT statement and returns a list of matching Persons.
     */
    public List<Theatre> getTheatreByType(Theatre.TheatreType theatreType) throws SQLException {
        List<Theatre> theatres = new ArrayList<Theatre>();
        String selectRestaurants =
                "SELECT * FROM Theatre WHERE TheatreType=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setString(1, theatreType.name());
            results = selectStmt.executeQuery();
            while (results.next()) {
                int theatreid = results.getInt("TheatreId");
                String name = results.getString("TheatreName");
                String location = results.getString("Location");
                Theatre.TheatreType Type = Theatre.TheatreType.valueOf(results.getString("TheatreType"));
                Theatre theatre = new Theatre(theatreid, name, location, Type);
                theatres.add(theatre);
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
        return theatres;
    }

    public List<Theatre> getTheatreByLocation(String location) throws SQLException {
        List<Theatre> theatres = new ArrayList<Theatre>();
        String selectRestaurants =
                "SELECT * FROM Theatre WHERE Location=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setString(1, location);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int theatreid = results.getInt("TheatreId");
                String name = results.getString("TheatreName");
                String location1 = results.getString("Location");
                Theatre.TheatreType theatreType = Theatre.TheatreType.valueOf(results.getString("TheatreType"));
                Theatre theatre = new Theatre(theatreid, name, location1, theatreType);
                theatres.add(theatre);
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
        return theatres;
    }
}
