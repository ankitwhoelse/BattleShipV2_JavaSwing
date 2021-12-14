package vue;

import general.InterfaceStrategie;
import general.OrdiStrategieAvance;
import general.OrdiStrategieDebutant;
import general.OrdiStrategieIntermediaire;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarreMenu extends JMenuBar implements ActionListener{

    JMenu menu;
    JMenuItem deb, inter, avanc;
    PanneauPrincipal panneauPrincipal;

    public BarreMenu(PanneauPrincipal panneauPrincipal) {
        this.panneauPrincipal = panneauPrincipal;
        configurerContenu();
        setVisible(true);
    }

    private void configurerContenu() {

        menu = new JMenu("Strategie");

        deb = new JMenuItem("Debutant");
        deb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(menu.getParent(), "La strategie de l'ordi est: "
                        + e.getActionCommand());
            }
        });

        inter = new JMenuItem("Intermediaire");
        deb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JOptionPane.showMessageDialog(menu.getParent(), "La strategie de l'ordi est: "
                        + e.getActionCommand());
            }
        });

        avanc = new JMenuItem("Avance");
        avanc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(menu.getParent(), "La strategie de l'ordi est: "
                        + e.getActionCommand());
            }
        });

        menu.add(deb);
        menu.add(inter);
        menu.add(avanc);

        this.add(menu);
    }



    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("qqch ");
        if (actionEvent.getActionCommand().equals("Debutant")) {
           // panneauPrincipal.panBas.actionPerformed(new ActionEvent(this, "Nouvelle partie")) .ordi.setStrategie((InterfaceStrategie) new OrdiStrategieDebutant());
        } else if (actionEvent.getActionCommand().equals("Intermediaire")) {
            panneauPrincipal.ordi.setStrategie((InterfaceStrategie) new OrdiStrategieIntermediaire());
        } else if (actionEvent.getActionCommand().equals("Avance")) {
            panneauPrincipal.ordi.setStrategie((InterfaceStrategie) new OrdiStrategieAvance());
        }

    }

}
