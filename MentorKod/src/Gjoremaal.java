
public class Gjoremaal {

    private boolean status;
    private String goal;
    private int points;

    public Gjoremaal(boolean status, String goal, int points){
        this.status = status;
        this.goal = goal;
        this.points = points;
    }




    @Override
    public String toString() {
        return "Gjoremaal{" + "status=" + status + ", goal='" + goal + '\'' + ", points=" + points + '}';
    }

    public boolean getStatus() {
        return getStatus();
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getGoal() {
        return getGoal();
    }
    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getPoints() {
        return getPoints();
    }
    public void setPoints(int points) {
        this.points = points;
    }


}
