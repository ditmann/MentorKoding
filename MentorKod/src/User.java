import java.util.ArrayList;

public class User {
    
    private ArrayList<Gjoremaal> Papirblokk;
    private int poeng;
    private String name;
    
    public User (ArrayList<Gjoremaal> Papirblokk, String name, int poeng) {
        this.Papirblokk = Papirblokk;
        this.name = name;
        this.poeng = poeng;
    }
    
    public User (ArrayList<Gjoremaal> Papirblokk) {
        this.Papirblokk = Papirblokk;
        this.poeng = 0;
    }
    
    public ArrayList<Gjoremaal> getPapirblokk() {
        return Papirblokk;
    }
    
    public void newPapirblokk(ArrayList<Gjoremaal> newPapirblokk) {
        this.Papirblokk = newPapirblokk;
    }
    
    public void addTask(Gjoremaal newgjoremaal) {
        Papirblokk.add(newgjoremaal);
    }
}
