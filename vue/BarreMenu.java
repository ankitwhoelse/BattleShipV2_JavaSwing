package vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarreMenu extends JMenuBar implements ActionListener {

    JMenu menu;
    JMenuItem deb, inter, avanc;

    public BarreMenu() {

        configurerContenu();
        setVisible(true);
    }

    private void configurerContenu() {
        JMenuBar barMenu = new JMenuBar();

        menu = new JMenu("Stratégie");

        deb = new JMenuItem("Débutant");
        inter = new JMenuItem("Intermédiaire");
        avanc = new JMenuItem("Avancé");

        menu.add(deb);
        menu.add(inter);
        menu.add(avanc);

        barMenu.add(menu);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getActionCommand().equals("Débutant")) {

        } else if (actionEvent.getActionCommand().equals("Intermédiaire")) {

        } else if (actionEvent.getActionCommand().equals("Avancé")) {

        }


    }
}
