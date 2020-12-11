package Nombre;

import Taux.genererExcel;

import java.util.ArrayList;

public class SIR {
    //Nbr de personnes saines
    private ArrayList<Personne> S;
    //Nbr de personnes Infectés
    private ArrayList<Personne> I;
    //Nbr de personnes guéris ou mortes
    private ArrayList<Personne> R;

    private ArrayList<Personne> lesPersonnes;

    private ArrayList<Integer> nbS;
    private ArrayList<Integer> nbI;
    private ArrayList<Integer> nbR;

    //Probabilité d'infecter quelq'un
    private float nbrInteractions;

    //Probabilité d'infecter quelq'un
    private float beta;

    //probabilité de ne plus être infecté
    private float gamma;

    //Temps de la simulation en Jours
    private int tempsSimulation;

    private int jourActuel;

    /**
     * @param beta Probabilité d'infecter quelq'un
     * @param gamma probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     */
    public SIR(int nbPersonnes, int nbrInteractions, float beta, float gamma, int tempsSimulation) {
        this.S = new ArrayList<>();
        this.I = new ArrayList<>();
        this.R = new ArrayList<>();
        this.lesPersonnes = new ArrayList<>();
        this.nbS = new ArrayList<>();
        this.nbI = new ArrayList<>();
        this.nbR = new ArrayList<>();
        this.nbrInteractions = nbrInteractions;
        this.beta = beta;
        this.gamma = gamma;
        this.tempsSimulation = tempsSimulation;
        this.jourActuel = 0;
        for (int i = 0; i < nbPersonnes - 1; i++) {

            this.S.add(new Personne());
        }
        this.I.add(new Personne("I"));
    }


    private void compter() {
        int nbS = 0;
        for (Personne unePersonne: S) {
            nbS++;
        }
        int nbI = 0;
        for (Personne unePersonne: I) {
            nbI++;
        }
        int nbR = 0;
        for (Personne unePersonne: R) {
            nbR++;
        }
        this.nbS.add(nbS);
        this.nbI.add(nbI);
        this.nbR.add(nbR);
        this.lesPersonnes = new ArrayList<>();
        this.lesPersonnes.addAll(S);
        this.lesPersonnes.addAll(I);
        this.lesPersonnes.addAll(R);
    }

    private void incrSimu() {
        infection();
        retablissement();
        compter();
        this.jourActuel++;
    }

    private void infection() {
        ArrayList<Personne> lesInfectes = new ArrayList<>(I);
        for (Personne unInfecte: lesInfectes) {
            for (int i = 0; i < nbrInteractions; i++) {
                Personne unePersRandom = lesPersonnes.get(random(lesPersonnes.size()));
                if (unInfecte.rencontrerSIR(unePersRandom, beta)) {
                    this.S.remove(unePersRandom);
                    this.I.add(unePersRandom);
                }
            }
        }
    }

    private void retablissement() {
        ArrayList<Personne> lesInfectes = new ArrayList<>(I);
        for (Personne unInfecte: lesInfectes) {
            if (unInfecte.retablissement(gamma)) {
                I.remove(unInfecte);
                R.add(unInfecte);
            }
        }
    }

    /**
     * Effectue la simulation
     */
    private void simuler() {
        compter();
        this.jourActuel++;
        while (jourActuel < tempsSimulation) {
            incrSimu();
        }
        genererExcel.SIRint(nbS, nbI, nbR);
    }

    /**
     * @return listeValeurs : un tableau composé des tableaux S, I et R
     */
    public ArrayList<ArrayList<Float>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Float>> listeValeurs = new ArrayList<>();
        return listeValeurs;
    }

    protected int random(int max) {
        return ((int) (Math.random() * max));
    }


    public ArrayList<Personne> getS() {
        return S;
    }

    public void setS(ArrayList<Personne> s) {
        S = s;
    }

    public ArrayList<Personne> getI() {
        return I;
    }

    public void setI(ArrayList<Personne> i) {
        I = i;
    }

    public ArrayList<Personne> getR() {
        return R;
    }

    public void setR(ArrayList<Personne> r) {
        R = r;
    }

    public ArrayList<Personne> getLesPersonnes() {
        return lesPersonnes;
    }

    public void setLesPersonnes(ArrayList<Personne> lesPersonnes) {
        this.lesPersonnes = lesPersonnes;
    }

    public ArrayList<Integer> getNbS() {
        return nbS;
    }

    public void setNbS(ArrayList<Integer> nbS) {
        this.nbS = nbS;
    }

    public ArrayList<Integer> getNbI() {
        return nbI;
    }

    public void setNbI(ArrayList<Integer> nbI) {
        this.nbI = nbI;
    }

    public ArrayList<Integer> getNbR() {
        return nbR;
    }

    public void setNbR(ArrayList<Integer> nbR) {
        this.nbR = nbR;
    }

    public float getNbrInteractions() {
        return nbrInteractions;
    }

    public void setNbrInteractions(float nbrInteractions) {
        this.nbrInteractions = nbrInteractions;
    }

    public float getBeta() {
        return beta;
    }

    public void setBeta(float beta) {
        this.beta = beta;
    }

    public float getGamma() {
        return gamma;
    }

    public void setGamma(float gamma) {
        this.gamma = gamma;
    }

    public int getTempsSimulation() {
        return tempsSimulation;
    }

    public void setTempsSimulation(int tempsSimulation) {
        this.tempsSimulation = tempsSimulation;
    }

    public int getJourActuel() {
        return jourActuel;
    }

    public void setJourActuel(int jourActuel) {
        this.jourActuel = jourActuel;
    }
}