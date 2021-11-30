package vue;

import general.Joueur;
import general.Ordi;
import general.PanneauGrilleGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauHaut extends JPanel implements ActionListener {

    PanneauGrilleGui panneauFlotte1;

    PanneauGrilleGui panneauFlotte2 ;

    PanneauGrilleGui panneauMontrerFlotte ;
    Joueur joueur ;
    Ordi ordi;

    PanneauHaut(Joueur joueur, Ordi ordi){
        this.joueur = joueur;
        this.ordi = ordi;

        configurerPanneau();
        configurerContenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void configurerPanneau() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        // A VERIFIER
        double height = (d.height * 0.6) / 3;
        double width = (d.width * 0.6) / 3;

        JLabel lbl1 = new JLabel("panneau flotte 1");
        panneauFlotte1 = new PanneauGrilleGui(new Dimension((int)width,(int)height));

        panneauFlotte2 = new PanneauGrilleGui(new Dimension((int)width,(int)height));

        panneauMontrerFlotte = new PanneauGrilleGui(new Dimension((int)width,(int)height));
    }

    public void configurerContenu() {

        setLayout(new BorderLayout());


        add(panneauFlotte1,BorderLayout.WEST);

        add(panneauFlotte2,BorderLayout.EAST);

    }
}
