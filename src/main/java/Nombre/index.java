package Nombre;

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
        SEIR uneSimu = new SEIR(10000, 10, 0.1f, 0.1f, 0.05f, 365);
        uneSimu.LancerSimulation();
    }
}
