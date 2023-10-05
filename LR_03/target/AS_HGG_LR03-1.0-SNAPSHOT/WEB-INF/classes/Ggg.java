import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Ggg", urlPatterns = "/Ggg")
public class Ggg extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = null;
        final String parameter = request.getParameter("parm");
        System.out.println("parameter" + parameter);

        if (parameter != null) {
            switch (parameter) {
                case ("GetGggForw"): // TODO: TASK1
                    this.doGet(request, response);
                    break;
                case ("GetGggRedir"):
                    this.doGet(request, response);
                    break;
                case ("PostGggForw"): // TODO: TASK2
                    this.doPost(request, response);
                    break;
                case ("PostGggRedir"):
                    this.doPost(request, response);
                    break;
                case ("GetTwoHtmlForw"): // TODO: TASK4
                    requestDispatcher = request.getRequestDispatcher("pages/page.html");
                    requestDispatcher.forward(request, response);
                    break;
                case ("GetTwoHtmlRedir"): // TODO: TASK4
                    response.sendRedirect("pages/page.html");
                    break;
                case ("GetTwoInfoForw"):
                    this.doGet(request, response);
                    break;
                case ("HttpClient"):
                    response.getWriter().println("Hello " + request.getParameter("name"));
                    break;
                default:
                    System.out.println("DEFAULT");
                    break;
            }
        } else {
            final String method = request.getMethod();
            switch (method) {
                case "GET":
                    this.doGet(request, response);
                    break;

                case "POST":
                    this.doPost(request, response);
                    break;

                default:
                    System.out.println("unsupported method");
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String parameter = request.getParameter("parm");
        response.setContentType("text/html;charset=utf-8");
        System.out.println("Ggg:doGet");

        if (parameter != null && parameter.equals("GetTwoInfoForw")) {
            response.getWriter().write("<h1>output Task5 from Ggg</h1>");
        }

        response.getWriter().write("<h1>Ggg:doGet " + (parameter != null ? parameter : "") + "</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h1>Ggg:doPost  " + request.getParameter("parm") + "</h1>");
        System.out.println("Ggg:doPost");
    }
}
