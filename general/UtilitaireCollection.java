package general; /**
 * Permet d'accepter plusieurs sortes de collection et de les traiter pour
 * obtenir les tirs (voir �nonc� tp3 A21).
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021	
 */
import java.util.AbstractList; 

public class UtilitaireCollection {

		/**
		 * G�n�re al�atoirement des coordonn�es jusqu'� ce qu'une d'entre elles
		 * ne fasse pas partie du collection re�ue.
		 * 
		 * @param collection La collection (Un Vector, un ArrayList ou 
		 *                                 un LinkedList).
		 *                                 
		 * @return Un tir qui ne fait pas partie de la collection.
		 */
		public static 
		         Coord obtenirCoupPasDejaJouer (AbstractList<Coord> collection){

			Coord c;

			do{

				c  = UtilitaireFonctions.coordAleatoire();

			// S'arr�te avec un coup pas d�j� jou�.
			}while(collectionContientCoord(collection, c));

			return c;

		}
		
		/**
		 * �quivalent � contains sauf qu'on regarde le contenu des coordonn�es
		 * et non seulement leur r�f�rence (deepContains)
		 *  
		 * @param collection Une collection  coordonn�es
		 * @param c La coordonn�e cherch�e
		 * @return Si une coordonn�es dans la collection correspond � c.
		 */

		public static 
		       boolean collectionContientCoord(AbstractList <Coord> collection, 
			                                   Coord c){
			
			/*
			 * Strat�gie : On recherche avec un while pour terminer d�s 
			 * qu'on a trouv�.
			 */

			boolean existe = false;

			// It�rateur.
			int  i = 0;

			// On regarde chaque coup du collection et s'il est identique au 
			// coup la boucle s'arr�te au prochain tour.
			while(i < collection.size() && !existe){
				
				existe = c.equals((Coord)collection.get(i));
				i++;
			}				 

			
			return existe;
		}

	/**
	 * Retourne le contenu des coordonnn�es dans un tableau de String.
	 * 
	 * @param tab Une liste de coordonn�es.
	 * 
	 * @return Les coordonn�es en String.
	 */
	public static String[] obtenirTabString(AbstractList<Coord> tab){

		String[] tabStr = null;

		if(tab.size() != 0){

			tabStr = new String[tab.size()];

			for(int i  = 0; i < tabStr.length; i++){
				
				tabStr[i] = tab.get(i).toString();
			}
		}
		return tabStr;		
	}
}