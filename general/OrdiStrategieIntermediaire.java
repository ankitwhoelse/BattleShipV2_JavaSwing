package general;

import java.util.LinkedList;

/**
 * general.Joueur qui joue depuis un bout de temps et a commenc� � se d�velopper une
 * strat�gie pas si mal mais pas excellente.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class OrdiStrategieIntermediaire extends StrategieOrdiAbstrait{

	// Retient les tirs � l'entours d'un tir qui a touch�.
	private LinkedList<Coord> fileCoupAJoue;
	
	/*
	 * Strat�gie : Lorsqu'on est avis� d'un touch�, on retient les quatre 
	 * coordonn�es voisines dans une file.
	 *                  
	 * Au moment de jouer, si la file n'est pas vide, 
	 * on y prend le prochain tir.
	 *                  
	 */

	/**
	 * Constructeur par d�faut
	 */
	public OrdiStrategieIntermediaire(){
		
		// Aucun coup jou� mais file initialis�e.
		fileCoupAJoue = new LinkedList<Coord>();
	}

	/**
	 * Retourne le tir de l'ordi
	 * 
	 * @return
	 */
		public Coord getTir(){

		/*
		 *  Strat�gie.  Retourne un coup au hasard qui n'a pas d�j� t� jou� � 
		 *  l'aide de l'utilitaire sur les collections.
		 *                   
		 *  Si la file n'est pas vide, il joue un de ces tirs qui n'a pas d�j� 
		 *  �t� jou�s.
		 *                               
		 *  Dans aviserTouche, il retient dans la file les coups adjacents
		 *  au dernier tir qui a touch�.
		 */
		Coord c;

		if(fileCoupAJoue.isEmpty()){
			
			c =  super.obtenirTirPasDejaJoue();
		}

		else{
			

			// On passe les tirs qui ont d�j� �t� jou�s qui peuvent se trouver
			// dans la file.
			do{
			      c = fileCoupAJoue.poll();
			      
			}while(super.tirDejaJoue(c) && !fileCoupAJoue.isEmpty());
			
			// Si la file redevient vide, on joue un coup pas d�j� jou�.
			if(fileCoupAJoue.isEmpty() &&  super.tirDejaJoue(c)) {
				
				c = super.obtenirTirPasDejaJoue();
			}
		}

		super.ajouterTir(c);
		

		return c;

	}

	public void aviserTouche(){		

		/*
		 * Strat�gie, on v�rifie les limites avant d'empiler.
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