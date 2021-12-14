package general;

/**
 * general.Joueur qui a d�j� jou�.  Sa strat�gie est un peu meilleure que la premi�re
 * fois qu'il a  jou�.
 *
 * Il retient ses coups et ne joue pas deux fois le m�me.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class OrdiStrategieDebutant extends StrategieOrdiAbstrait{
	
	/**
	 * 
	 * @return
	 */
	public Coord getTir(){
		
		/*
		 * Strat�gie : La m�thode a �t� mise dans general.UtilitaireCollection pour
		 * r�utilisation. 
		 */
		Coord c =super.obtenirTirPasDejaJoue();
				
		// Ajoute le tir dans le tirs jou� du parent.
		super.ajouterTir(c);
		System.out.print(c);
		return c; 
		
	}
	
	public void aviserTouche(){}	
}
