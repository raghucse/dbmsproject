package movies.dal;

import movies.dal.ConnectionManager;
import movies.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author nikithanagaraj
 */
public class UsersDao {
    // Single pattern: instantiation is limited to one object.
    private static UsersDao instance = null;
    protected ConnectionManager connectionManager;

    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }

    public static UsersDao getInstance() {
        if (instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    public Users create(Users users) throws SQLException {
        // Insert into the superclass table first.

        String insertUser = "INSERT INTO User(UserName,Password,FirstName,LastName,Email,PhoneNumber) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);
            insertStmt.setString(1, users.getUserName());
            insertStmt.setString(2, users.getPassword());
            insertStmt.setString(3, users.getFirstName());
            insertStmt.setString(4, users.getLastName());
            insertStmt.setString(5, users.getEmail());
            insertStmt.setString(6, users.getPhone());
            insertStmt.executeUpdate();
            return users;
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
        }
    }

    /**
     * Update the LastName of the BlogUsers instance.
     * This runs a UPDATE statement.
     */
    public Users updateLastName(Users users, String newLastName) throws SQLException {
        String updateUser = "UPDATE User SET LastName=? WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateUser);
            updateStmt.setString(1, newLastName);
            updateStmt.setString(2, users.getUserName());
            updateStmt.executeUpdate();

            // Update the person param before returning to the caller.
            users.setLastName(newLastName);
            return users;
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
     * Delete the BlogUsers instance.
     * This runs a DELETE statement.
     */
    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM User WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setString(1, user.getUserName());
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

    public Users getUserFromUserName(String userName) throws SQLException {
        String selectUser = "SELECT UserName,Password,FirstName,LastName,Email,PhoneNumber FROM User WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultUserName = results.getString("UserName");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String password = results.getString("Password");
                String email = results.getString("Email");
                String phone = results.getString("PhoneNumber");
                Users user = new Users(resultUserName, firstName, lastName, password, email, phone);
                return user;
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

    public List<Users> getAllUsers() throws SQLException {
        List<Users> users = new ArrayList<Users>();
        String selectusers = "SELECT * FROM User";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        UsersDao usersDao = UsersDao.getInstance();
        MoviesDao moviesDao = MoviesDao.getInstance();
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectusers);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String resultUserName = results.getString("UserName");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String password = results.getString("Password");
                String email = results.getString("Email");
                String phone = results.getString("PhoneNumber");
                Users user = new Users(resultUserName, firstName, lastName, password, email, phone);
                users.add(user);
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
        return users;
    }
}
