package general;

import java.awt.Color;
/**
 * Solution du tp1A21 inf111
 * 
 * Regroupe tous les SP pour tout ce qui concerne la communication avec le gui
 * Il sert � l'affichage des navires d'un jeu de bataille navale dans un panneau
 * qui repr�sente une grille graphique.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class UtilitaireGrilleGui {

	
	/**
	 * Remet le texte du gui dans leurs conditions initiales general.Constantes.VIDE_GUI
	 * dans chaque case d'une ligne voulue.
	 * 
	 * @param gui
	 * @param ligne
	 */
	private  static void viderCases(PanneauGrilleGui gui, int ligne){
		
		/**
		 * Strat�gie : On utilise for pour parcourir toutes les colonnes
		 *  (puisqu'on connait le nombre)             
		 */
		
		 //N�cessaire � l'appel de gui.setValeur
		Coord c = new Coord(0,0);

		for(int j = 0; j < gui.getNbColonnes(); j++){
			
			c.ligne = ligne;
			c.colonne = j;
			gui.setValeur(c, Constantes.VIDE_GUI);
		}
	}	
	
	/**
	 * Remet les couleurs du gui � general.Constantes.COULEUR_FOND
	 * 
	 * @param gui
	 */
	public  static void reinitialiserGui(PanneauGrilleGui gui){

		//On utilise les SP pr�vus � cet effet
		for(int i = 0; i < gui.getNbLignes(); i++){
			
			changerCouleurFondLigne(gui,i,gui.getBackground());
			viderCases(gui,i);
		}
	}

	/**
	 * Proc�dure priv�e pour r�initialiser le gui et changer la couleur de 
	 * toute une ligne.
	 * 
	 * @param gui
	 * @param ligne
	 * @param couleur
	 */
     private  static void changerCouleurFondLigne(PanneauGrilleGui gui, 
    		 int ligne, Color couleur){

    	 /**
    	  * Strat�gie : On utilise for pour parcourir toutes les colonnes
    	  *  (puisqu'on connait le nombre)             
    	  */
    	 
    	 // N�cessaire � l'appel de gui.setCouleurFond.
		Coord coord = new Coord(0,0);

		for(int j = 0; j < gui.getNbColonnes(); j++){
			
			coord.ligne =ligne;
			coord.colonne = j;
			gui.setCouleurFond(coord, couleur);
		}

	}
	
	
	/**
	 * Montre les navire de la flotte dans le gui.
	 * 
	 * Change la couleur de fond des cases � la position des navires.
	 * 
	 * @param grilleJeu  La grille contenant les navires
	 * @param gui L'interface graphique � remplir
	 * @param pourc Le pourcentage de la grille � mettre dans le gui
	 */
	public static void montrerFlotte(Flotte flotte, PanneauGrilleGui gui){

		/*
		 * Strat�gie : Pour chaque general.Navire de la grille de jeu, on colore le fond
		 * de la position de debut � la position de fin avec la couleur 
		 * du navire.
		 *                  
		 *  Il faut tenir copte s'il est direction NORD_SUD ou EST_OUEST.
		 */

		// On obtient les navires � montrer.
		Navire[] tabNavires = flotte.getTabNavires();
		
		// �vite pls appels aux accesseurs.
		Coord debut;
		Coord fin;
		
		// Une coordonn�e � remplir
		Coord coord = new Coord(0,0);
		
		for(int i = 0; i < tabNavires.length; i++){
			
			debut = tabNavires[i].getCoordDebut();
			fin = tabNavires[i].getCoordFin();
						
			// Pour debug
			Color couleur = tabNavires[i].getCouleur();
			
			// La boucle du debut � la fin du navire en ligne ou en colonne.			
			colorierNavireEnLigne(gui, coord, debut, fin, couleur);
			colorierNavireEnColonne(gui, coord, debut, fin, couleur);			

		}
	}
	
	/*
	 * Principalement pour le d�coupage.
	 */
	private static void colorierNavireEnColonne(PanneauGrilleGui gui, 
			Coord coord, 
			Coord debut, Coord fin, 
			Color couleur){
		
		// Change de ligne � chaque tour.
		for(int ligne = debut.ligne; ligne <= fin.ligne; ligne++){
			coord.ligne = ligne;
			coord.colonne = debut.colonne;
			gui.setCouleurFond(coord, couleur);
		}		
	}

	
	/*
	 * Principalement pour le d�coupage.
	 */
	private static void colorierNavireEnLigne(PanneauGrilleGui gui, 
			Coord coord, 
			Coord debut, Coord fin, 
			Color couleur){
		
		//Change de colonne � chaque tour
		for(int colonne = debut.colonne; colonne <= fin.colonne; colonne++){
			coord.ligne = debut.ligne;
			coord.colonne = colonne;
			gui.setCouleurFond(coord, couleur);
		}
	}

}