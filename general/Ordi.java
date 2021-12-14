package general;

public class Ordi extends Participant implements InterfaceOrdi {

    private InterfaceStrategie strategie;

    public Ordi(){
        strategie = new OrdiStrategieDebutant();
        super.getFlotte();
    }


    @Override
    public InterfaceStrategie getStrategie() {
        return strategie;
    }

    @Override
    public void setStrategie(InterfaceStrategie strategie) {

        if (strategie == new OrdiStrategieDebutant()) {
            this.strategie = new OrdiStrategieDebutant();
        } else if (strategie == new OrdiStrategieIntermediaire()) {
            this.strategie = new OrdiStrategieIntermediaire();
        } else if (strategie == new OrdiStrategieAvance()) {
            this.strategie = new OrdiStrategieAvance();
        }

    }
}
