import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

public class Ggg extends HttpServlet implements Servlet { 
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) 
    throws ServletException, IOException { 
        String parm1 = rq.getParameter("page");
        String parm2 = rq.getParameter("name");
        System.out.println("Ggg:doGet:page=" + parm1);
        System.out.println("Ggg:doGet:name=" + parm2);
        rs.setContentType("text/html");
        PrintWriter pw = rs.getWriter();
        pw.println("<html><body>"
            +"Hello from Servlet"
            +"<br>Ggg:doGet: page=" + parm1
            +"<br>Ggg:doGet: name=" + parm2
            +"<br>Ggg:getRemoteHost: "+rq.getRemoteHost()
            +"<br>Ggg:getServletPath: "+rq.getServletPath()
            +"<br>Ggg:getServerName: "+rq.getServerName()
            +"<br>Ggg:rq.getContextPath: "+rq.getContextPath()
            +"<br>Ggg:getServletName: "+this.getServletName()
            +"</body></html>");
        pw.close(); 
    } 
} 