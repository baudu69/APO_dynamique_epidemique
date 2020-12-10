package Simple;

import java.util.ArrayList;

public class SEIR extends SIR {
    //Nombre de personnes en incubation
    private ArrayList<Integer> E;

    //Probabilité d'avoir fini l'incubation
    private float alpha;

    /**
     * @param S               Nbr de personnes saines
     * @param E               Nombre de personnes en incubation
     * @param I               Nbr de personnes Infectés
     * @param R               Nbr de personnes guéris ou mortes
     * @param beta            Probabilité d'infecter quelq'un
     * @param alpha           Probabilité d'avoir fini l'incubation
     * @param gamma           probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     */
    public SEIR(int S, int E, int I, int R, float beta, float alpha, float gamma, int tempsSimulation) {
        super(S, I, R, beta, gamma, tempsSimulation);
        this.E = new ArrayList<>();
        this.E.add(E);
        this.alpha = alpha;
    }

    /**
     * Effectue la simulation
     */
    private void simuler() {
        for (int temps=0; temps<getTempsSimulation(); temps++) {
            int nbPersonnesDeSaE = (int) (getBeta()*getS().get(temps)*getI().get(temps));
            if (nbPersonnesDeSaE > getS().get(temps)) nbPersonnesDeSaE = getS().get(temps);
            int nbPersonnesDeEaI = (int) (getAlpha()*getE().get(temps));
            int nbPersonnesDeIaR = (int) (getGamma()*getI().get(temps));

            getS().add((getS().get(temps) - nbPersonnesDeSaE));
            getE().add(getE().get(temps) + nbPersonnesDeSaE - nbPersonnesDeEaI);
            getI().add((getI().get(temps) + nbPersonnesDeEaI - nbPersonnesDeIaR));
            getR().add((getR().get(temps) + nbPersonnesDeIaR));
        }
    }

    /**
     * @return listeValeurs : un tableau composé des tableaux S, E, I et R
     */
    public ArrayList<ArrayList<Integer>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Integer>> listeValeurs = new ArrayList<>();
        listeValeurs.add(getS());
        listeValeurs.add(E);
        listeValeurs.add(getI());
        listeValeurs.add(getR());
        return listeValeurs;
    }

    protected ArrayList<Integer> getE() {
        return E;
    }

    protected void setE(ArrayList<Integer> e) {
        E = e;
    }

    protected float getAlpha() {
        return alpha;
    }

    protected void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
