package by.belstu.servlet;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Aaa extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // URL сервлета Bbb на удаленном сервере, заменить localhost на адрес удаленного сервера
        String remoteServerUrl = "http://localhost:8086/HGG_8/Bbb";

        String param1 = "param1Value";
        String param2 = "param2Value";
        String param3 = "param3Value";

        String header1Name = "Header1";
        String header1Value = "Header1Value";
        String header2Name = "Header2";
        String header2Value = "Header2Value";
        String header3Name = "Header3";
        String header3Value = "Header3Value";

        // Выполняем HTTP-запрос POST к сервлету Bbb на удаленном сервере
        HttpClient httpClient = HttpClients.createDefault();
        String params = "?param1=" + param1 + "&param2=" + param2 + "&param3=" + param3;
        remoteServerUrl+=params;
        HttpPost httpPost = new HttpPost(remoteServerUrl);

        httpPost.addHeader(header1Name, header1Value);
        httpPost.addHeader(header2Name, header2Value);
        httpPost.addHeader(header3Name, header3Value);

        HttpResponse httpResponse = httpClient.execute(httpPost);

        Header[] responseHeaders = httpResponse.getAllHeaders();

        // Выводим значения заголовков в окно браузера
        for (Header header : responseHeaders) {
            out.println(header.getName() + ": " + header.getValue());
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        HttpEntity entity = httpResponse.getEntity();
        String responseBody = EntityUtils.toString(entity);

        response.setContentType("text/html");
        out.println("<html><body>");
        out.println("<h1>Response from Bbb:</h1>");
        out.println("<p>Status Code: " + statusCode + "</p>");
        out.println("<p>Response Body: " + responseBody + "</p>");
        out.println("</body></html>");
    }
}

