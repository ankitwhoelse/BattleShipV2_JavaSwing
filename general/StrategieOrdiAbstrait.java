package general;

import java.util.Vector;

/**
 * Classe qui existe principalement pour éviter 
 * la répétition de code dans les sous-classes concrètes de stratégie.
 * 
 * Elle contient le code pour retenir les tirs joués et retourner 
 * le dernier sur demande.
 * 
 * @author Pierre Bélisle
 * @version Copyright A2021
 *
 */


// Vous devez écrire le code manquant pour que cete classe implémente 
// InterfaceStratégie et permette de retenir les coups.

public class StrategieOrdiAbstrait  implements InterfaceStrategie{

	// Retient les coups joués dans une collection.
	private Vector<Coord> tabTirsJoues =  new Vector<Coord> ();

	// Cinq méthodes à écrire (voir énoncé).
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
