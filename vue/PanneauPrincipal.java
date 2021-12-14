package vue;

import general.Joueur;
import general.Ordi;
import modele.Modele;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipal extends JPanel{

    Joueur joueur = new Joueur("Joueur1");
    Ordi ordi = new Ordi();

    PanneauHaut panHaut = new PanneauHaut(joueur,ordi);
    PanneauBas panBas = new PanneauBas(panHaut);

    PanneauPrincipal(){
        Modele.getInstance().attacherObserver(panBas);

        configurerContenu();
    }


    private void configurerContenu() {
        // Panneau Principal
        setBackground(Color.lightGray);
        setLayout(new FlowLayout());

        add(panHaut);
        add(panBas);
    }

}