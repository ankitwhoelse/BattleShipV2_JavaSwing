package observer;

/**
 * Classe abstraite du patron Observable
 * @author Fred Simard | ETS
 * @version ETE 2018
 */

import modele.Modele;

import java.util.ArrayList;
//SUBJECT
public abstract class MonObservable {

	// liste des observers
	ArrayList<MonObserver> observers = new ArrayList<MonObserver>();
	
	/**
	 * méthode pour attacher un Observer
	 * @param observer
	 */
	public void attacherObserver(MonObserver observer){
		observers.add(observer);
	}
	
	/**
	 * méthode pour avertir tous les observers
	 * @param modele
	 */
	public void avertirLesObservers(Modele modele){
		for(MonObserver observer:observers){
			observer.avertir(modele);
		}
	}
		
}
