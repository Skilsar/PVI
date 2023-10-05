import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WebServlet(name = "Sss", urlPatterns = "/Sss")
public class Sss extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.service(request, response);
        System.out.println("Sss:service");
        RequestDispatcher requestDispatcher = null;
        String parm = request.getParameter("parm");
        PrintWriter printWriter = response.getWriter();
        switch (parm) {
            case ("GetGggForw"): // TODO: TASK1
                requestDispatcher = request.getRequestDispatcher("Ggg");
                requestDispatcher.forward(request, response);
                break;
            case ("GetGggRedir"):
                response.sendRedirect("Ggg"); // "Ggg?parm="+parm
                break;
            case ("PostGggForw"): // TODO: TASK2
                requestDispatcher = request.getRequestDispatcher("Ggg");
                requestDispatcher.forward(request, response);
                break;
            case ("PostGggRedir"):
                response.setStatus(307);
                response.setHeader("Location", "http://localhost:8085/AS_HGG_LR03/Ggg");
                break;
            case ("GetHtmlForw"): // TODO: TASK3
                requestDispatcher = request.getRequestDispatcher("pages/page.html");
                requestDispatcher.forward(request, response);
                break;
            case ("GetHtmlRedir"):
                response.sendRedirect("pages/page.html");
                break;
            case ("GetTwoHtmlForw"): // TODO: TASK4
                requestDispatcher = request.getRequestDispatcher("Ggg");
                requestDispatcher.forward(request, response);
                break;
            case ("GetTwoHtmlRedir"): // TODO: TASK4
                response.sendRedirect("Ggg?parm=" + parm);
                break;
            case ("GetTwoInfoForw"): // TODO: TASK5
                printWriter.write("Info from Sss");
                requestDispatcher = request.getRequestDispatcher("Ggg");
                requestDispatcher.forward(request, response);
                break;
            case ("GetTwoInfoRedir"): // TODO: TASK5
                printWriter.write("Info from Sss");
                response.sendRedirect("Ggg");
                break;
            case ("HttpClientGet"): // TODO: TASK7
                HttpClient client = HttpClient.newHttpClient();
                System.out.println(request.getRequestURI());
                HttpRequest customRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8085/AS_HGG_LR03/Ggg?parm=HttpClient&name=Gleb")).build();
                try {
                    HttpResponse<String> customResponse = client.send(customRequest,
                            HttpResponse.BodyHandlers.ofString());
                    response.getWriter().write("response Task 7:" + customResponse.body());
                } catch (InterruptedException e) {
                    response.getWriter().write("Task 7: Bad Request");
                }
                break;
            case ("HttpClientGetRemote"):
                System.out.println("remote");
                HttpClient clientToRemote = HttpClient.newHttpClient();
                HttpRequest customRequestToRemote = HttpRequest.newBuilder()
                        .uri(URI.create(
                                "http://localhost:8086/AS_HGG_LR03/Ggg?parm=HttpClient&name=GlebRemote"))
                        .build();
                try {
                    HttpResponse<String> customResponse = clientToRemote.send(customRequestToRemote,
                            HttpResponse.BodyHandlers.ofString());
                    response.getWriter().write("response Task 7:" + customResponse.body());
                } catch (InterruptedException e) {
                    response.getWriter().write("Task 7: Bad Request");
                }
                break;
            case ("HttpClientPost"):// TODO: TASK7
                HttpClient clientTask8 = HttpClient.newHttpClient();
                HttpRequest customRequestTask8 = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8085/AS_HGG_LR03/Ggg?parm=HttpClient&name=HGG")).build();
                try {
                    HttpResponse<String> customResponse = clientTask8.send(customRequestTask8,
                            HttpResponse.BodyHandlers.ofString());
                    response.getWriter().write("response Task 8:" + customResponse.body());
                } catch (InterruptedException e) {
                    response.getWriter().write("Task 8: Bad Request");
                }
                break;
            case ("HttpClientPostRemote"):
                HttpClient clientTask8Remote = HttpClient.newHttpClient();
                HttpRequest customRequestTask8Remote = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8086/AS_HGG_LR03/Ggg?parm=HttpClient&name=HGG_Remote"))
                        .build();
                try {
                    HttpResponse<String> customResponse = clientTask8Remote.send(customRequestTask8Remote,
                            HttpResponse.BodyHandlers.ofString());
                    response.getWriter().write("response Task 8:" + customResponse.body());
                } catch (InterruptedException e) {
                    response.getWriter().write("Task 8: Bad Request");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h1>Sss:doGet</h1>");
        System.out.println("Sss:doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h1>Sss:doPost</h1>");
        System.out.println("Sss:doPost");
    }
}
