package movies.servlet;


import movies.dal.UsersDao;
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


@WebServlet("/movieusercreate")
public class UserCreate extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		 usersDao = UsersDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the BlogUser.
        	String firstName = req.getParameter("firstname");
        	String lastName = req.getParameter("lastname");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Users user = new Users(userName, firstName, lastName, password, email, phone);
	        	user = usersDao.create(user);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }
}
