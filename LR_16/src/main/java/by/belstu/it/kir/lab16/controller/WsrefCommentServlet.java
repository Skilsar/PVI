package by.belstu.it.kir.lab16.controller;

import by.belstu.it.kir.lab16.bean.WsrefComment;
import by.belstu.it.kir.lab16.dto.WsrefCommentDto;
import by.belstu.it.kir.lab16.service.WsrefCommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class WsrefCommentServlet extends HttpServlet {
    private final WsrefCommentService wsrefCommentService = new WsrefCommentService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("x-session-id", req.getSession().getId());
        try {
            int wsref = Integer.parseInt(req.getParameter("wsref"));
            List<WsrefComment> wsrefCommentList = wsrefCommentService.findByWsref(wsref);
            objectMapper.writeValue(resp.getOutputStream(), wsrefCommentList);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
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
            WsrefCommentDto wsrefCommentDto = objectMapper.readValue(jsonBody.toString(), WsrefCommentDto.class);
            wsrefCommentDto.setSessionId(req.getSession().getId());
            wsrefCommentService.insertWsrefComment(wsrefCommentDto);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] pathParts = req.getPathInfo().split("/");
            int id = Integer.parseInt(pathParts[1]);
            StringBuilder jsonBody = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            WsrefCommentDto wsrefCommentDto = objectMapper.readValue(jsonBody.toString(), WsrefCommentDto.class);
            wsrefCommentService.updateWsrefComment(id, wsrefCommentDto);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] pathParts = req.getPathInfo().split("/");
            int id = Integer.parseInt(pathParts[1]);
            wsrefCommentService.deleteWsrefComment(id);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }
}