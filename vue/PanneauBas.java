package vue;

import general.Constantes;
import general.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauBas extends JPanel implements ActionListener {

    JButton btnNouvellePartie = new JButton("Nouvelle partie");
    JButton btncacherFloote = new JButton("Cacher la flotte");
    boolean booPariteEnCours = false;
    String participant = "";
    PanneauHaut panneauHaut;
    PanneauBas(PanneauHaut panneauHaut){
        this.panneauHaut = panneauHaut;
        configurerPanneau();
        configurerContenu();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }


    public void configurerPanneau() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        // A VERIFIER
        int height = (int) (d.height * 0.4);
        int width = (int) (d.width * 0.4);
        setSize(new Dimension(height,width));
    }

    public void configurerContenu() {
        setLayout(new FlowLayout());

        btncacherFloote.addActionListener(this);
        btnNouvellePartie.addActionListener(this);

        add(btnNouvellePartie);
        add(btncacherFloote);

    }


}
