package vue;

import general.Coord;
import general.UtilitaireGrilleGui;
import modele.Modele;
import observer.MonObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class PanneauBas extends JPanel implements ActionListener , MonObserver {


    JButton btnNouvellePartie = new JButton("Nouvelle partie");
    JButton btnCacherFlotte = new JButton("Montrer la flotte de l'ordinateur");
    boolean booPartieEnCours = false;
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

            if(booPartieEnCours){
                JOptionPane.showMessageDialog(this,"La partie actuelle est annuler.");
            }
            booPartieEnCours = true;
            AtomicInteger nbTourJoueur = new AtomicInteger();
            AtomicInteger nbTourOrdi = new AtomicInteger();
            panneauHaut.viderCases();
            panneauHaut.joueur.genereNouvelleFlotte();
            panneauHaut.montreFlotteJoueur();
            panneauHaut.reinitialiserPanneauOrdi();


            btnCacherFlotte.setEnabled(true);
            btnCacherFlotte.setText("Montrer la flotte de l'ordinateur");



            Runnable code = () -> {

                while (!panneauHaut.ordi.jeuEstTermine() || !panneauHaut.ordi.jeuEstTermine()){

                    //SI PANNEAU FLOOTE JOUEUR A RECU UN CLIQUE DONC CEST LE TOUR DU L ORDI
                    if(panneauHaut.panneauFlotteJoueur.caseEstCliquee()){
                        //tour de l ordi
                        nbTourOrdi.incrementAndGet();

                    }
                    if(panneauHaut.panneauFlotteOrdi.caseEstCliquee()){
                        // tour du joueur
                        Coord tir = panneauHaut.panneauFlotteOrdi.getPosition();
                        panneauHaut.AfficherTirJoueur(tir);
                        if(panneauHaut.ordi.dernierTirACoule()){
                            JOptionPane.showMessageDialog(this,
                                    panneauHaut.ordi.getDernierNavireCoule() + " coulé");
                        }

                        nbTourJoueur.incrementAndGet();
                    }
                }

                if(panneauHaut.ordi.jeuEstTermine()){
                    JOptionPane.showMessageDialog(this,
                            panneauHaut.joueur.getNom()+ " a gagné en " + nbTourJoueur + " tirs.");
                    booPartieEnCours = false;
                }
                if(panneauHaut.joueur.jeuEstTermine()){
                    JOptionPane.showMessageDialog(this,
                            "Ordi a gagné en " + nbTourOrdi + " tirs.");
                    booPartieEnCours = false;
                }
            };
            Thread t = new Thread(code);
            t.start();

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
        btnCacherFlotte.setEnabled(false);

    }

    @Override
    public void avertir(Modele modele) {
     //   panneauHaut.AfficherTirJoueur(modele.getDernierClic());
    }



}
