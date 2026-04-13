import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    Task item1 = new Task("Gå ut med søpla", 4);
    Task item2 = new Task("Be Mats om å prøve før han gir opp", 5);
    Task item3 = new Task("Si at Mats er gammel", 6);
    Task item4 = new Task("Få Håvard til å si \"Hmm?\"", 7);

    ArrayList<Task> huskeliste = new ArrayList<>();

    huskeliste.add(item1);
    huskeliste.add(item2);
    huskeliste.add(item3);
    huskeliste.add(item4);

    for (Task item: huskeliste){
        System.out.println(item);
    }
    }
}
