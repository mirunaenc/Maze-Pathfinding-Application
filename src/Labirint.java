import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Labirint {
    Node[][] matrice;
    Node start;
    List<Node> iesiri;
    Graf graf;

    // Constructor
    public Labirint(Node[][] matrice, Node start) {
        this.matrice = matrice;
        this.start = start;
        this.iesiri = new ArrayList<>();
        this.graf = new Graf();
        construiesteGraf();
        gasesteIesiri();
    }
    public List<Node> gasesteDrum(Node start, Node end) {
        // Creez o coada pentru nodurile care trebuie vizitate
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        // Creez un map pentru a tine evidenta nodurilor vizitate si a nodurilor parinte
        Map<Node, Node> visited = new HashMap<>();
        visited.put(start, null);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            // Daca am ajuns la nodul final, reconstruiesc drumul
            if (node.equals(end)) {
                List<Node> path = new ArrayList<>();
                Node step = end;

                while (step != null) {
                    path.add(step);
                    step.esteInDrum = true;
                    step = visited.get(step);
                }

                Collections.reverse(path);
                return path;
            }

            // Adaug toti vecinii nevizitati ai nodului curent in coada
            for (Node neighbour : node.vecini) {
                if (!visited.containsKey(neighbour)) {
                    queue.add(neighbour);
                    visited.put(neighbour, node);
                }
            }
        }
        end.esteAccesibil = false;

        // Daca nu exista un drum de la start la end, returnez null
        return null;
    }
    // Metoda pentru a construi graful
    private void construiesteGraf() {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[0].length; j++) {
                if (matrice[i][j].esteAccesibil) {
                    Node nod = matrice[i][j];
                    graf.adaugaNod(nod);
                    if (i > 0 && matrice[i-1][j].esteAccesibil) {
                        nod.adaugaVecin(matrice[i-1][j]);
                    }
                    if (j > 0 && matrice[i][j-1].esteAccesibil) {
                        nod.adaugaVecin(matrice[i][j-1]);
                    }
                    if (i < matrice.length - 1 && matrice[i+1][j].esteAccesibil) {
                        nod.adaugaVecin(matrice[i+1][j]);
                    }
                    if (j < matrice[0].length - 1 && matrice[i][j+1].esteAccesibil) {
                        nod.adaugaVecin(matrice[i][j+1]);
                    }
                }
            }
        }
    }

    private void gasesteIesiri() {
        int m = matrice.length;
        int n = matrice[0].length;

        // Verific marginile matricei pentru iesiri
        for (int i = 0; i < m; i++) {
            if (matrice[i][0].esteAccesibil) {
                iesiri.add(matrice[i][0]);
            }
            if (matrice[i][n-1].esteAccesibil) {
                iesiri.add(matrice[i][n-1]);
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrice[0][j].esteAccesibil) {
                iesiri.add(matrice[0][j]);
            }
            if (matrice[m-1][j].esteAccesibil) {
                iesiri.add(matrice[m-1][j]);
            }
        }
    }
}