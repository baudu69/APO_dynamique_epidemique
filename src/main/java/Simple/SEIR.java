package Simple;

import java.util.ArrayList;

public class SEIR extends SIR {
    //Nombre de personnes en incubation
    private ArrayList<Float> E;

    //Probabilité d'avoir fini l'incubation
    private float alpha;

    /**
     * @param E               Nombre de personnes en incubation
     * @param I               Nbr de personnes Infectés
     * @param beta            Probabilité d'infecter quelq'un
     * @param alpha           Probabilité d'avoir fini l'incubation
     * @param gamma           probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     */
    public SEIR(float E, float I, float beta, float alpha, float gamma, int tempsSimulation) {
        super(I, beta, gamma, tempsSimulation);
        this.E = new ArrayList<>();
        this.E.add(E);
        this.alpha = alpha;
    }

    /**
     * Effectue la simulation
     */
    private void simuler() {
        for (int temps=0; temps<getTempsSimulation(); temps++) {
            Float nbPersonnesDeSaE =  (getBeta()*getS().get(temps)*getI().get(temps));
            Float nbPersonnesDeEaI =  (getAlpha()*getE().get(temps));
            Float nbPersonnesDeIaR =  (getGamma()*getI().get(temps));

            getS().add((getS().get(temps) - nbPersonnesDeSaE));
            getE().add(getE().get(temps) + nbPersonnesDeSaE - nbPersonnesDeEaI);
            getI().add((getI().get(temps) + nbPersonnesDeEaI - nbPersonnesDeIaR));
            getR().add((getR().get(temps) + nbPersonnesDeIaR));
        }
    }

    /**
     * @return listeValeurs : un tableau composé des tableaux S, E, I et R
     */
    public ArrayList<ArrayList<Float>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Float>> listeValeurs = new ArrayList<>();
        listeValeurs.add(getS());
        listeValeurs.add(E);
        listeValeurs.add(getI());
        listeValeurs.add(getR());
        genererExcel.SEIEVO(getS(), getE(), getI(), getR());
        return listeValeurs;
    }

    protected ArrayList<Float> getE() {
        return E;
    }

    protected void setE(ArrayList<Float> e) {
        E = e;
    }

    protected float getAlpha() {
        return alpha;
    }

    protected void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
