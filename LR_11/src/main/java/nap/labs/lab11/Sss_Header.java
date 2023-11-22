package nap.labs.lab11;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Sss_Header extends HttpServlet implements Servlet
{
      @Override
      public void init() throws ServletException
      {
            super.init();
            System.out.println("Sss_Header. Init");
      }

      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {
            System.out.println("Sss_Header. Get");
      }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            System.out.println("Sss_Header. Post");
            int x = Integer.parseInt(request.getHeader("Value-x"));
            int y = Integer.parseInt(request.getHeader("Value-y"));
            Integer z = x + y;

            Thread.sleep(10000);

            response.setHeader("Value-z", z.toString());
        }
        catch (Exception e)
        {
            response.getWriter().println(e.getMessage());
        }
    }
}
