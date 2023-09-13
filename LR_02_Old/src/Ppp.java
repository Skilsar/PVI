import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
public class Ppp extends HttpServlet implements Servlet { 
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) 
    throws ServletException, IOException { 
        String firstname = rq.getParameter("firstname"); 
        String lastname = rq.getParameter("lastname"); 
        String password = rq.getParameter("password"); 
        String sex = rq.getParameter("sex"); 
        String press = rq.getParameter("press"); 
        rs.setContentType("text/html"); 
        PrintWriter pw = rs.getWriter(); 
        pw.println("<html> " + "<body> " 
            + "<br>Ppp:doPost:firstname=" + firstname 
            + "<br>Ppp:doPost:lastname= " + lastname 
            + "<br>Ppp:doPost:password= " + password 
            + "<br>Ppp:doPost:sex= " + sex 
            + "<br>Ppp:doPost:press= " + press 
            + "</body>" + "</html>"); 
        pw.close(); 
    } 
} 