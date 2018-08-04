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


/**
 * FindUsers is the primary entry point into the application.
 *
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 *
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/loginUser")
public class LoginUser extends HttpServlet {

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
        Users users = null;
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || username.trim().isEmpty()) {
            messages.put("failed", "Please enter a valid name.");
			req.getRequestDispatcher("/Login.jsp").forward(req, resp);
		} else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	users = usersDao.getUserFromUserNameAndPassword(username, password);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + username);
        	if(users == null){
                messages.put("failed", "User not found please check the credentials");
                req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            }
            req.setAttribute("Users", users);
        	req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
        }

	}
}
