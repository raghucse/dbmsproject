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


@WebServlet("/deletecreditcard")
public class DeleteCreditCard extends HttpServlet {

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
        // Provide a title and render the JSP.
        messages.put("title", "Delete User");
        req.getRequestDispatcher("/DeleteCreditCard.jsp").forward(req, resp);
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
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the BlogUser.
            CreditCards creditCards = new CreditCards(Long.parseLong(cardnumber));
            try {
                creditCards = creditCardsDao.delete(creditCards);
                // Update the message.
                if (creditCards == null) {
                    messages.put("title", "Successfully deleted " + cardnumber);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + cardnumber);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/DeleteCreditCard.jsp").forward(req, resp);
    }
}
