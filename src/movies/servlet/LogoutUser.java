package movies.servlet;

import java.io.IOException;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logoutUser")
public class LogoutUser extends HttpServlet {


    @Override
    public void init() throws ServletException {
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Cookie user = new Cookie("user", "");
        user.setMaxAge(0);
        Cookie searchuser = new Cookie("searchuser", "");
        searchuser.setMaxAge(0);
        resp.addCookie(searchuser);
        resp.addCookie(user);
        resp.sendRedirect("Login.jsp");
    }
}
