package by.belstu.servlet;

import java.io.IOException;
import java.util.List;

import by.belstu.CBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// http://localhost:8085/HGG_09/ccc?cBean=old&value1=v1&value2=v2&value3=v3
public class Ccc extends HttpServlet {

    private CBean cBean = new CBean();
    private CBean newCBean = new CBean();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response(request, response);
    }

    protected void response(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value1 = request.getParameter("value1");
        String value2 = request.getParameter("value2");
        String value3 = request.getParameter("value3");
        String cBeanName = request.getParameter("cBean");
        String sessionId = request.getSession().getId();
        System.out.println(sessionId);
        if (cBeanName == null) {
            writeErrorMessage(response, "add cBean parameter");
            return;
        } else {
            if (cBeanName.equals("new")) {
                System.out.println("new");
                newCBean = new CBean();
                newCBean = this.setValues(newCBean, value1, value2, value3);
                request.getSession().setAttribute(sessionId, newCBean);
            } else if (cBeanName.equals("old")) {
                System.out.println("old");
                cBean = (CBean) request.getSession().getAttribute(sessionId);
                if (cBean == null) {
                    writeErrorMessage(response, "create new CBean");
                    return;
                }
                cBean = this.setValues(cBean, value1, value2, value3);
                request.getSession().setAttribute(sessionId, cBean);
            } else {
                writeErrorMessage(response, "wrong cBean parameter");
                return;
            }
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/CccSession.jsp");
        requestDispatcher.forward(request, response);
    }


    private CBean setValues(CBean cBean, String value1, String value2, String value3) {
        if (value1 != null) {
            cBean.setValue1(value1);
        }
        if (value2 != null) {
            cBean.setValue2(value2);
        }
        if (value3 != null) {
            cBean.setValue3(value3);
        }
        return cBean;
    }

    private void writeErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(message);
    }
}
