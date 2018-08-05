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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Users users = null;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || username.trim().isEmpty()) {
            messages.put("failed", "Please enter a valid name.");
			req.getRequestDispatcher("/Login.jsp").forward(req, resp);
		} else {
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
            Cookie user = new Cookie("user",
       			  username);
			resp.addCookie(user);
        	resp.sendRedirect("DashBoard.jsp");
        }

	}
}
