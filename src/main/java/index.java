import Taux.Monde;

public class index {
    public static void main(String[] args) {
        //SEIREvo uneSimu = new SEIREvo(20000, 0, 5, 0, 0.8f, 0.75f, 0.14f, 0.009f, 0.01f, 500);
        //SIR uneSimu = new SEIREvo(0f, 0.01f, 0.8f, 0.75f, 0.05f, 0.009f, 0.01f, 100);
        //uneSimu.LancerSimulation();

        Monde unMonde = new Monde(10, 0f, 0f, 0.8f, 0.75f, 0.05f, 0.009f, 0.01f, 100);
        unMonde.LancerSimulation();
        unMonde.recupererResultat();


        //ArrayList<ArrayList<Integer>> leTab = uneSimu.LancerSimulation();
        //System.out.println(leTab);
    }
}
