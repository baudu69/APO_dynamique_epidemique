import Simple.SEIR;
import Simple.SEIREvo;
import Simple.SIR;

import java.util.Locale;
import java.util.ResourceBundle;

public class index {
    public static void main(String[] args) {
        SEIREvo uneSimu = new SEIREvo(20000, 0, 5, 0, 0.8f, 0.75f, 0.14f, 0.009f, 0.01f, 500);
        uneSimu.LancerSimulation();
        //ArrayList<ArrayList<Integer>> leTab = uneSimu.LancerSimulation();
        //System.out.println(leTab);
    }
}
