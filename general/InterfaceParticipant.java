package general;

/**
 * Contrat � remplir par la classe general.Participant du tp3 inf11 A2021 (voir �nonc�).
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public interface InterfaceParticipant {
		
	/**
	 * Demande � l'ordi de se g�n�rer une nouvelle flotte.
	 * 
	 * @return ordi.super.flotte.clone();
	 */
	public void genereNouvelleFlotte();
	
	
	/**
	 * Retourne une copie de la flotte de l'ordi.  Une modification �
	 * cette flotte n'affecte pas la sienne.
	 * 
	 * @return ordi.super.flotte.clone();
	 */
	public Flotte getFlotte();
	
	/**
	 * Retourne si le tir re�u a touch� � la flotte.
	 * 
	 * @param tir Le tir � consid�rer.
	 * 
	 * @return true si le tir touche � un des navires de la flotte.
	 */
	public boolean flotteARecuTirQuiATouche(Coord tir);


	/*
	 * Retourne si tous les navires du joueur sont coul�s.
	 * 
	 * @return true si tous les navires sont coul�s et que le jeu est termin�.
	 */
	public boolean jeuEstTermine();
	
	
	/**
	 * Retourne le nom du dernier navire qui a �t� coul�.
	 * 
	 * @return le nom du dernier navire qui a �t� coul�.
	 */
	public String getDernierNavireCoule();
	
	/**
	 * Retourne si le dernier tir a coul� un navire dans la flotte del'ordi.
	 * 
	 * @return true si le dernier tir a coul� un navire.
	 */
	public boolean dernierTirACoule();


	

}
