package observer;

import modele.Modele;

/**
 * Interface d'un observer
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018
 */
//Observateur ou observer
public interface MonObserver {

	public void avertir(Modele modele);
	
}
