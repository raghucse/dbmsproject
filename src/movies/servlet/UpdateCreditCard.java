package movies.servlet;

import movies.dal.CreditCardsDao;
import movies.dal.MoviesDao;
import movies.model.CreditCards;
import movies.model.Movies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/updatecreditcard")
public class UpdateCreditCard extends HttpServlet {

    protected CreditCardsDao creditCardsDao;

    @Override
    public void init() throws ServletException {
        creditCardsDao = creditCardsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String creditcardnumber = req.getParameter("creditcardnumber");

        if (creditcardnumber == null || creditcardnumber.trim().isEmpty()) {
            messages.put("success", "Please enter a credit card number");
        } else {
            try {
                CreditCards cards = creditCardsDao.getCreditCardByCardNumber(Integer.parseInt(creditcardnumber));
                System.out.println(cards);

                if(cards == null) {
                    messages.put("success", "cardnumber does not exist.");
                }
                req.setAttribute("creditcards", cards);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdateCreditCards.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String creditcardnumber = req.getParameter("creditcardnumber");
        if (creditcardnumber == null || creditcardnumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid creditcardnumber");
        } else {
            try {
                CreditCards cards = creditCardsDao.getCreditCardByCardNumber(Integer.parseInt(creditcardnumber));
                if(cards == null) {
                    messages.put("success", "card number does not exist. No update to perform.");
                } else {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String expiration1 = req.getParameter("expiration");
                    Date expiration = new Date();
                    try {
                        expiration = dateFormat.parse(expiration1);
                        cards = creditCardsDao.updateExpiration(cards, expiration);
                        messages.put("success", "Successfully updated " + cards.getCardnumber());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        messages.put("success", "Please enter a valid expiration.");
                        throw new IOException(e);
                    }


                }
                req.setAttribute("creditcardcard", cards);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UpdateCreditCards.jsp").forward(req, resp);
    }
}
