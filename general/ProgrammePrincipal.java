package general;

import vue.CadreBatailleNavale;
import vue.PanneauPrincipal;

import javax.swing.*;

public class ProgrammePrincipal {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new CadreBatailleNavale());

    }
}
