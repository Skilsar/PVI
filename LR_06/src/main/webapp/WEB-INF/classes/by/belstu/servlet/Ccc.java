package belstu.servlet;

import java.io.IOException;
import java.util.List;

import belstu.CBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// http://localhost:8085/HGG_LR_6/ccc?cBean=old&value1=v1&value2=v2&value3=v3
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

        if (cBeanName == null) {
            writeErrorMessage(response, "add cBean parameter");
            return;
        } else {
            if (cBeanName.equals("new")) {
                System.out.println("new");
                newCBean = new CBean();
                newCBean = this.setValues(newCBean, value1, value2, value3);
                getServletContext().setAttribute("atrCBean", newCBean);
            } else if (cBeanName.equals("old")) {
                System.out.println("old");
                cBean = (CBean) getServletContext().getAttribute("atrCBean");
                cBean = this.setValues(cBean, value1, value2, value3);
                getServletContext().setAttribute("atrCBean", cBean);
            } else {
                writeErrorMessage(response, "wrong cBean parameter");
                return;
            }
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Ccc.jsp");
        requestDispatcher.forward(request, response);
    }

    private CBean setValues(CBean cBean, String value1, String value2, String value3) {
        if (cBean != null){
            if (value1 != null) {
                cBean.setValue1(value1);
            }
            if (value2 != null) {
                cBean.setValue2(value2);
            }
            if (value3 != null) {
                cBean.setValue3(value3);
            }
        }
        return cBean;
    }

    private void writeErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(message);
    }
}
