package vue;

import general.*;
import modele.PanneauHautModele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauHaut extends JPanel implements ActionListener {

    JPanel panelJoueur = new JPanel();
    JPanel panelOrdi = new JPanel();

    PanneauGrilleGui panneauFlotteJoueur;

    PanneauGrilleGui panneauFlotteOrdi ;

    PanneauGrilleGui panneauMontrerFlotte ;
    Joueur joueur ;
    Ordi ordi;
    PanneauHautModele modele = PanneauHautModele.getInstance();


    PanneauHaut(Joueur joueur, Ordi ordi){
        this.joueur = joueur;
        this.ordi = ordi;

        configurerPanneau();
        configurerContenu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(panneauFlotteJoueur.getPosition());
    }

    public void configurerPanneau() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        // A VERIFIER
        double height = (d.height * 0.6) / 2;
        double width = (d.width * 0.6) / 2;

        JLabel lbl1 = new JLabel("Joueur");
        JLabel lbl2 = new JLabel("Ordi");



        panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.Y_AXIS));
        panneauFlotteJoueur = new PanneauGrilleGui(new Dimension((int)width,(int)height));
        panelJoueur.add(lbl1);
        panelJoueur.add(panneauFlotteJoueur);


        panelOrdi.setLayout(new BoxLayout(panelOrdi, BoxLayout.Y_AXIS));
        panneauFlotteOrdi = new PanneauGrilleGui(new Dimension((int)width,(int)height));
        panelOrdi.add(lbl2);
        panelOrdi.add(panneauFlotteOrdi);

        panneauMontrerFlotte = new PanneauGrilleGui(new Dimension((int)width,(int)height));
    }

    public void configurerContenu() {

        setLayout(new BorderLayout());

        joueur.genereNouvelleFlotte();
        Flotte flotteJoueur = joueur.getFlotte();

        modele.GrilleFlotte(flotteJoueur,panneauFlotteJoueur);

        ordi.genereNouvelleFlotte();
        Flotte flotteOrdi = ordi.getFlotte();
        modele.GrilleFlotte(flotteOrdi,panneauFlotteOrdi);

        add(panelJoueur,BorderLayout.WEST);

        add(panelOrdi,BorderLayout.EAST);

    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Ordi getOrdi() {
        return ordi;
    }

    public Coord getTirJoueur(){
        return panneauFlotteJoueur.getPosition();
    }

    public boolean estClique(){
        return panneauFlotteJoueur.caseEstCliquee();
    }

    //3.2.6 Les méthodes utiles au déroulement du jeu

    public void AfficherTirJoueur(Coord tir){
        panneauFlotteJoueur.setValeur(tir, Constantes.TOUCHE);
    }

    public void AfficherTirOrdi(Coord tir){
        panneauFlotteOrdi.setValeur(tir, Constantes.TOUCHE);
    }
    public void caseToucheJoueur(Coord tir){
        panneauFlotteJoueur.setCouleurFond(tir, Constantes.COULEUR_LIGNE_IDENTIQUE);
    }
    public void caseToucheOrdi(Coord tir){
        panneauFlotteOrdi.setCouleurFond(tir, Constantes.COULEUR_LIGNE_IDENTIQUE);
    }

    public void montreFlotteJoueur(){
        UtilitaireGrilleGui.montrerFlotte(joueur.getFlotte(),panneauFlotteJoueur);
    }
    public void montreFlotteOrdi(){
        UtilitaireGrilleGui.montrerFlotte(ordi.getFlotte(),panneauFlotteOrdi);
    }
    public void desactiverCaseOrdi(Coord tir){
        panneauFlotteOrdi.desactiverCase(tir);
    }

    public void reinitialiserPanneauOrdi(Coord tir){
     //  panneauFlotteOrdi = new PanneauGrilleGui();
    }


}
