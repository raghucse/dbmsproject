package movies.servlet;

import movies.dal.*;
import movies.model.*;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/findcreditcard")
public class FindCreditCard extends HttpServlet {

    protected CreditCardsDao creditCardsDao;

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
        List<CreditCards> creditCards = new ArrayList<CreditCards>();
        try {
            creditCards = creditCardsDao.getAllCreditCards();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
//            messages.put("success", "Displaying results for " + username);
        // Save the previous search term, so it can be used as the default
        // in the input box when rendering FindUsers.jsp.
//            messages.put("creditcard", creditCards);
        req.setAttribute("creditcard", creditCards);
        req.getRequestDispatcher("/FindCreditCard.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<CreditCards> creditCards = new ArrayList<CreditCards>();
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            // Retrieve BlogUsers, and store as a message.
            try {
                creditCards = creditCardsDao.getCreditCardsByUserName(username);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + username);
        }
        req.setAttribute("creditcard", creditCards);

        req.getRequestDispatcher("/FindCreditCard.jsp").forward(req, resp);
    }
}
