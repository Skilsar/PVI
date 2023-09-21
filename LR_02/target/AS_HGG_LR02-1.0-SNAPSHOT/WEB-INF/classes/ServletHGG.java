import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class ServletHGG extends HttpServlet {

   private String message = "";

   public ServletHGG() {
      super();
      System.out.println("ServletHGG: constructor");
   }

   public void init() throws ServletException {
      super.init();
      final String init = "init state";
      System.out.println(init);
      message += init;
   }

   public void destroy() {
      super.destroy();
      final String destroy = "destroy state";
      System.out.println(destroy);
      message += " " + destroy;
   }

   @Override
   public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.service(req, resp);
      final String service = "service state";
      System.out.println(service);
      message += " " + service;
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      // Set response content type
      response.setContentType("text/html");

      final String method = request.getMethod();
      final String ip = request.getRemoteAddr();
      final String host = request.getRemoteHost();
      final String queryString = request.getQueryString();
      final String firstname = queryString.split("&")[0].split("=")[1];
      final String lastname = queryString.split("&")[1].split("=")[1];
      final String uri = request.getRequestURI();

      message = "<p>Hello World</p>";
      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>" + "Method:" + method + "</h1>");
      out.println("<h1>" + "IP:" + ip + "</h1>");
      out.println("<h1>" + "Host:" + host + "</h1>");
      out.println("<h1>" + message + "</h1>");
      out.println("<h1>" + firstname + "</h1>");
      out.println("<h1>" + lastname + "</h1>");
      out.println("<h1>" + uri + "</h1>");

   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      // Set response content type
      response.setContentType("text/html");

      final String method = request.getMethod();
      final String ip = request.getRemoteAddr();
      final String host = request.getRemoteHost();
      final String queryString = request.getQueryString();
      final String firstname = queryString.split("&")[0].split("=")[1];
      final String lastname = queryString.split("&")[1].split("=")[1];
      final String uri = request.getRequestURI();

      message = "<h1>Post request</h1>";
      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>Post request</h1>");
      out.println("<h1>" + "Method:" + method + "</h1>");
      out.println("<h1>" + "IP:" + ip + "</h1>");
      out.println("<h1>" + "Host:" + host + "</h1>");
      out.println("<h1>" + firstname + "</h1>");
      out.println("<h1>" + lastname + "</h1>");
      out.println("<h1>" + uri + "</h1>");
   }

}