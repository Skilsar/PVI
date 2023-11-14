package by.belstu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet()
public class Bbb extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
        String param3 = request.getParameter("param3");

        StringBuilder headersInfo = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersInfo.append(headerName).append(": ").append(headerValue).append("<br>");
        }

        response.addHeader("Custom-Header1", "Value1");
        response.addHeader("Custom-Header2", "Value2");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Received Parameters and Headers:</h1>");
        out.println("<p>param1: " + param1 + "</p>");
        out.println("<p>param2: " + param2 + "</p>");
        out.println("<p>param3: " + param3 + "</p>");

        out.println("<h2>Headers:</h2>");
        out.println("<p>" + headersInfo.toString() + "</p>");
        out.println("</body></html>");
    }
}
