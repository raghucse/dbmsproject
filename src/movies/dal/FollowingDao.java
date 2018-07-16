package movies.dal;

import movies.model.Following;
import movies.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowingDao {
    // Single pattern: instantiation is limited to one object.
    private static FollowingDao instance = null;
    protected ConnectionManager connectionManager;

    protected FollowingDao() {
        connectionManager = new ConnectionManager();
    }

    public static FollowingDao getInstance() {
        if (instance == null) {
            instance = new FollowingDao();
        }
        return instance;
    }

    public Following create(Following following) throws SQLException {
        String insertfollowing = "INSERT INTO Following(ID,UserName,FollowingName) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertfollowing);
            insertStmt.setInt(1, following.getFollwingid());
            insertStmt.setString(2, following.getUsers());
            insertStmt.setString(3, following.getFollowingname());
            insertStmt.executeUpdate();
            return following;
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
     * Delete the BlogUsers instance.
     * This runs a DELETE statement.
     */
    public Following delete(Following following) throws SQLException {
        String deletefollowing = "DELETE FROM Following WHERE ID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletefollowing);
            deleteStmt.setLong(1, following.getFollwingid());
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

    public Following getFollowingById(int followingid) throws SQLException {
        String selectfollowing = "SELECT * FROM Following WHERE ID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectfollowing);
            selectStmt.setLong(1, followingid);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            if (results.next()) {
                int followingId = results.getInt("ID");
                String username = results.getString("UserName");
                String followingname = results.getString("FollowingName");
                Users user = usersDao.getUserFromUserName(username);
                Users user2 = usersDao.getUserFromUserName(followingname);
                Following following = new Following(followingId, user, user2);
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

    public List<Following> getFollowingByUserName(String username)
            throws SQLException {
        List<Following> following = new ArrayList<Following>();
        String selectfollowing =
                "SELECT * from Following WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectfollowing);
            selectStmt.setString(1, username);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            while (results.next()) {
                int followingid = results.getInt("ID");
                String user = results.getString("UserName");
                String followingname = results.getString("FollowingName");
                Users user1 = usersDao.getUserFromUserName(username);
                Users user2 = usersDao.getUserFromUserName(followingname);
                Following following1 = new Following(followingid, user1, user2);
                following.add(following1);
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
        return following;
    }
}

