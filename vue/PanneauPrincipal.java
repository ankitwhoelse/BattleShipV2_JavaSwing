package vue;

import general.Joueur;
import general.Ordi;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipal extends JFrame implements Runnable{

    JPanel panPrincipal = new JPanel();

    Joueur joueur = new Joueur("Joueur1");
    Ordi ordi = new Ordi();
    PanneauHaut panHaut = new PanneauHaut(joueur,ordi);


    @Override
    public void run() {

        configurerCadre();
        configurerContenu();

        setVisible(true);
    }


    private void configurerCadre() {
        setTitle("PanneauHaut");

        setSize(new Dimension(1200,700));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurerContenu() {

        setContentPane(panPrincipal);
        panPrincipal.setBackground(Color.RED);
        panPrincipal.setLayout(new BorderLayout());


      //  panPrincipal.add(panHaut, BorderLayout.PAGE_START);
    }



}