import java.util.ArrayList;
import java.util.List;

public class Graf {
    List<Node> noduri;

    // Constructor
    public Graf() {
        noduri = new ArrayList<>();
    }

    public void adaugaNod(Node nod) {
        noduri.add(nod);
    }

}
