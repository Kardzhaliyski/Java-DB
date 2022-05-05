package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
    private static Connection connection;
    private static final String connectionString = "jdbc:mysql://localhost:3306/";

    private MyConnector() {};

    public static void createConnection(String username, String password, String dbName) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(connectionString + dbName, properties);
    }

    public static Connection getConnection() {
        if(connection == null) throw new NullPointerException("Not connected to a DB!");
        return connection;
    }

    public static void close() throws SQLException {
        connection.close();
    }
}
