import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class Ccc extends HttpServlet implements Servlet
{
    public Ccc()
    {
        super();
        System.out.println("Ccc:constructor");
    }

    public void init(ServletConfig sc) throws ServletException
    {
        super.init();
        System.out.println("Ccc:init");
    }

    public void destroy()
    {
        super.destroy();
        System.out.println("Ccc:destroy");
    }

    protected void service(HttpServletRequest rq, HttpServletResponse rs)
    throws ServletException, IOException
    {
    	System.out.println("Sss:service:"+rq.getMethod() + "\n\r, Sss:service: " + rq.getRequestURL());
    	System.out.println("Sss:serviceName :"+rq.getServerName() + "\n\r, Sss:service: " + rq.getRequestURL());
    	rs.setContentType("text/html");
    	PrintWriter pw = rs.getWriter();
    	pw.println("Sss:serviceName :"+rq.getServerName() + "\nSss:metod: " + rq.getMethod() + "\n\r, Sss:service: " + rq.getRequestURL());
        super.service(rq,rs);
    }

    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException
    {
        String firstname = rq.getParameter("firstname");
        String lastname = rq.getParameter("lastname");
        System.out.println("Ccc:doGet:fistname=" + firstname + ", lastname=" + lastname);
        rs.setContentType("text/html");
        PrintWriter pw = rs.getWriter();
        pw.println("<html> "
                +"<body> "
                +"<br><h1>Metod:" + rq.getMethod() + "</h1>"
                +"<br><h2>RequestURL:" + rq.getRequestURL() + "</h2>"
                +"<br><h2>Ccc:Get firstname = " + firstname + ", lastname = " + lastname + "</h2>"
                +"</body>"
                +"</html>");
        pw.close();
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException
    {
        String firstname = rq.getParameter("firstname");
        String lastname = rq.getParameter("lastname");
        System.out.println("Ccc:doPost:fistname=" + firstname + ", lastname=" + lastname);
        rs.setContentType("text/html");
        PrintWriter pw = rs.getWriter();
        pw.println("<html> "
                +"<body> "
                +"<h1>Ccc:Post firstname=" + firstname + ", lastname=" + lastname + "</h1>"
                +"</body>"
                +"</html>");
        pw.close();
    }
}