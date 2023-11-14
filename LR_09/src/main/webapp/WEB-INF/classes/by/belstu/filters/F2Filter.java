package by.university.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/Ccc", "/Ccc.jsp"}, filterName = "F2")
public class F2Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("We are in Ccc.jsp filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String parameterValue = request.getParameter("block");
        if ("null".equals(parameterValue)){
            filterChain.doFilter(request, response);
            String newResponseContent = "filter 2 good";
            response.setStatus(HttpServletResponse.SC_OK );
            response.getWriter().write(newResponseContent);
        }
        else if ("2".equals(parameterValue)) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            String newResponseContent = "filter 2 blocked";
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND );
            httpResponse.getWriter().write(newResponseContent);
        }else {
            filterChain.doFilter(request, response);
            String newResponseContent = "filter 2 good";
            response.setStatus(HttpServletResponse.SC_OK );
            response.getWriter().write(newResponseContent);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
