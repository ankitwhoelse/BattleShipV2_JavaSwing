package general;

import javax.swing.JOptionPane;

/**
 * Petit programme pour tester les strat�gies de bataille navale du tp3.
 *  
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class TesteLesStrategies {

	public static void main(String[] args){


		//� initialiser selon une valeur au hasard.
		InterfaceStrategie strategie = null;
		
		int confirme = 0;

		do{
			// Il y a juste quelques strat�gies.
			int quelleStrategie = UtilitaireFonctions.nbAlea(Constantes.DEBUTANT,
							                                 Constantes.AVANCE);
			
			String type = null;
			
			// Pas le choix, il faut  cr�er la strat�gie un jour ou l'autre.
			switch(quelleStrategie){

				case Constantes.DEBUTANT:
					type = "d�butant";
					strategie = (InterfaceStrategie) new OrdiStrategieDebutant();
					break;
	
				case  Constantes.INTERMEDIAIRE:
					type = "intermediaire";
					strategie = (InterfaceStrategie) new OrdiStrategieIntermediaire();
					break;
	
				case Constantes.AVANCE:
					type = "avanc�";
					strategie = (InterfaceStrategie) new OrdiStrategieAvance();
					break; // optionnel
				}
			
			
			// Boucle qui affiche le tir et attend que l'utilisateur annule
			// Si la strat�gie change et le tir change, c'est que la 
			// hi�rarchie fonctionne. 
			confirme = JOptionPane.showConfirmDialog(null,
					                                 "Strat�gie : " + type +
					                                 
					                                 "\nLe tir : " + 
			                                          strategie.getTir() + 
			                                          
			                                          "\nVoulez-vous continuer ?");
		

	    // Se termine pour autre chose que oui.
		}while (confirme == JOptionPane.YES_OPTION);
		
	}
}