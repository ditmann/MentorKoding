import java.sql.Connection;
import java.sql.DriverManager;

// just holds the db connection so every other class can use it
public class Database {
    /**
     * URL change /yourDatabaseName
     * USER change to your user
     * PASS change to your pass
     */

    private static final String URL  = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "1234";

    private static Connection conn;

    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("connected to db");
        }
        return conn;
    }
}