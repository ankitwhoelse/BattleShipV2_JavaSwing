package vue;

import modele.Modele;

import javax.swing.*;
import java.awt.*;

public class CadreBatailleNavale extends JFrame implements Runnable{

    PanneauPrincipal panPrincipal = new PanneauPrincipal();
    BarreMenu menu = new BarreMenu(panPrincipal);
    static String nom;

    public void run() {
        configurerCadre();
        configurerContenu();
        setVisible(true);
    }

    private String demanderNom() {
        nom = JOptionPane.showInputDialog(this, "Entrez votre nom svp");

        if (nom == null) {
            nom = "Joueur 01";
        }
        this.nom = nom;

        return nom;
    }

    private void configurerCadre() {
        demanderNom();
        setTitle("Bataille Navaille V2 - " + this.nom);
        setSize(new Dimension(1200,700));
        setResizable(false);

        setJMenuBar(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void configurerContenu(){
        setContentPane(panPrincipal);
    }

    public static String getNom() {
        return nom;
    }
}
