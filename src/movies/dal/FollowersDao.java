package movies.dal;

import movies.model.CreditCards;
import movies.model.Followers;
import movies.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowersDao {
    // Single pattern: instantiation is limited to one object.
    private static FollowersDao instance = null;
    protected ConnectionManager connectionManager;
    protected FollowersDao() {
        connectionManager = new ConnectionManager();
    }

    public static FollowersDao getInstance() {
        if (instance == null) {
            instance = new FollowersDao();
        }
        return instance;
    }

    public Followers create(Followers followers) throws SQLException {
        String insertfollowers = "INSERT INTO Followers(ID,UserName,FollowerName) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertfollowers);
            insertStmt.setInt(1, followers.getFollowerid());
            insertStmt.setString(2, followers.getUsers());
            insertStmt.setString(3, followers.getFollowername());
            insertStmt.executeUpdate();
            return followers;
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
    public Followers delete(Followers followers) throws SQLException {
        String deletefollower = "DELETE FROM Followers WHERE ID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletefollower);
            deleteStmt.setLong(1, followers.getFollowerid());
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

    public Followers getFollowersById(int followerid) throws SQLException {
        String selectfollower = "SELECT * FROM Followers WHERE ID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectfollower);
            selectStmt.setLong(1, followerid);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            if (results.next()) {
                int followerId = results.getInt("ID");
                String username = results.getString("UserName");
                String followername = results.getString("FollowerName");
                Users user = usersDao.getUserFromUserName(username);
                Users user2 = usersDao.getUserFromUserName(followername);
                Followers followers = new Followers(followerid, user, user2);
                return followers;
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

    public List<Followers> getFollowersByUserName(String username)
            throws SQLException {
        List<Followers> followers = new ArrayList<Followers>();
        String selectfollowers =
                "SELECT * from Followers WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectfollowers);
            selectStmt.setString(1, username);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            while (results.next()) {
                int followerid = results.getInt("ID");
                String user = results.getString("UserName");
                String followername = results.getString("FollowerName");
                Users user1 = usersDao.getUserFromUserName(username);
                Users user2 = usersDao.getUserFromUserName(followername);
                Followers follower = new Followers(followerid, user1, user2);
                followers.add(follower);
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
        return followers;
    }
}
