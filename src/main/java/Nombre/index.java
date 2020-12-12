package Nombre;

import Taux.genererExcel;

import java.util.ArrayList;

public class index {
    public static void main(String[] args) {
        /*
            nbPersonnes : 10000;
            nbrInteraction : 10;
            alpha : 0.1f
            beta : 0.1f
            gamma : 0.05f
            tempsSimu : 365
            n : 3.21f
            u : 2.50f

         */
        /*SEIR uneSimu = new SEIR(1000, 10, 0.1f, 0.1f, 0.05f, 365);
        uneSimu.LancerSimulation();*/

        //TODO: Debugger le SEIREvo qui donne un liste de S incompréhensible
        Grille uneGrille = new Grille(106, 4, 10, 0.1f, 0.1f, 0.05f, 365);
        uneGrille.LancerSimulationComplete();
        ArrayList<ArrayList<Integer>> lesResultats = uneGrille.getLesResultats();
        genererExcel.SEIEVOint(lesResultats.get(0), lesResultats.get(1), lesResultats.get(2), lesResultats.get(3));
    }
}
