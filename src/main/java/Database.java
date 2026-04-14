import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

public class Database {

    private static Connection conn;

    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));

            String url  = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.pass");

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("connected to db");
        }
        return conn;
    }
}