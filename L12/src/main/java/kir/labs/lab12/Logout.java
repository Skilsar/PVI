package main.java.kir.labs.lab12;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Logout extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("Servlet:Logout");
        if (request.getSession() != null) {
            request.getSession().invalidate();// remove session.
        }
        request.logout();// JAAS log out (from servlet specification)! It is a MUST!
        response.sendRedirect("http://localhost:8085/BKS_12/");
    }
}