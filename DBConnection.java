import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/university_db";
        String user = "root"; // Change if needed
        String password = "your_password"; // Change your password
        return DriverManager.getConnection(url, user, password);
    }
}