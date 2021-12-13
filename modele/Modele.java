package modele;

import general.Coord;
import observer.MonObservable;

//SUBJECT
public class Modele extends MonObservable implements Runnable  {
    private static Modele instance = new Modele();

    Coord dernierClic;


    public static Modele getInstance(){
        return instance;
    }

    @Override
    public void run() {
        avertirLesObservers(this);
    }

    public void setClick(Coord dernierClic) {
        this.dernierClic = dernierClic;
        avertirLesObservers(this);

    }

    public Coord getDernierClic() {
        return dernierClic;
    }
}
