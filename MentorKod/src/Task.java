
public class Task {

    private boolean taskDone;
    private String goal;
    private int points;

    public Task(boolean taskDone, String goal, int points){
        this.taskDone = taskDone;
        this.goal = goal;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Gjoremaal{" + "status=" + taskDone + ", goal='" + goal + '\'' + ", points=" + points + '}';
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
