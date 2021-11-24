package general;

/**
 * Classe qui représente un joueur d'une partie de bataille navale
 * 
 * Le joueur a un nom, une flotte et retient les tirs qu'il a effectués.
 * 
 * @author Pierre Bélisle
 * @version Copyright A2021
 */
public class Joueur extends Participant{

	/*
	 * Stratégie : On conserve les tirs dans un vecteur et les tâches sont 
	 * déléguées à l'utilitaire de collections.
	 */
	private String nom;
	
	/**
	 * Constructeur par copie de l'attribut nom.
	 * 
	 * Un flotte aléatoire et aucun tirs joués.
	 * 
	 * @param nom
	 */
	public Joueur(String nom){		
		
		this.nom = nom;
	}

	
	/**
	 * Accesseur du nom.
	 * 
	 * @return 
	 */
	public String getNom() {
		
		return nom;
	}
	
	
}
