package movies.servlet;


import movies.dal.CreditCardsDao;
import movies.dal.TheatreDao;
import movies.dal.UsersDao;
import movies.model.CreditCards;
import movies.model.Theatre;
import movies.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createcreditcard")
public class CreateCreditCard extends HttpServlet {

    protected CreditCardsDao creditCardsDao;
    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        creditCardsDao = CreditCardsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/CreateCreditCard.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String cardnumber = req.getParameter("cardnumber");
        if (cardnumber == null || cardnumber.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            // Create the BlogUser.
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringDob = req.getParameter("expiration");
            Date expiration = new Date();
            try {
                expiration = dateFormat.parse(stringDob);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new IOException(e);
            }

            String username = req.getParameter("username");
            try {
                // Exercise: parse the input for StatusLevel.
                Users user = usersDao.getUserFromUserName(username);
                CreditCards creditCards = new CreditCards(Integer.parseInt(cardnumber), expiration, user);
                creditCards = creditCardsDao.create(creditCards);
                messages.put("success", "Successfully created " + creditCards);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/CreateCreditCard.jsp").forward(req, resp);
    }
}
