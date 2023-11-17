import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;

@WebServlet("/JDBC/task4")
public class StoredProcedureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DBConnection jdbcServlet = new DBConnection();


            String userIdParam = req.getParameter("userId");


            if (userIdParam != null && !userIdParam.isEmpty()) {
                int userId = Integer.parseInt(userIdParam);
                System.out.println(userId);


                String sql = "exec get_user_by_id @user_id = ?, @user_name = ?;";
                CallableStatement callableStatement = jdbcServlet.connection.prepareCall(sql);
                callableStatement.setInt(1, userId);
                callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                callableStatement.execute();
                String userName = callableStatement.getString(2);

                PrintWriter out = resp.getWriter();
                out.println("<html><body>");
                out.println("User Name: " + userName);
                out.println("</body></html>");

                callableStatement.close();
            } else {
                // Handle the case where userIdParam is null or empty
                resp.getWriter().println("User ID parameter is missing or empty.");
            }
        } catch (SQLException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
