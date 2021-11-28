package general;

public class Ordi extends Participant implements InterfaceOrdi {


    public Ordi(){
        super.getFlotte();
    }
    @Override
    public InterfaceStrategie getStrategie() {
        return null;
    }

    @Override
    public void setStrategie(InterfaceStrategie strategie) {

    }
}
