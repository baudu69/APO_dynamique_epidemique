package Nombre;

import Taux.genererExcel;
import org.jfree.ui.RefineryUtilities;

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
        //SEIR uneSimu = new SEIREvo(1000, 10, 0.1f, 0.1f, 0.05f, 365, 3.21f, 2.50f);
        //uneSimu.LancerSimulation();

        /*
            1. Confinement
            2. Deconfinement
            3. Port du Masque
            4. Demasquage
            5. Quarantaine
            6. Dequarantaine
            7. Vaccination
         */
        App uneApp = new App();
/*        ArrayList<ArrayList<Integer>> listeMesures = new ArrayList<>();
        ArrayList<Integer> confinement = new ArrayList<>();
        ArrayList<Integer> masque = new ArrayList<>();
        ArrayList<Integer> quarantaine = new ArrayList<>();
        ArrayList<Integer> vaccination = new ArrayList<>();
        masque.add(120);
        masque.add(150);
        masque.add(200);
        masque.add(250);
        masque.add(300);
        confinement.add(50);
        vaccination.add(30);
        //confinement.add()
        listeMesures.add(confinement);
        listeMesures.add(masque);
        listeMesures.add(quarantaine);
        listeMesures.add(vaccination);



        //TODO: Debugger le SEIREvo qui donne un liste de S incompréhensible
        Grille uneGrille = new Grille(30, 1200, 5, 0.1f, 0.1f, 0.05f, 365, listeMesures);
        uneGrille.LancerSimulationComplete();
        ArrayList<ArrayList<Integer>> lesResultats = uneGrille.getLesResultats();
        Graphique chart = new Graphique("Evo" , "Evo", lesResultats);

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );*/
        //genererExcel.SEIEVOint(lesResultats.get(0), lesResultats.get(1), lesResultats.get(2), lesResultats.get(3));
    }
}
