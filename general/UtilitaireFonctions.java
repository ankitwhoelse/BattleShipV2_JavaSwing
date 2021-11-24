package general;

/**
 * Contient quelques fontions de calcul math�matique.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 */
public class UtilitaireFonctions {

	/**
	 * Retourne un nombre al�atoire entre min et mas inclusivement.
	 */
	public static int nbAlea(int min, int max){

  	    /*
  	     * Strat�gie, on utilise le g�n�rateur de Java qui retourne une valeur 
  	     * r�elle entre 0 et 1[  ensuite, on le ram�ne dans l'intervalle 
  	     * min..max et on le transforme en entier
  	     */
  		return (int) (Math.random() * (max - min + 1) + min); 
  	}
    
 
   /**
    * Retourne une coordonn�e entre 0 + longueurNavire -1 et 
    * general.Constantes.TAILLE - longueurNavire
    */
   public static Coord coordAleatoire(int longueurNavire){
  	 
  	 return new Coord(
  			 
  			 nbAlea(longueurNavire - 1, Constantes.TAILLE - longueurNavire), 
  			 nbAlea(longueurNavire - 1, Constantes.TAILLE - longueurNavire));
   }
   


   /**
    * Retourne une coordonn�e dans la grille entre 0 et  general.Constantes.TAILLE - 1.
    */
   public static Coord coordAleatoire(){
  	 
  	 return new Coord(
  			 
  			 nbAlea(0, Constantes.TAILLE - 1), 
  			 nbAlea(0, Constantes.TAILLE - 1));
   }
}
