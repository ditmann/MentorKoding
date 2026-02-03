import java.util.ArrayList;

public class User {

    private int points;
    private String name;
    private int pointTotal = 0;
    private ArrayList<Task> taskList = new ArrayList<>();

    public User (ArrayList<Task> taskList, String name, int poeng) {
        this.taskList = taskList;
        this.name = name;
        this.points = poeng;
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
