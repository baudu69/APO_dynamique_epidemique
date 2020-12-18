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

    //Liste de toutes les Personnes
    private ArrayList<Personne> lesPersonnes;

    //Liste du nombre de personnes Saines sur la durée
    private ArrayList<Integer> nbS;

    //Liste du nombre de personnes Infecté sur la durée
    private ArrayList<Integer> nbI;

    //Liste du nombre de personnes Rétabli sur la durée
    private ArrayList<Integer> nbR;

    //Nombre d'interaction par personne avec les gens sur la même case
    private float nbrInteractions;

    //Probabilité d'infecter quelq'un
    private float beta;

    //probabilité de ne plus être infecté
    private float gamma;

    //Temps de la simulation en Jours
    private int tempsSimulation;

    //Jour actuel de la simulation
    private int jourActuel;


    /**
     * @param nbPersonnes Nombre de personne de la simulation
     * @param nbrInteractions Nombre d'interaction par personne avec les gens sur la même case
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


    /**
     * Remet chaque personne dans sa catégorie respective (S, I, R)
     * Compte les personne dans chaque catégories et les ajoute au décomte
     * total (nbS, nbI, nbR)
     */
    protected void compter() {
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

    /**
     * Incrémente la simulation d'un jour
     */
    protected void incrSimu() {
        infection();
        retablissement();
        compter();
        this.jourActuel++;
    }

    /**
     * Gère les infections des personne de la simulation
     */
    protected void infection() {
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

    /**
     * Gère le rétablissement des personnes de la simulation
     */
    protected void retablissement() {
        ArrayList<Personne> lesInfectes = new ArrayList<>(I);
        for (Personne unInfecte: lesInfectes) {
            if (unInfecte.retablissement(gamma)) {
                I.remove(unInfecte);
                R.add(unInfecte);
            }
        }
    }

    /**
     * Effectue la simulation complète
     */
    protected void simuler() {
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

    /**
     * @param max Le maximum pouvant être atteint
     * @return le nombre random généré
     */
    protected int random(int max) {
        return ((int) (Math.random() * max));
    }

    public void masquer() {
        this.beta /= 10;
    }
    public void deMasquer() {
        this.beta *= 10;
    }


    /**
     * Partie GETTER et SETTER
     */

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