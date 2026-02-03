
public class Task {

    private boolean taskDone;
    private String goalDescription;
    private int points;

    public Task(String goalDescription, int points){
        taskDone = false;
        this.goalDescription = goalDescription;
        this.points = points;
    }

    public boolean getTaskDone() {
        return taskDone;
    }

    public void setTaskDone() {
        taskDone = true;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Task{" + "status=" + taskDone + ", goal='" + goalDescription + '\'' + ", points=" + points + '}';
    }
}
