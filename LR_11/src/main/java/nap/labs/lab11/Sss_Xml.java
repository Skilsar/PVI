package nap.labs.lab11;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class Sss_Xml extends HttpServlet implements Servlet {
      @Override
      public void init() throws ServletException
      {
            super.init();
            System.out.println("Sss_Xml:init");
      }

      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
      {
            System.out.println("Sss_Xml:get");
      }

      @Override
      protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
      {
            try
            {
                Random random = new Random();
                int n = Integer.parseInt(request.getHeader("XRand-N"));
                StringBuilder textResult = new StringBuilder("<?xml version=\"1.0\"  encoding = \"utf-8\" ?>");
                int count = random.nextInt(6) + 5;

                textResult.append("<rand>");

                for (int i = 0; i < count; i++)
                {
                    Integer number = random.nextInt(n*2) - n;
                    textResult.append("<num>").append(number).append("</num>");
                }
                textResult.append("</rand>");

                Thread.sleep(5000);

                response.setContentType("text/xml");
                response.getWriter().println(textResult);

            }
            catch (Exception e)
            {
                response.getWriter().println(e.getMessage());
            }
        }
}
