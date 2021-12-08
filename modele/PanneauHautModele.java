package modele;

import general.Flotte;
import general.PanneauGrilleGui;
import general.UtilitaireGrilleGui;

public class PanneauHautModele {

    private static PanneauHautModele instance = new PanneauHautModele();
    private PanneauHautModele(){}

    public static PanneauHautModele getInstance(){
        return instance;
    }

    public void GrilleFlotte(Flotte flotte, PanneauGrilleGui gui){
        UtilitaireGrilleGui.montrerFlotte(flotte,gui);
    }

}
