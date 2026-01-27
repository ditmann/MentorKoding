import java.util.ArrayList;

public class User {
    private String name;
    private int pointTotal = 0;
    private ArrayList<Task> taskList;

    public User(String name) {
        this.name = name;
    }

    public void addListItem(Task newItem) {
        this.taskList.add(newItem);
    }

    public int getPointTotal() {
        return this.pointTotal;
    }

    public void calculatePointTotal() {
        int tempPoints = 0;
        for (Task task : taskList) {
            if (task.isTaskDone()) {
                tempPoints += task.getPoints();
            }
        }
        this.pointTotal = tempPoints;
    }

    public String getName(){
        return this.name;
    }
}
