package by.belstu.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static java.lang.System.out;

@WebFilter(urlPatterns = "/Ccc", filterName = "F1")
public class F1Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        out.println("init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        out.println("We are in CccServlet filter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String parameterValue = request.getParameter("block");
        out.println(parameterValue);
        if ("null".equals(parameterValue)){
            filterChain.doFilter(request, response);
            String newResponseContent = "filter 1 good";
            response.setStatus(HttpServletResponse.SC_OK );
            response.getWriter().write(newResponseContent);
        }
        else if ("1".equals(parameterValue)) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            String newResponseContent = "filter 1 blocked";
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND );
            httpResponse.getWriter().write(newResponseContent);
        } else {
            filterChain.doFilter(request, response);
            String newResponseContent = "filter 1 good";
            response.setStatus(HttpServletResponse.SC_OK );
            response.getWriter().write(newResponseContent);
        }



    }

    @Override
    public void destroy() {
        out.println("destroy");
        Filter.super.destroy();
    }
}
