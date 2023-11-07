package by.belstu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// http://localhost:8085/HGG_LAB_6/url?urln=1
// http://localhost:8085/HGG_LAB_6/url?urln=2
public class serv extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String param = req.getParameter("urln");
        System.out.println(param);
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("servlet2");
    }
}
