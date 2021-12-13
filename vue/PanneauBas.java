package vue;

import modele.Modele;
import observer.MonObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauBas extends JPanel implements ActionListener , MonObserver {


    JButton btnNouvellePartie = new JButton("Nouvelle partie");
    JButton btnCacherFlotte = new JButton("Montrer la flotte de l'ordinateur");
    boolean booPariteEnCours = false;
    String participant = "";
    PanneauHaut panneauHaut;
    PanneauBas(PanneauHaut panneauHaut){
        this.panneauHaut = panneauHaut;
        configurerPanneau();
        configurerContenu();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Nouvelle partie"){
           // JOptionPane.showMessageDialog(this,"La partie actuelle est terminer.");
            panneauHaut.joueur.genereNouvelleFlotte();
            panneauHaut.montreFlotteJoueur();
            panneauHaut.ordi.genereNouvelleFlotte();
            panneauHaut.panneauFlotteOrdiCopie = panneauHaut.panneauFlotteOrdi.clone();


        }else if(e.getActionCommand() == "Cacher la flotte de l'ordinateur"){
            btnCacherFlotte.setText("Montrer la flotte de l'ordinateur");
            panneauHaut.cacherFlotteOrdi();

        }else if(e.getActionCommand() == "Montrer la flotte de l'ordinateur"){
            btnCacherFlotte.setText("Cacher la flotte de l'ordinateur");
            panneauHaut.montreFlotteOrdi();
        }
    }


    public void configurerPanneau() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        // A VERIFIER
        int height = (int) (d.height * 0.4);
        int width = (int) (d.width * 0.4);
        setSize(new Dimension(height,width));
    }

    public void configurerContenu() {
        setLayout(new FlowLayout());

        btnCacherFlotte.addActionListener(this);
        btnNouvellePartie.addActionListener(this);

        add(btnNouvellePartie);
        add(btnCacherFlotte);

    }

    @Override
    public void avertir(Modele modele) {
        System.out.println(modele.getDernierClic());
        panneauHaut.AfficherTirJoueur(modele.getDernierClic());

    }



}
