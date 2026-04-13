import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    public User checkLogin(String username, String password) throws Exception {
        return query("SELECT * FROM User WHERE username = ? AND password = ?", username, password);
    }

    public User getUser(String username) throws Exception {
        return query("SELECT * FROM User WHERE username = ?", username);
    }

    // shared helper so checkLogin and getUser don't repeat themselves
    private User query(String sql, String... params) throws Exception {
        PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) stmt.setString(i + 1, params[i]);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User u = new User(rs.getString("username"));
            u.setName(rs.getString("name"));
            u.setPointTotal(rs.getInt("total_points"));
            return u;
        }
        return null;
    }

    public void createUser(User user, String password) throws Exception {
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "INSERT INTO User (username, password, total_points, name) VALUES (?, ?, 0, ?)"
        );
        stmt.setString(1, user.getUsername());
        stmt.setString(2, password);
        stmt.setString(3, user.getName());
        stmt.executeUpdate();
    }

    public void deleteUser(String username) throws Exception {
        Connection conn = Database.getConnection();

        // delete tasks first or mysql complains about foreign keys
        PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM Task WHERE User_username = ?");
        stmt1.setString(1, username);
        stmt1.executeUpdate();

        PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM User WHERE username = ?");
        stmt2.setString(1, username);
        stmt2.executeUpdate();
    }

    public void updateTotalPoints(String username) throws Exception {
        User user = new User(username);
        for (Task t : new TaskDAO().getTasksForUser(username)) {
            user.addTask(t);
        }
        user.calculatePointTotal();

        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "UPDATE User SET total_points = ? WHERE username = ?"
        );
        stmt.setInt(1, user.getPointTotal());
        stmt.setString(2, username);
        stmt.executeUpdate();
    }
}