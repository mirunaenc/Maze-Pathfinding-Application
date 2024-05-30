import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Citirea matricei labirintului dintr-un fisier
        Node[][] matrice = null;
        Node start = null;
        try {
            Scanner scanner = new Scanner(new File("D:\\Facultate\\Anul II\\AG\\Tema 2 AG\\src\\myFile.txt"));
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            matrice = new Node[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int valoare = scanner.nextInt();
                    Node nod = new Node(i, j,valoare);
                    nod.esteAccesibil = (valoare != 0);
                    if (valoare == 2) {
                        start = nod;
                    }
                    matrice[i][j] = nod;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit.");
            e.printStackTrace();
        }

        // Crearea labirintului si a panoului
        Labirint labirint = new Labirint(matrice, start);
        MyPanel myPanel = new MyPanel(labirint);

        // Crearea ferestrei principale
        JFrame frame = new JFrame("Labirint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(myPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}