import java.util.ArrayList;

public class Bruker {

    private String name;
    private int pointTotal;
    private ArrayList<Gjoremaal> huskeListe = new ArrayList<>();

    public Bruker(String name, int pointTotal) {
        this.name = name;
        this.pointTotal = pointTotal;
    }

    public void addGoal(Gjoremaal goal) {
        huskeListe.add(goal);
    }

    public ArrayList<Gjoremaal> getList() {
        return new ArrayList<>(huskeListe);
    }



}
