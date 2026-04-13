import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class User {

    private String username;
    private String name;

    @JsonProperty("total_points")
    private int pointTotal;

    // don't include taskList when sending user as json (we have a separate endpoint for tasks)
    @JsonIgnore
    private ArrayList<Task> taskList = new ArrayList<>();

    // Jackson needs an empty constructor
    public User() {}

    public User(String username) {
        this.username = username;
        this.name = username;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    // adds up points from completed tasks
    public void calculatePointTotal() {
        int total = 0;
        for (Task task : taskList) {
            if (task.isDone()) {
                total += task.getPoints();
            }
        }
        this.pointTotal = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(int pointTotal) {
        this.pointTotal = pointTotal;
    }

    public ArrayList<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', name='" + name + "', points=" + pointTotal + "}";
    }
}