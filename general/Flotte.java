package general;

import java.util.Vector;

import java.awt.Color;

/**
 * Collection de navires dans l'impl�mentation d'un jeu de Bataille Navale 
 * 
 * Elle conserve les navires pour une aire de jeu.  Le contructeur doit recevoir 
 * la taille de la grille carr�e qui contient les navires.
 * 
 * Si les coordonn�es d'un navire ajout� sont invalides, le navire n'est pas 
 * ajout� et un code d'erreur est retourn�.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class Flotte{

	/*
	 * Strat�gie : On ne cr�e pas de grille 2D, on ne retient que la 
	 * taille pour la validation des coordonn�es des navires ajout�s.
	 */

	//**********************************
	//CONSTANTES DE BATTLESHIP
	//**********************************           
	/**message lorsqu'une position n'est pas dans la grille*/
	public static final int POSITIONINVALIDE = 0;

	/**message lorsqu'un navire a �t� correctement ajout�*/
	public static final int NAVIREAJOUTE = 7;

	/**message lorsqu'un navire est d�j� � une position donn�e*/
	public static final int NAVIREEXISTANT = 8;

	/**haut*/
	public static final int NORD = 1;

	/**bas*/
	public static final int SUD = 2;

	/**droite*/
	public static final int EST = 3;

	/**gauche*/
	public static final int OUEST = 4;           

	//******************************
	// ATTRIBUTS DE LA FLOTTE
	//******************************

	// On conserve les navires dans java.util.Vector (voir api)
	// pour utiliser ses m�thodes d�j� �crites (add, contains).
	private Vector<Navire> tabNavires;


	// Sert � retenir si le dernier tir sur la flotte a coul� un bateau.
	private boolean coule;

	private String dernierNavireCoule;

	//******************
	// CONSTRUCTEURS
	//******************
	/**
	 * Constructeur par d�faut  qui cr�e une flotte sans navire
	 * 
	 */
	public Flotte(){

		// On cr�e la collection de navires vide.
		this.tabNavires = new Vector<Navire>();
	}


	/**
	 * Retourne si le dernier tir que la flotte a re�u
	 * a coul� un navire.
	 * @return Le dernier tir  re�u a coul� un navire de la flotte
	 */
	public boolean dernierTirACoule(){        	   

		// On retourne la valeur de l'attribut qui est affect� dans
		// la m�thode leTirTouche.
		return coule;
	}

	/**
	 * Ajoute le navire dans la grille � sa position en tenant compte de
	 * la taille en constante.  La position doit �tre valide et ne rien 
	 * chevaucher.
	 * 
	 * @param navire le navire � ajouter
	 * 
	 * @return NAVIREAJOUTE si l'ajout a �t� effectu� <p>
	 *         NAVIREEXISTANT s'il y avait d�j� un navire
	 *         POSITIONINVALIDE si la position n'est pas dans la grille
	 */           
	public int ajouterNavire(Navire navire){               

		/*
		 * Strat�gie : On ajoute le navire � la fin du vecteur navires
		 * � moins que la position soit invalide.
		 *
		 * La t�che de v�rifier le chevauchement et la valitid� des 
		 * coordonn�es est d�l�gu� � la classe general.Navire.
		 *
		 * La v�rification de la validit� est faite localement 
		 * (pourrait �tre une fonction)
		 */

		// Code retourne si ajout�.
		int codeErreur = NAVIREAJOUTE;

		// On v�rifie que le navire n'en chevauche pas un autre.
		for(int i = 0; i <tabNavires.size();i++)
			if(navire.chevauche((Navire)tabNavires.get(i)))
				codeErreur =  NAVIREEXISTANT;

		// S'il n'a rien chevauch�, la position doit �tre valide.
		if(codeErreur == NAVIREAJOUTE){

			if(navire.getCoordDebut().ligne < 0 || 
					navire.getCoordDebut().ligne  >= Constantes.TAILLE ||
					navire.getCoordFin().colonne  < 0 ||
					navire.getCoordFin().colonne >= Constantes.TAILLE){

				codeErreur = POSITIONINVALIDE;
			}

			else{

				// On peut ajouter le navire.
				tabNavires.add(navire);                          
			}
		}

		return codeErreur;
	}


	/**
	 * Retourne si tous les navires de la flotte sont coul�s.
	 * 
	 * La partie est alors termin�e.
	 * 
	 * @return true si tous les navires ont �t� coul�s.
	 */
	public boolean jeuEstTermine(){
		
		int nb = 0;

		for (int i = 0; i < tabNavires.size(); i++){

			if(tabNavires.get(i).estCoule()) {

				nb++;
			}
		}

		return tabNavires.size() == nb;
	}           

	/**
	 * Retourne une copie du tableau de navires
	 * 
	 * @return Une copie du tableau de navires
	 */
	public Navire[] getTabNavires(){
		
		// Simplement.
		
		return  tabNavires.toArray(new Navire[tabNavires.size()]);
	}

	/**
	 * Fabrique d'une flotte avec des navires positionn� al�atoirement.
	 * 
	 * @return Une flotte avec des navires ayant des positions valides
	 */
	public static Flotte obtenirFlotteAleatoire(){

		Flotte flotte = new Flotte();

		// Ajouter les navires dans la grille de jeu est fait localement.
		flotte.genererPosNavireAleaInsererDsGrille();

		return flotte;
	}


	//************************************************
	//GENERER NAVIRE ET INSERER DANS GRILLE
	//************************************************
	/*
	 * G�n�re 5 bateaux al�atoirement qui ne se chevauchent pas
	 */
	private void genererPosNavireAleaInsererDsGrille() {

		/*
		 * Strat�gie : le SP ajouterNavire ajoute un navire seulement si
		 * sa position est valide (coord valide et pas de chevauchement).
		 *                  
		 * obtenirNavireAleatoire retourne un navire avec le nom
		 * et la longueur demand� positionn� au hasard sur la grille.
		 */

		// On cr�e un porte-avion tant que ce n'est pas valide.

		// On met sur pls lignes pour respecter la norme du 80 colonnes
		// m�me si cela s'�crit en 1 ligne.  On �vite de d�clarer des 
		// variables inutilement.
		while(ajouterNavire(
				obtenirNavireAleatoire(Constantes.PORTE_AVION, 
						5,
						Color.BLUE)) != NAVIREAJOUTE);

		while(ajouterNavire(
				obtenirNavireAleatoire(Constantes.CUIRASSE, 
						4, 
						Color.YELLOW)) != NAVIREAJOUTE);

		while(ajouterNavire(
				obtenirNavireAleatoire(Constantes.SOUS_MARIN, 
						3, 
						Color.CYAN)) != NAVIREAJOUTE);

		while(ajouterNavire(
				obtenirNavireAleatoire(Constantes.DESTROYER, 
						3,
						Color.GREEN)) != NAVIREAJOUTE);

		while(ajouterNavire(
				obtenirNavireAleatoire(Constantes.CROISEUR, 
						2, 
						Color.GRAY)) !=NAVIREAJOUTE);


	}

	/**
	 * Retourne si un des navires de la flotte a d�j� re�u un tir.
	 * 
	 * @param tir
	 * @return
	 */
	public boolean dejaRecuCoup(Coord tir){

		/*
		 * Strat�gie : On d�l�gue cela aux navires, en boucle.
		 */
		int i = 0;

		boolean dejaRecu = false;

		while(i < tabNavires.size() && !dejaRecu){

			dejaRecu = tabNavires.get(i).dejaRecuCoup(tir);
			i++;
		}

		return dejaRecu;
	}

	/**
	 * Cr�e un bateau avec le nom re�u de la longueur voulue 
	 * � une position al�atoire.
	 * 
	 * @param nom
	 * @param longueur
	 * @return
	 */
	private Navire obtenirNavireAleatoire(String nom,
			int longueur, 
			Color couleur){

		/*
		 * Strat�gie : On g�n�re une position dans la grille et on choisit 
		 * une direction au hasard (NORD, SUD, EST ou OUEST).
		 *                  
		 * Tant que la position + la longueur dans la direction est 
		 * impossible, on g�n�re une nouvelle position et une autre 
		 * direction.
		 *                  
		 */

		// Coordonn�es valide � g�n�rer.
		Coord debut = new Coord(0,0);
		Coord fin = new Coord(0,0);

		// � g�n�rer au hasard.
		int direction;

		// general.Navire � retourner.
		Navire navire = null;

		// Mis vrai lorsque le constructeur de navire arr�te d'envoyer 
		// des exceptions.
		boolean navireAjoute = false;

		while(!navireAjoute){

			try {

				// Cette fonction retourne une position dans la grille o� 
				// �a rentre
				debut = UtilitaireFonctions.coordAleatoire(longueur);

				// Retourne NORD, EST, OUEST ou SUD.
				direction = UtilitaireFonctions.nbAlea(NORD, OUEST);

				// Ajuste la fin par rapport au d�but et les ordonne.
				ajusterPosition(debut, fin, direction, longueur);

				// On tente un nouveau navire qui l;�verea une exception
				// si les coordonn�es sont invalides.
				navire =  new Navire(nom, debut, fin, couleur);

				// Si on est ici, il n'y a pas eu de message d'exception.
				navireAjoute = true;

			} catch (Exception e) {

				// On affiche la trace et la boucle recommence.
				e.printStackTrace();
			}
		}

		// C'est certain que les coordonn�es du navire sont dans la grille.
		return navire;
	}          

	/**
	 * Proc�dure priv�e locale pour ajuster le d�but et la fin selon la
	 * direction et la longueur.  Principale pour le d�tirage en SP.
	 * 
	 * On veut les donner en ordre de la ligne et la colonne.
	 * debut vient avant fin de haut en bas et de gauche � droite
	 * 
	 * @param debut Doit avoir une coordonn�e valide
	 * @param fin Sera calcul�
	 * @param direction NORD SUD EST ou OUEST
	 * @param longueur longueur valide selon la taille des navires
	 */
	private static void ajusterPosition(Coord debut, 
			                            Coord fin, 
			                            int direction, 
			                            int longueur){

		/*
		 * Strat�gie : Selon la direction, on ajuste la fin pour que le 
		 * d�but vienne avant en ligne ou en colonne.
		 */
		fin.ligne = debut.ligne;

		switch(direction){

		case NORD : {
			fin.colonne = debut.colonne;
			debut.colonne = debut.colonne - longueur + 1;
			break;
		}
		case EST :{
			fin.ligne = debut.ligne;
			fin.colonne = debut.colonne  + longueur - 1;
			break;
		}
		case OUEST : {
			fin.colonne = debut.colonne;
			debut.ligne = debut.ligne - longueur + 1;
			break;
		}
		case SUD : {
			fin.colonne = debut.colonne + longueur - 1;
		}
		}
	}
	/**
	 * Re�oit un tir sur la flotte.  Retourne si un navire est touch�.
	 * 
	 * @param tir Le tir � consid�r�
	 * 
	 * @return true si le tir a touch� � un des navires.
	 */
	public boolean leTirTouche(Coord tir){

		/*
		 * Strat�gie : On regarde si le tir touche � un des navires � 
		 * l'aide d'un while et la boucle se termine d�s qu'on a 
		 * la r�ponse.
		 *                  
		 * On d�l�gue la t�che de v�rifier si le  tirAtouche � la classe 
		 * general.Navire.
		 *                                    
		 */
		boolean touche = false;

		int i = 0;

		while (i < tabNavires.size() && !touche){

			Navire nav = tabNavires.get(i);

			touche = nav.tirAtouche(tir);

			// On retient si le dernier tir a coul� un navire.
			// Utile au d�roulement du jeu.
			coule = nav.estCoule();

			dernierNavireCoule = nav.getNom();

			i++;
		}


		return touche;

	}


	/**
	 * Retourne le nom du dernier bateau coul�.
	 * 
	 * @return Le nom du dernier bateau coul�.
	 */
	public String getDernierNavireCoule() {


		return dernierNavireCoule;
	}


	/**
	 * Retourne une copie de la flotte actuelle � une nouvelle adresse.
	 * 
	 * @return Une nouvelle flotte avec les m�mes attributs que
	 *         la flotte actuelle
	 */
	public Flotte clone() {

		Flotte f = new Flotte();

		f.tabNavires = (Vector<Navire>)tabNavires.clone();

		f.coule = coule;

		f.dernierNavireCoule = dernierNavireCoule;

		return f;
	}           
}
