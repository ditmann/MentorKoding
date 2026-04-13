import java.sql.*;
import java.util.ArrayList;

public class TaskDAO {

    // get all tasks for a user
    public ArrayList<Task> getTasksForUser(String username) throws Exception {
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "SELECT * FROM Task WHERE User_username = ? ORDER BY date_created DESC"
        );
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Task> tasks = new ArrayList<>();
        while (rs.next()) {
            Task t = new Task(rs.getString("tasks"), rs.getInt("points"));
            t.setDescription(rs.getString("task_description"));
            t.setDone(rs.getInt("done") == 1);
            t.setDateDue(rs.getString("date_due"));
            tasks.add(t);
        }
        return tasks;
    }

    // save a new task to db
    public void createTask(Task task, String username, String dateDue) throws Exception {
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "INSERT INTO Task (tasks, date_created, User_username, task_description, points, done, date_due) " +
                        "VALUES (?, NOW(), ?, ?, ?, 0, ?)"
        );
        stmt.setString(1, task.getTitle());
        stmt.setString(2, username);
        stmt.setString(3, task.getDescription());
        stmt.setInt(4, task.getPoints());

        if (dateDue == null || dateDue.isEmpty())
            stmt.setNull(5, Types.TIMESTAMP);
        else
            stmt.setString(5, dateDue);

        stmt.executeUpdate();
    }

    // flip done between 0 and 1
    public void toggleDone(String taskName, String username) throws Exception {
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "UPDATE Task SET done = 1 - done WHERE tasks = ? AND User_username = ?"
        );
        stmt.setString(1, taskName);
        stmt.setString(2, username);
        stmt.executeUpdate();
    }

    // delete a task
    public void deleteTask(String taskName, String username) throws Exception {
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "DELETE FROM Task WHERE tasks = ? AND User_username = ?"
        );
        stmt.setString(1, taskName);
        stmt.setString(2, username);
        stmt.executeUpdate();
    }
}