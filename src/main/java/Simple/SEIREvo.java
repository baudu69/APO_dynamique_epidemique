/*
package Simple;

import java.util.ArrayList;

public class SEIREvo extends SEIR {

    private float n;

    private float u;

    private int nbPersTotal;

    */
/**
     * @param S               Nbr de personnes saines
     * @param E               Nombre de personnes en incubation
     * @param I               Nbr de personnes Infectés
     * @param R               Nbr de personnes guéris ou mortes
     * @param beta            Probabilité d'infecter quelq'un
     * @param alpha           Probabilité d'avoir fini l'incubation
     * @param gamma           probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     * @param n               Taux de naissance
     * @param u               Taux de mort naturelle
     *//*

    public SEIREvo(int S, int E, int I, int R, float beta, float alpha, float gamma, float n, float u, int tempsSimulation) {
        super(S, E, I, R, beta, alpha, gamma, tempsSimulation);
        this.u = u;
        this.n = n;
        nbPersTotal = S + I;
    }

    private void simuler() {
        for (int temps=0; temps<getTempsSimulation(); temps++) {
            int nbPersonnesNes = (int) n*nbPersTotal;
            int nbPersonnesDeSaE = (int) (getBeta()*getS().get(temps)*getI().get(temps));
            if (nbPersonnesDeSaE > getS().get(temps)) nbPersonnesDeSaE = getS().get(temps);
            int nbPersonnesDeEaI = (int) (getAlpha()*getE().get(temps));
            int nbPersonnesDeIaR = (int) (getGamma()*getI().get(temps));

            int nbPersMortS = (int) u*getS().get(temps);
            int nbPersMortE = (int) u*getE().get(temps);
            int nbPersMortI = (int) u*getI().get(temps);
            int nbPersMortR = (int) u*getR().get(temps);

            getS().add((getS().get(temps) - nbPersonnesDeSaE + nbPersonnesNes - nbPersMortS));
            getE().add(getE().get(temps) + nbPersonnesDeSaE - nbPersonnesDeEaI - nbPersMortE);
            getI().add((getI().get(temps) + nbPersonnesDeEaI - nbPersonnesDeIaR - nbPersMortI));
            getR().add((getR().get(temps) + nbPersonnesDeIaR - nbPersMortR));
        }
        genererExcel();
    }

    public ArrayList<ArrayList<Integer>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Integer>> listeValeurs = new ArrayList<>();
        listeValeurs.add(getS());
        listeValeurs.add(getE());
        listeValeurs.add(getI());
        listeValeurs.add(getR());
        return listeValeurs;
    }

    private void genererExcel() {
        genererExcel.SIR(getS(), getI(), getR());
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
*/
