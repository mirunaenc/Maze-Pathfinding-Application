import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MyPanel extends JPanel {
    Labirint labirint;
    // Constructor
    public MyPanel(Labirint labirint) {
        this.labirint = labirint;

        JButton button = new JButton("Aplica BFS");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Creez un Timer care ruleaza la fiecare secunda
                Timer timer = new Timer(1000, null);
                timer.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!labirint.iesiri.isEmpty()) {
                            // Aplic BFS pentru urmatoarea iesire
                            Node iesire = labirint.iesiri.get(0);
                            labirint.iesiri.remove(0);
                            List<Node> drum = labirint.gasesteDrum(labirint.start, iesire);
                            if (drum != null) {
                                // Daca exista un drum catre iesire, colorez nodurile din drum in verde
                                for (Node nod : drum) {
                                    nod.esteInDrum = true;
                                }
                            } else {
                                // Daca nu exista un drum catre iesire, colorez iesirea in rosu
                                iesire.esteAccesibil = false;
                            }
                        } else {
                            // Daca toate iesirile au fost procesate, opresc Timer-ul
                            ((Timer) e.getSource()).stop();
                        }
                        repaint();  // Redesenez panoul pentru a afisa rezultatele
                    }
                });
                timer.start();  // Pornesc Timer-ul
            }
        });
        add(button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int latimeFereastra = getWidth();
        int inaltimeFereastra = getHeight();

        int numarCeluleLatime = labirint.matrice[0].length;
        int numarCeluleInaltime = labirint.matrice.length;

        int latimeCelula = latimeFereastra / numarCeluleLatime;
        int inaltimeCelula = inaltimeFereastra / numarCeluleInaltime;

        for (int i = 0; i < numarCeluleInaltime; i++) {
            for (int j = 0; j < numarCeluleLatime; j++) {
                Node nod = labirint.matrice[i][j];
                if (nod.esteZid) {
                    g.setColor(Color.BLACK);  // daca nodul este un zid, il colorez cu negru
                } else if (!nod.esteAccesibil) {
                    g.setColor(Color.RED);  // daca nodul nu este accesibil, il colorez cu rosu
                } else if (nod.esteInDrum && !nod.equals(labirint.start)) {
                    g.setColor(Color.GREEN);  // daca nodul face parte dintr-un drum, il colorez cu verde
                } else if (nod.equals(labirint.start)) {
                    g.setColor(Color.BLUE);  // daca nodul este nodul de start, il colorez cu albastru
                } else if (nod.valoare == 1) {
                    g.setColor(Color.WHITE);  // daca nodul este o cale de acces, il colorez cu alb
                }

                g.fillRect(j * latimeCelula, i * inaltimeCelula, latimeCelula, inaltimeCelula);
            }
        }
    }
}