package servlets;

import dao.UsersDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet extends HttpServlet {
    private final String CONTENT_TYPE = "text/html;charset=utf-8";
    private UsersDaoImpl user;

    public SignUpServlet(UsersDaoImpl user) {
        this.user = user;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType(CONTENT_TYPE);
        try {
            user.addUser(login, password);
            response.getWriter().println("Authorized: " + login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
