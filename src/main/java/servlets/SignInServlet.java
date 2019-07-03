package servlets;

import dao.UsersDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignInServlet extends HttpServlet {
    private final String CONTENT_TYPE = "text/html;charset=utf-8";
    private UsersDaoImpl user;

    public SignInServlet(UsersDaoImpl user) {
        this.user = user;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType(CONTENT_TYPE);
        try {
            if (user.isUserPresent(login, password)) {
                response.getWriter().println("You are entering: " + login);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().println("Wrong login or password!");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}