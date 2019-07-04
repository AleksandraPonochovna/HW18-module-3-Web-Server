package main;

import dao.UsersDaoImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.RequestServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        UsersDaoImpl userDaoImpl = new UsersDaoImpl();
        RequestServlet requestServlet = new RequestServlet();
        SignInServlet signInServlet = new SignInServlet(userDaoImpl);
        SignUpServlet signUpServlet = new SignUpServlet(userDaoImpl);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(requestServlet), "/mirror");
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        Server server = new Server(8080);
        server.setHandler(context);
        System.out.println("Server started.");
        server.start();
        server.join();
    }
}
