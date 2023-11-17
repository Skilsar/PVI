
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection connection;

    public DBConnection() throws SQLException {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=HGG10;encrypt=false;";
        String username = "sa";
        String password = "Qwer1234*";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            connection = conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
