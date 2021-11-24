package general;

/**
 * Cet enregistrement repr�sente les coordonn�es 
 * possible dans diff�rents jeux de grille.
 * 
 *@author Pierre B�lisle
 *@version Copyright A2021
 */
public class Coord {

	/*
	 * Les coordonn�es du jeu.
	 */
	int ligne;
	int colonne;

	/**
	 * Constructeur par d�faut (0,0)
	 */
	public Coord(){
		
		ligne = 0;
		colonne = 0;
	}
	
	/**
	 * Constructeurs par ciopie d'attributs,
	 * 
	 * @param ligne
	 * @param colonne
	 */
	public Coord(int ligne,int colonne){
		
		this.ligne = ligne;
		this.colonne = colonne;
	}

	/**
	 * M�thode qui compare les lignes et les colonnes des deux coordonn�es
	 * (this et coord)
	 * 
	 * @param coord
	 * @return Si coord est �gal � this (deep equals)
	 */
	public boolean equals(Coord coord){
		
		return coord.ligne == ligne && coord.colonne == colonne;
	}
	
	/**
	 * Une version String d'un general.Coord.
	 */
	public String toString(){
		
		return "(" + ligne + "," + colonne + ")";
	}	
	
	/**
	 * Obtenir une copie de this � une adresse diff�rente.
	 * 
	 * @return Une instance de general.Coord != this && .equals(this).
	 */
	public Coord clone(){
		
		return new Coord(this.ligne, this.colonne);
	}
	
}
