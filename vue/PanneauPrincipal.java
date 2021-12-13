package vue;

import general.Joueur;
import general.Ordi;
import modele.Modele;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipal extends JFrame implements Runnable{

    JPanel panPrincipal = new JPanel();
    Joueur joueur = new Joueur("Joueur1");
    Ordi ordi = new Ordi();



    PanneauHaut panHaut = new PanneauHaut(joueur,ordi);
    PanneauBas panBas = new PanneauBas(panHaut);

    BarreMenu menu = new BarreMenu();
    @Override
    public void run() {
         Modele.getInstance().attacherObserver(panBas);

        configurerCadre();
        configurerContenu();
        setVisible(true);
    }


    private void configurerCadre() {
        setTitle("PanneauHaut");

        setSize(new Dimension(1200,700));
        setResizable(false);

        setJMenuBar(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurerContenu() {
        // Panneau Principal
        setContentPane(panPrincipal);
        panPrincipal.setBackground(Color.lightGray);
        panPrincipal.setLayout(new FlowLayout());

        panPrincipal.add(panHaut);
        panPrincipal.add(panBas);

    }

}