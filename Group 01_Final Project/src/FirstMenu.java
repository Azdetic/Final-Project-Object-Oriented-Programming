import java.sql.*;

public class FirstMenu {
    private static final String DB_FILE = "user_management.db";
    private static Connection conn = null;
    
    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Load SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");
                // Create connection to SQLite database
                conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
                System.out.println("Database connected successfully!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Database connection error: " + e.getMessage());
            }
        }
        return conn;
    }
}
