package Simple;

import java.util.ArrayList;

public class SIR {
    //Nbr de personnes saines
    private ArrayList<Float> S;
    //Nbr de personnes Infectés
    private ArrayList<Float> I;
    //Nbr de personnes guéris ou mortes
    private ArrayList<Float> R;

    //Probabilité d'infecter quelq'un
    private float beta;

    //probabilité de ne plus être infecté
    private float gamma;

    //Temps de la simulation en Jours
    private int tempsSimulation;

    /**
     * @param S Taux de personnes saines
     * @param I Taux de personnes Infectés
     * @param R Taux de personnes guéris ou mortes
     * @param beta Probabilité d'infecter quelq'un
     * @param gamma probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     */
    public SIR(float I, float beta, float gamma, int tempsSimulation) {
        this.S = new ArrayList<Float>();
        this.I = new ArrayList<Float>();
        this.R = new ArrayList<Float>();
        this.S.add(1-I);
        this.I.add(I);
        this.R.add(0f);
        this.beta = beta;
        this.gamma = gamma;
        this.tempsSimulation = tempsSimulation;
    }

    /**
     * Effectue la simulation
     */
    private void simuler() {
        for (int temps=0; temps<getTempsSimulation(); temps++) {
            float tauxPersonnesDeSaI = (getBeta()*getS().get(temps)*getI().get(temps));
            float tauxPersonnesDeIaR = (getGamma()*getI().get(temps));

            getS().add((getS().get(temps) - tauxPersonnesDeSaI));
            getI().add((getI().get(temps) + tauxPersonnesDeSaI - tauxPersonnesDeIaR));
            getR().add((getR().get(temps) + tauxPersonnesDeIaR));
        }
    }

    /**
     * @return listeValeurs : un tableau composé des tableaux S, I et R
     */
    public ArrayList<ArrayList<Float>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Float>> listeValeurs = new ArrayList<>();
        listeValeurs.add(getS());
        listeValeurs.add(getI());
        listeValeurs.add(getR());
        genererExcel.SIR(getS(), getI(), getR());
        return listeValeurs;
    }

    /**
     * @return S
     */
    public ArrayList<Float> getS() {
        return S;
    }

    /**
     * @param s
     */
    protected void setS(ArrayList<Float> s) {
        S = s;
    }

    /**
     * @return I
     */
    public ArrayList<Float> getI() {
        return I;
    }

    /**
     * @param i
     */
    protected void setI(ArrayList<Float> i) {
        I = i;
    }

    /**
     * @return R
     */
    public ArrayList<Float> getR() {
        return R;
    }

    /**
     * @param r
     */
    protected void setR(ArrayList<Float> r) {
        R = r;
    }

    /**
     * @return beta
     */
    public float getBeta() {
        return beta;
    }

    /**
     * @param beta
     */
    protected void setBeta(float beta) {
        this.beta = beta;
    }

    /**
     * @return gamma
     */
    public float getGamma() {
        return gamma;
    }

    /**
     * @param gamma
     */
    protected void setGamma(float gamma) {
        this.gamma = gamma;
    }

    /**
     * @return tempsSimulation
     */
    public int getTempsSimulation() {
        return tempsSimulation;
    }

    /**
     * @param tempsSimulation
     */
    protected void setTempsSimulation(int tempsSimulation) {
        this.tempsSimulation = tempsSimulation;
    }
}
