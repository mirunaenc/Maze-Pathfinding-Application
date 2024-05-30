import java.util.ArrayList;
import java.util.List;
public class Node {
    int x, y;
    int valoare;  // valoarea din matrice

    boolean esteZid;
    List<Node> vecini;
    boolean esteInDrum;
    boolean esteAccesibil;

    // Constructor
    public Node(int x, int y, int valoare) {
        this.x = x;
        this.y = y;
        this.valoare = valoare;
        this.esteInDrum = false;
        this.esteAccesibil = valoare == 1;
        this.vecini = new ArrayList<>();
        this.esteZid = (valoare == 0);
    }

    // Metoda pentru adaugarea unui vecin
    public void adaugaVecin(Node vecin) {
        if (!this.vecini.contains(vecin)) {
            this.vecini.add(vecin);
        }
    }
}