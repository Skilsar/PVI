import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class Jjj extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jjj:doGet");
        forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Jjj:doPost");
        forward(req, resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int hour = LocalDateTime.now().getHour();
        if (hour < 12) {
            resp.getWriter().write(getFromJsp("pages/morning.jsp", resp));
            // req.getRequestDispatcher("morning.jsp").forward(req, resp);
        } else if (hour < 17) {
            // resp.setHeader("Content-Type", "text/html");
            resp.getWriter().write(getFromJsp("pages/afternoon.jsp", resp));

            // req.getRequestDispatcher("pages/afternoon.jsp").forward(req, resp);
        } else if (hour < 22) {
            req.getRequestDispatcher("pages/evening.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("pages/night.jsp").forward(req, resp);
        }
    }

    private String getFromJsp(String jspName, HttpServletResponse resp) throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:8086/HGG_LR_04/%s", jspName)))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return "Task 12" + response.body();
        } catch (InterruptedException | IOException e) {
            return "Task 12: Bad Request";
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getMethod().equals("POST")) {
            this.doPost(req, resp);
        } else if (req.getMethod().equals("GET")) {
            this.doGet(req, resp);
        }
    }
}