import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    Gjoremaal firstInList = new Gjoremaal(false,"gå med søppel", 4);
    Gjoremaal first1InList = new Gjoremaal(false,"gå med søppel", 5);
    Gjoremaal firs2tInList = new Gjoremaal(false,"gå med søppel", 6);
    Gjoremaal first3InList = new Gjoremaal(false,"gå med søppel", 7);


    System.out.println(firstInList.toString());


    ArrayList<Gjoremaal> huskeliste = new ArrayList<>();

    huskeliste.add(firstInList);
    huskeliste.add(first1InList);
    huskeliste.add(firs2tInList);
    huskeliste.add(first3InList);


    for (Gjoremaal item: huskeliste){
        System.out.println(item);
    }


    }
}
