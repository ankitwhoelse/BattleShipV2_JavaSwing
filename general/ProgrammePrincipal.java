package general;

import vue.PanneauPrincipal;

import javax.swing.*;

public class ProgrammePrincipal {

    public static void main(String[] args) {
        PanneauPrincipal cadre = new PanneauPrincipal();
        SwingUtilities.invokeLater(cadre);
        cadre.run();
    }
}
