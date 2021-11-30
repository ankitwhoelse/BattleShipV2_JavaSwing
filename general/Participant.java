package general;

public class Participant implements InterfaceParticipant {

    private Flotte flotte;

    @Override
    public void genereNouvelleFlotte() {
        flotte = flotte.obtenirFlotteAleatoire();
    }

    @Override
    public Flotte getFlotte() {
        return flotte;
    }

    @Override
    public boolean flotteARecuTirQuiATouche(Coord tir) {
        return flotte.leTirTouche(tir);
    }

    @Override
    public boolean jeuEstTermine() {
        return flotte.jeuEstTermine();
    }

    @Override
    public String getDernierNavireCoule() {
        return flotte.getDernierNavireCoule();
    }

    @Override
    public boolean dernierTirACoule() {
        return flotte.dernierTirACoule();
    }
}
