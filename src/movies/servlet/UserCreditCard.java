package movies.servlet;


import movies.dal.CreditCardsDao;
import movies.dal.UsersDao;
import movies.model.CreditCards;
import movies.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/usercreditcard")
public class UserCreditCard extends HttpServlet {

    protected CreditCardsDao creditCardsDao;
    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        creditCardsDao = CreditCardsDao.getInstance();
        usersDao = UsersDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
       doPost(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Cookie[] cookies = req.getCookies();
        String userName = null;
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            if(name.equals("user")){
                userName = cookies[i].getValue();
            }
        }
        // Retrieve and validate name.
        try {
            String cardnumber = req.getParameter("cardnumber");
            List<CreditCards> creditcard = creditCardsDao.getCreditCardsByUserName(userName);
            req.setAttribute("creditcard", creditcard);
            if (cardnumber != null && !cardnumber.trim().isEmpty()) {
                // Create the BlogUser.
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String stringDob = req.getParameter("expiration");
                Date expiration;
                expiration = dateFormat.parse(stringDob);
                System.out.println(userName);
                Users user = usersDao.getUserFromUserName(userName);
                CreditCards creditCards = new CreditCards(Integer.parseInt(cardnumber), expiration, user);
                creditCards = creditCardsDao.create(creditCards);
                messages.put("success", "Successfully created " + creditCards);
            }
        }catch (ParseException | SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.getRequestDispatcher("/UserCreditCards.jsp").forward(req, resp);
    }
}
