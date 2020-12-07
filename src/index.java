import java.util.ArrayList;

public class index {
    public static void main(String[] args) {
        SIR uneSimu = new SIR(2000, 5, 0, 0.001f, 0.14f, 50);
        ArrayList<ArrayList<Integer>> leTab = uneSimu.LancerSimulation();
        System.out.println(leTab);
    }
}
