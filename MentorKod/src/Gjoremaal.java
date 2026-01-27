
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
}
