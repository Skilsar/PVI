import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/JDBC/task1")
public class JDBCServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DBConnection jdbcServlet = new DBConnection();
            ResultSet resultSet;
            if (req.getParameter("name") != null) {
                String name = req.getParameter("name");
                PreparedStatement prepState = jdbcServlet.connection.prepareStatement("select * from Users where first_name = ?");
                prepState.setString(1, name);
                resultSet = prepState.executeQuery();
            } else {
                System.out.println("select");;
                resultSet = jdbcServlet.connection.createStatement().executeQuery("select * from Users");
                System.out.println(resultSet);
            }
            PrintWriter out = resp.getWriter();
            while (resultSet.next()) {
                out.println(resultSet.getString("id"));
                out.println(resultSet.getString("first_name"));
                out.println(resultSet.getString("last_name"));
                out.println(resultSet.getString("email"));
                out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
