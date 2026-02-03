import java.util.ArrayList;

public class User {
    private String name;
    private int pointTotal;
    private ArrayList<Task> taskList = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public User (String name, ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.name = name;
    }

    public void addListItem(Task newItem) {
        this.taskList.add(newItem);
    }


    // tbc
    public void changeList(ArrayList<Task> newList) {

    }

    public int getPointTotal() {
        return this.pointTotal;
    }

    public void calculatePointTotal() {
        int tempPoints = 0;
        for (Task task : taskList) {
            if (task.getTaskDone()) {
                tempPoints += task.getPoints();
            }
        }
        this.pointTotal = tempPoints;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // returning a copy of the actual list
    public ArrayList<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }

}
