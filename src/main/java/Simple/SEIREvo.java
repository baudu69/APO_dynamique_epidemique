package Simple;

import java.util.ArrayList;

public class SEIREvo extends SEIR {

    private float n;

    private float u;


    /**
     * @param E               Nombre de personnes en incubation
     * @param I               Nbr de personnes Infectés
     * @param beta            Probabilité d'infecter quelq'un
     * @param alpha           Probabilité d'avoir fini l'incubation
     * @param gamma           probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     * @param n               Taux de naissance
     * @param u               Taux de mort naturelle
     */
    public SEIREvo(float E, float I, float beta, float alpha, float gamma, float n, float u, int tempsSimulation) {
        super(E, I, beta, alpha, gamma, tempsSimulation);
        this.u = u;
        this.n = n;
    }

    private void simuler() {
        for (int temps=0; temps<getTempsSimulation(); temps++) {

            float nbPersonnesDeSaE = (getBeta()*getS().get(temps)*getI().get(temps));
            float nbPersonnesDeEaI = (getAlpha()*getE().get(temps));
            float nbPersonnesDeIaR = (getGamma()*getI().get(temps));

            float nbPersonnesNesS = n*getS().get(temps);
            float nbPersonnesNesE = n*getE().get(temps);
            float nbPersonnesNesI = n*getI().get(temps);
            float nbPersonnesNesR = n*getR().get(temps);
            float nbPersMortS = u*getS().get(temps);
            float nbPersMortE = u*getE().get(temps);
            float nbPersMortI = u*getI().get(temps);
            float nbPersMortR = u*getR().get(temps);

            getS().add((getS().get(temps) - nbPersonnesDeSaE + nbPersonnesNesS - nbPersMortS));
            getE().add(getE().get(temps) + nbPersonnesDeSaE - nbPersonnesDeEaI + nbPersonnesNesE - nbPersMortE);
            getI().add((getI().get(temps) + nbPersonnesDeEaI - nbPersonnesDeIaR + nbPersonnesNesI - nbPersMortI));
            getR().add((getR().get(temps) + nbPersonnesDeIaR + nbPersonnesNesR - nbPersMortR));
        }
        genererExcel();
    }

    public ArrayList<ArrayList<Float>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Float>> listeValeurs = new ArrayList<>();
        listeValeurs.add(getS());
        listeValeurs.add(getE());
        listeValeurs.add(getI());
        listeValeurs.add(getR());
        return listeValeurs;
    }

    private void genererExcel() {
        genererExcel.SEIEVO(getS(), getE(), getI(), getR());
    }

    public float getN() {
        return n;
    }

    protected void setN(float n) {
        this.n = n;
    }

    public float getU() {
        return u;
    }

    protected void setU(float u) {
        this.u = u;
    }
}
