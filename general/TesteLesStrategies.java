package general;

import javax.swing.JOptionPane;

/**
 * Petit programme pour tester les stratégies de bataille navale du tp3.
 *  
 * @author Pierre Bélisle
 * @version Copyright A2021
 */
public class TesteLesStrategies {

	public static void main(String[] args){


		//À initialiser selon une valeur au hasard.
		InterfaceStrategie strategie = null;
		
		int confirme = 0;

		do{
			// Il y a juste quelques stratégies.
			int quelleStrategie = UtilitaireFonctions.nbAlea(Constantes.DEBUTANT,
							                                 Constantes.AVANCE);
			
			String type = null;
			
			// Pas le choix, il faut  créer la stratégie un jour ou l'autre.
			switch(quelleStrategie){

				case Constantes.DEBUTANT:
					type = "débutant";
					strategie = (InterfaceStrategie) new OrdiStrategieDebutant();
					break;
	
				case  Constantes.INTERMEDIAIRE:
					type = "intermediaire";
					strategie = (InterfaceStrategie) new OrdiStrategieIntermediaire();
					break;
	
				case Constantes.AVANCE:
					type = "avancé";
					strategie = (InterfaceStrategie) new OrdiStrategieAvance();
					break; // optionnel
				}
			
			
			// Boucle qui affiche le tir et attend que l'utilisateur annule
			// Si la stratégie change et le tir change, c'est que la 
			// hiérarchie fonctionne. 
			confirme = JOptionPane.showConfirmDialog(null,
					                                 "Stratégie : " + type +
					                                 
					                                 "\nLe tir : " + 
			                                          strategie.getTir() + 
			                                          
			                                          "\nVoulez-vous continuer ?");
		

	    // Se termine pour autre chose que oui.
		}while (confirme == JOptionPane.YES_OPTION);
		
	}
}