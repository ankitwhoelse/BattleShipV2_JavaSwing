package general;

import java.awt.Color;
import java.util.Vector;

/**
 * Repondre � l'�nonc� du tp3 A21 pour cette partie.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class Navire {

	// Calculer au constructeur.
	private int nbCases;

	// Le nom (pas vraiment utilis�).
	private String nom;

	// Le nombre de touche par l'ennemi.
	private int nbToucheEnnemi;

	// Les  general.Coord qui repr�sente les cases sur
	// lesquelles le  navire a �t� plac�.
	private Coord debut;
	private Coord fin;

	private Color couleur;

	private Vector<Coord> tabCoups;
	
	/**
	 * Les positions doivent �tre ordonn�es.
	 * 
	 * Aucune validation.
	 * 
	 * @param nbCases
	 */
	public Navire(String nom, Coord debut, Coord fin, Color couleur)
			      throws Exception{


		int nbLignes = (fin.ligne - debut.ligne + 1);
		int nbColonnes = (fin.colonne - debut.colonne + 1);

		if(nbLignes > 1 && debut.colonne != fin.colonne) {
			
			throw new Exception("NORD_SUD invalide");
		}
			

		if(nbColonnes > 1 && debut.ligne != fin.ligne) {
			
			throw new Exception("EST_OUEST invalide");			
		}
			

		if(debut.ligne > fin.ligne || debut.ligne < 0) {
			
			throw new Exception("ligne invalide");
		}

		if(debut.colonne > fin.colonne || debut.colonne < 0) {
			
			throw new Exception("colonne invalide");
		}

		this.nom = nom;
		this.debut = debut;
		this.fin = fin;

		this.couleur = couleur;

		nbCases =  (nbLignes > 1)?nbLignes:nbColonnes; 

		tabCoups = new Vector<Coord>();

	}


	/**
	 * Accesseur de couleur
	 * 
	 * @return
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * Mutateur de la couleur
	 * 
	 * @param couleur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}



	// Un service boolean estCoule() qui retourne vrai si toutes les cases du  
	// ont �t� touch�es.
	public  boolean estCoule(){	
		
		return nbToucheEnnemi == nbCases;
	}

	/**
	 * Fonction locale pour �viter la r�p�tition de code
	 * qui retourne si le coup touche au navire actuel
	 * 
	 * Il touche s'il est sur la m�me ligne ou sur la m�me colonne
	 * dans l'intervalle entre debut et fin de l'autre dimension.
	 * 
	 * @param coup
	 * @return
	 */
	private boolean positionTouche(Coord coup){

		boolean touche = (coup.ligne >= debut.ligne && 
				coup.ligne <= fin.ligne && 
				coup.colonne == debut.colonne) || 
				(coup.colonne >= debut.colonne && 
				coup.colonne <= fin.colonne &&
				coup.ligne == debut.ligne);
		
		return touche;
	}


	/**
	 * Retourne si le coup en param�tre touche au navire.
	 * Le navire retient s'il a �t� touch�.
	 * 
	 * @param coup Le coup re�u
	 * 
	 * @return Si le coup a touch� le bateau
	 */
	public boolean tirAtouche(Coord coup){

		boolean touche = false;

		// Si le navire est coul�, il n'a pas �t� touch� par ce dernier coup
		if(!estCoule()){

			// S'il a d�j� �t� atteint, on ne fait rien
			if(!UtilitaireCollection.collectionContientCoord(tabCoups, coup))

				// Fonction locale qui regarde si le coup touche au navire 
				// actuel.
				if( positionTouche(coup))  {

					touche = true;		

					// On retient le coup et on compte un touch� de plus.
					tabCoups.add(coup);

					// Un touch� de plus.
					nbToucheEnnemi++;					
				}					
		}

		return touche;
	}

	/**
	 * V�rifie si le navire re�u et l'actuel se touchent
	 * 
	 * @param bateau le bateau � v�rifier
	 * @return true s'ils se touchent et false sinon
	 */
	public boolean chevauche(Navire navire){

		/*
		 * Strat�gie : Pour chaque coordonn�e du navire re�u, on regarde q'il 
		 * touche au navire actuel (this) . La fonction se termine aussit�t que 
		 * la r�ponse est connue
		 */
		// Compte les coordonn�es v�rifi�es.
		int compteur = 0;

		// Fini si on a v�rifi� toutes les coordonn�es.
		boolean touche = false;

		Coord c = new Coord(0,0);
		
        // On veut arr�ter si on s'aper�oit qu'il y a une touche.
		while(compteur < navire.nbCases && !touche){

			// NORD_SUD
			if(	navire.fin.ligne - navire.debut.ligne + 1 > 1){
				c.ligne = navire.debut.ligne + compteur;
				c.colonne = navire.debut.colonne;
			}

			// EST_OUEST
			else{
				c.ligne = navire.debut.ligne ;
				c.colonne = navire.debut.colonne + compteur;
			}					

			touche = positionTouche(c);

			compteur++;
		}
		return touche;
	}

	/**
	 * Reoturne si le tir a d�j� �t� re�u par le navire.
	 * 
	 * @param coup
	 * @return
	 */
	public boolean dejaRecuCoup(Coord tir){
		
		return UtilitaireCollection.collectionContientCoord(tabCoups, tir);
	}
	
	/*
	 * @return les attributs du navire
	 */
	public String toString(){
		
		return nom + " :  (" + debut.ligne + "," + debut.colonne  + ")" + " " + 
		                  "(" + fin.ligne + "," + fin.colonne + ")"  + 
				           "longueur : " + nbCases;
	}

	/**
	 * Accesseur du nom
	 * 
	 * @return
	 */
	public String getNom() {
		
		return nom;
	}

	/**
	 * Mutateur du nom
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		
		this.nom = nom;
	}

	/**
	 * Acesseur de la coordonn�e de d�but du navire.
	 * 
	 * @return
	 */
	public Coord getCoordDebut() {
		
		return debut;
	}

	/**
	 * Mutateur de la coordonn�e de d�but du navire
	 * 
	 * @param debut
	 */
	public void setCoordDebut(Coord debut) {
		
		this.debut = debut;
	}

	/**
	 * Acesseur de la coordonn�e de fin du navire.
	 * 
	 * @return
	 */
	public Coord getCoordFin() {
		
		return fin;
	}

	/**
	 * Mutateur de la coordonn�e de fin du navire
	 * 
	 * @param debut
	 */
	public void setCoordFin(Coord fin) {
		
		this.fin = fin;
	}
	
	
	/**
	 * Retourne une copie du navire actuel.
	 * 
	 * @return Un navire avec les m�mes attributs � des adresses diff�rentes.
	 */
	public Navire clone() {
		
		Navire n = null;
		
		try {

			// Il est important que clone() de general.Coord existe.
			n = new Navire(nom, debut.clone(), fin.clone(), couleur);
			
			// Calculer au constructeur.
			n.nbCases = nbCases;

			// Le nombre de touche par l'ennemi.
			n.nbToucheEnnemi = nbToucheEnnemi;
			
			n.tabCoups = (Vector<Coord>)tabCoups.clone();

		}catch(Exception e) {}
		
		

		return n;
	}
}