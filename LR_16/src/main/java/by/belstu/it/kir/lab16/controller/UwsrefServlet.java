package by.belstu.it.kir.lab16.controller;

import by.belstu.it.kir.lab16.dto.AuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;

import java.io.BufferedReader;
import java.io.IOException;

public class UwsrefServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public static String generateSessionId() {
        SecureRandom random = new SecureRandom();
        byte[] sessionIdBytes = new byte[16];
        random.nextBytes(sessionIdBytes);
        return DatatypeConverter.printHexBinary(sessionIdBytes);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/uwsr.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StringBuilder jsonBody = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }

            String sessionId = generateSessionId();
            System.out.println(sessionId);
            Cookie newCookie = new Cookie("JSESSIONID", sessionId);
            AuthDto authDto = objectMapper.readValue(jsonBody.toString(), AuthDto.class);
            if (!req.getServletContext().getInitParameter("PASSWORD").equals(authDto.getPassword())) {
                throw new RuntimeException("Invalid password");
            } else {
                resp.addCookie(newCookie); // Установка новой куки в ответ
            }

        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String sessionId = generateSessionId();
            System.out.println(sessionId);
            Cookie newCookie = new Cookie("JSESSIONID", sessionId);
            resp.addCookie(newCookie); // Установка новой куки в ответ
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }
}