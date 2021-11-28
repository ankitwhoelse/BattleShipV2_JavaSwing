package general;

import java.util.LinkedList;

/**
 * general.Joueur qui joue depuis longtemps et a une assez bonne strat�gie.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class OrdiStrategieAvance extends StrategieOrdiAbstrait{

	
	/*
	 * Strat�gie : Les coups sont jou� sur la premi�re diagonale jusqu'� un
	 * touch�.
	 * 
	 * Ensuite, les cases vosines sont enfil�s et tantqu'il y a des coups dans 
	 * la file, on joue un des coups qui s'y trouve.  Si la file redevient vide, 
	 * on continue sur la diagonale.
	 *                  
	 * Lorsqu'on a tout visit� les cases de la premi�re diagonale, 
	 * on fait l'autre.  Si on fait toutwe la deuxi�me diagonale, les coups
	 * sont jou�s au hasard sur une des cases vides restantes 
	 * (coup pas d�j� jou�).
	 *                  
	 */
	
	// Les coups retenus apr�s un touch�.
	private LinkedList<Coord> fileCoupAJoue;
	
	
	// Quelle diagonale on est rendu.
	boolean premiereDiagonale = true;
	boolean deuxiemeDiagonale = false;
	
	// Utilise� globalement entre getTir et aviseTouche pour se rappeler o� on 
	// est rendu en diagonale lorsque la file est vide.
	Coord cDiag;


	/**
	 * Constructeur par d�faut
	 * 
	 */
	public  OrdiStrategieAvance(){
		
		// Une file vide au d�part.
		fileCoupAJoue = new LinkedList<Coord>();
		
		// La premi�re case de la premi�re diagonale.
		cDiag = new Coord(0,0);
	}

	/**
	 * Retourne le coup de l'ordi
	 * 
	 * @return
	 */
	public Coord getTir(){

		/*
		 * Strat�gie.  On utilise le bool�en premiereDiagonale qu'on met
		 * � faux si toute la premi�re diagonale a �t� visit�e.   
		 * M�me chose pour la deuxi�me. 
		 */

		Coord cRetour = new Coord(0,0);

		// Si on a pas de coup � jouer, on joue sur les diagonales.
		if(fileCoupAJoue.isEmpty()){

			// Premi�re diagonale.
			if(premiereDiagonale){

				// Cherche un coup pas jou� sur la diagonale.
				while(super.tirDejaJoue(cDiag) &&	
					cDiag.ligne < Constantes.TAILLE){
					
					cDiag.ligne++;
					cDiag.colonne++;
				}
				
				// On a fini de regarder la premi�re diagonale.
				if(cDiag.ligne == Constantes.TAILLE){
					
					// �osition de la premi�re case de la deuxi�me diagonale.
					cDiag.ligne = 0;
					cDiag.colonne = Constantes.TAILLE - 1;

					premiereDiagonale = false;
					deuxiemeDiagonale = true;
				}
			}

			// Toujours v�rifi� car il est possible qu'on n'ait pas de coup 
			// de la premi�re diagonale, il faut continuer sur la deuxi�me.
			if(deuxiemeDiagonale){

				// Cherche un coup pas jou� sur la 2e diagonale.
				while(super.tirDejaJoue(cDiag) && 
					  cDiag.ligne < Constantes.TAILLE){
					
					cDiag.ligne++;
					cDiag.colonne--;
				}

				// On a fini de regarder la deuxi�me  et on a rien trouver alors 
				// on joue dans une case pas d�j� jou� au hasard.
				if(cDiag.ligne == Constantes.TAILLE){
					
					deuxiemeDiagonale = false;
					cDiag = super.obtenirTirPasDejaJoue();
				}
			}

			
			if(!premiereDiagonale && !deuxiemeDiagonale){
				
				cDiag = super.obtenirTirPasDejaJoue();
			}

			// On affecte la variable de retour.
			cRetour.ligne = cDiag.ligne;
			cRetour.colonne = cDiag.colonne;
		}

		else{

			// On enl�ve les coup qui ont d�j� �t� jou�.
			do{
				cRetour = (Coord)fileCoupAJoue.poll();
			}while(super.tirDejaJoue(cRetour) && !fileCoupAJoue.isEmpty());

			// Si la file redevient vide, on joue un coup pas d�j� jou�.
			if(fileCoupAJoue.isEmpty() &&  super.tirDejaJoue(cRetour))
				cRetour =  super.obtenirTirPasDejaJoue();
		}

		super.ajouterTir(cRetour);

		return cRetour;

	}

	
	public void aviserTouche(){		

		/*
		 * Strat�gie, on v�rifie les limites avant d'enfiler.
		 * 
		 * Juste 4 voisins possibles alors on a pas fait de boucle.
		 */


		Coord c = super.getDernierTir();
		Coord cPush = null;

		//NORD
		if(c.ligne>0){ 
			
			cPush = new Coord(c.ligne-1, c.colonne);
			fileCoupAJoue.add(cPush);
		}		

		//SUD
		if(c.ligne < Constantes.TAILLE - 1){
			
			cPush = new Coord(c.ligne+1, c.colonne);		
			fileCoupAJoue.add(cPush);
		}

		//EST
		if(c.colonne < Constantes.TAILLE - 1){
			
			cPush = new Coord(c.ligne, c.colonne+1);		
			fileCoupAJoue.add(cPush);
		}

		//OUEST
		if(c.colonne>0){
			
			cPush = new Coord(c.ligne, c.colonne - 1);		
			fileCoupAJoue.add(cPush);
		}
	}
}