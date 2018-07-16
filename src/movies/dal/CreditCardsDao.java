package movies.dal;

import movies.dal.ConnectionManager;
import movies.model.CreditCards;
import movies.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * @author nikithanagaraj
 */

public class CreditCardsDao {
    // Single pattern: instantiation is limited to one object.
    private static CreditCardsDao instance = null;
    protected ConnectionManager connectionManager;

    protected CreditCardsDao() {
        connectionManager = new ConnectionManager();
    }

    public static CreditCardsDao getInstance() {
        if (instance == null) {
            instance = new CreditCardsDao();
        }
        return instance;
    }

    public CreditCards create(CreditCards creditCards) throws SQLException {
        String insertCreditcard = "INSERT INTO CreditCards(CardNumber,Expiration,UserName) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCreditcard);
            insertStmt.setLong(1, creditCards.getCardnumber());
            insertStmt.setTimestamp(2, new Timestamp(creditCards.getDate().getTime()));
            insertStmt.setString(3, creditCards.getUsers().getUserName());
            insertStmt.executeUpdate();
            return creditCards;
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
    public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
        String updateCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCard);

            updateStmt.setDate(1, newExpiration);
            updateStmt.setLong(2, creditCard.getCardnumber());
            updateStmt.executeUpdate();

            // Update the person param before returning to the caller.
            creditCard.setDate(newExpiration);
            return creditCard;
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
    public CreditCards delete(CreditCards creditCards) throws SQLException {
        String deleteCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCard);
            deleteStmt.setLong(1, creditCards.getCardnumber());
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

    public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException {
        String selectCard = "SELECT CardNumber,Expiration,UserName FROM CreditCards WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCard);
            selectStmt.setLong(1, cardNumber);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            if (results.next()) {
                Long resultCardNumber = results.getLong("CardNumber");
                Date expiration = results.getDate("Expiration");
                String username = results.getString("UserName");
                Users user = usersDao.getUserFromUserName(username);
                CreditCards creditCards = new CreditCards(resultCardNumber, expiration, user);
                return creditCards;
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

    public List<CreditCards> getCreditCardsByUserName(String user)
            throws SQLException {
        List<CreditCards> creditCards = new ArrayList<CreditCards>();
        String selectCreditcards =
                "SELECT CreditCards.UserName AS UserName,Expiration,CardNumber " + "" +
                        "FROM CreditCards INNER JOIN Users" +
                        " ON CreditCards.UserName = Users.UserName WHERE CreditCards.UserName = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCreditcards);
            selectStmt.setString(1, user);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            while (results.next()) {
                String resultuserName = results.getString("UserName");
                long cardnumber = results.getLong("CardNumber");
                Date expiration = results.getDate("Expiration");
                Users username = usersDao.getUserFromUserName(resultuserName);
                CreditCards blogUser = new CreditCards(cardnumber, expiration, username);
                creditCards.add(blogUser);
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
        return creditCards;
    }
}
