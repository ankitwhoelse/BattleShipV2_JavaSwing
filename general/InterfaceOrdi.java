package general;

/**
 * Contrat � remplir par la classe Ordi du tp3 inf11 A2021 (voir �nonc�).
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public interface InterfaceOrdi {
	
	
	
	
	
	/**
	 * Accesseur de la strat�gie de l'ordi.
	 * 
	 * @return La strat�gie de l'ordi.
	 */
	public InterfaceStrategie getStrategie();

	/**
	 * Mutateur de la strat�gie.
	 * 
	 * @param strategie La nouvelle strategie de l'ordi.
	 */
	public void setStrategie(InterfaceStrategie strategie);

}
