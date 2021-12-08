package vue;

import javax.swing.*;
import java.awt.*;

public class CadrePrincipal extends JFrame implements Runnable{

    PanneauPrincipal panPrincipal = new PanneauPrincipal();

    @Override
    public void run() {
        initCadre();
        initContenu();
        setVisible(true);
    }

    private void initCadre(){

        setSize(new Dimension(600,375));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initContenu(){
        setContentPane(panPrincipal);


    }
}
