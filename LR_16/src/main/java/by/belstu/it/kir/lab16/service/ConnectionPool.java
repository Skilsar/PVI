package by.belstu.it.kir.lab16.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool(10);

    private final BlockingQueue<Connection> pool;

    public ConnectionPool(int maxPoolSize) {
        try {
            System.out.println("Connect Pool");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://dbcontainer:1433;databaseName=uwsr;user=sa;password=Qwer1234*;trustServerCertificate=true";
            this.pool = new LinkedBlockingQueue<>(maxPoolSize);
            for (int i = 0; i < maxPoolSize; i++) {
                try {
                    pool.offer(DriverManager.getConnection(url));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void returnConnection(Connection connection) {
        if (connection != null)
            pool.offer(connection);
    }
}