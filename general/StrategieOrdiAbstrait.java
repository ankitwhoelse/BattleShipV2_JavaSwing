package general;

import java.util.Vector;

/**
 * Classe qui existe principalement pour �viter 
 * la r�p�tition de code dans les sous-classes concr�tes de strat�gie.
 * 
 * Elle contient le code pour retenir les tirs jou�s et retourner 
 * le dernier sur demande.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 *
 */


// Vous devez �crire le code manquant pour que cete classe impl�mente 
// InterfaceStrat�gie et permette de retenir les coups.

public class StrategieOrdiAbstrait  implements InterfaceStrategie{

	// Retient les coups jou�s dans une collection.
	private Vector<Coord> tabTirsJoues =  new Vector<Coord> ();

	// Cinq m�thodes � �crire (voir �nonc�).
	Coord obtenirTirPasDejaJoue() {
		return UtilitaireCollection.obtenirCoupPasDejaJouer(tabTirsJoues) ;
	}

	void ajouterTir(Coord c) {
		tabTirsJoues.add(0,c);
	}

	boolean tirDejaJoue(Coord tir) {

		return UtilitaireCollection.collectionContientCoord(tabTirsJoues,tir);
	}

	Coord getDernierTir() {
		return tabTirsJoues.lastElement();
	}


	@Override
	public Coord getTir() {
		return null;
	}

	@Override
	public void aviseTouche(Coord c) {

	}
	@Override
	public void resetTirsJoues() {
		tabTirsJoues.clear();
	}


}
