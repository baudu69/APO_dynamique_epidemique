package Nombre;

import Taux.genererExcel;

import java.util.ArrayList;

public class SEIR extends SIR {

    //Probabilité d'avoir fini l'incubation
    private float alpha;

    //Liste de personne en incubation
    private ArrayList<Personne> E;

    //Liste du nombre de personnes en Incubation sur la durée
    private ArrayList<Integer> nbE;

    /**
     * @param nbPersonnes Nombre de personne de la simulation
     * @param nbrInteractions Nombre d'interaction par personne avec les gens sur la même case
     * @param alpha Probabilité d'avoir fini l'incubation
     * @param beta Probabilité d'infecter quelq'un
     * @param gamma probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     */
    public SEIR(int nbPersonnes, int nbrInteractions,float alpha, float beta, float gamma, int tempsSimulation) {
        super(nbPersonnes, nbrInteractions, beta, gamma, tempsSimulation);
        this.alpha = alpha;
        this.E = new ArrayList<>();
        this.nbE = new ArrayList<>();
    }

    /**
     * Remet chaque personne dans sa catégorie respective (S, E, I, R)
     * Compte les personne dans chaque catégories et les ajoute au décomte
     * total (nbS, nbE, nbI, nbR)
     */
    @Override
    protected void compter() {
        int nbS = 0;
        for (Personne unePersonne: getS()) {
            nbS++;
        }
        int nbI = 0;
        for (Personne unePersonne: getI()) {
            nbI++;
        }
        int nbR = 0;
        for (Personne unePersonne: getR()) {
            nbR++;
        }
        int nbE = 0;
        for (Personne unePersonne: E) {
            nbE++;
        }
        this.getNbS().add(nbS);
        this.getNbI().add(nbI);
        this.getNbR().add(nbR);
        this.nbE.add(nbE);
        this.setLesPersonnes(new ArrayList<>());
        this.getLesPersonnes().addAll(getS());
        this.getLesPersonnes().addAll(getI());
        this.getLesPersonnes().addAll(getR());
        this.getLesPersonnes().addAll(E);
    }

    /**
     * Incrémente la simulation d'un jour
     */
    @Override
    protected void incrSimu() {
        incuber();
        infection();
        retablissement();
        compter();
        this.setJourActuel(this.getJourActuel()+1);
    }

    /**
     * Gère les infections des personne de la simulation
     */
    @Override
    protected void infection() {
        ArrayList<Personne> lesInfectes = new ArrayList<>(getI());
        for (Personne unInfecte: lesInfectes) {
            for (int i = 0; i < getNbrInteractions(); i++) {
                int nbrRandom = random(getLesPersonnes().size());
                Personne unePersRandom = getLesPersonnes().get(nbrRandom);
                if (unInfecte.rencontrerSEIR(unePersRandom, getBeta())) {
                    this.getS().remove(unePersRandom);
                    this.E.add(unePersRandom);
                }
            }
        }
    }

    /**
     * Gère les incubations des personne de la simulation
     */
    protected void incuber() {
        ArrayList<Personne> lesIncubes = new ArrayList<>(E);
        for (Personne unInfecte: lesIncubes) {
            if (unInfecte.incubassion(alpha)) {
                E.remove(unInfecte);
                getI().add(unInfecte);
            }
        }
    }

    /**
     * Gère les rétablissement des personne de la simulation
     */
    @Override
    protected void retablissement() {
        ArrayList<Personne> lesInfectes = new ArrayList<>(getI());
        for (Personne unInfecte: lesInfectes) {
            if (unInfecte.retablissement(getGamma())) {
                getI().remove(unInfecte);
                getR().add(unInfecte);
            }
        }
    }

    /**
     * Effectue la simulation
     */
    @Override
    protected void simuler() {
        compter();
        this.setJourActuel(this.getJourActuel()+1);
        while (getJourActuel() < getTempsSimulation()) {
            incrSimu();
        }
        genererExcel.SEIEVOint(getNbS(), nbE, getNbI(), getNbR());
    }

    /**
     * Lance la simulation et
     * @return Les valeurs de la simulation
     */
    @Override
    public ArrayList<ArrayList<Float>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Float>> listeValeurs = new ArrayList<>();
        return listeValeurs;
    }

    /**
     * @param uneSimu Simulation avec laquelle les personnes seront échangés
     * @param nbPersonnesEchanges Nombre de personne à échanger
     */
    public void EchangerPersonne(SEIR uneSimu, int nbPersonnesEchanges) {
        for (int i = 0; i < nbPersonnesEchanges; i++) {
            Personne unePersGrille1 = this.getLesPersonnes().get(random(this.getLesPersonnes().size()));
            Personne unePersGrille2 = uneSimu.getLesPersonnes().get(random(uneSimu.getLesPersonnes().size()));
            switch (unePersGrille1.getStatus()) {
                case "S":
                    this.getS().remove(unePersGrille1);
                    this.getLesPersonnes().remove(unePersGrille1);
                    uneSimu.getS().add(unePersGrille1);
                    uneSimu.getLesPersonnes().add(unePersGrille1);
                    break;
                case "E":
                    this.getE().remove(unePersGrille1);
                    this.getLesPersonnes().remove(unePersGrille1);
                    uneSimu.getE().add(unePersGrille1);
                    uneSimu.getLesPersonnes().add(unePersGrille1);
                    break;
                case "I":
                    this.getI().remove(unePersGrille1);
                    this.getLesPersonnes().remove(unePersGrille1);
                    uneSimu.getI().add(unePersGrille1);
                    uneSimu.getLesPersonnes().add(unePersGrille1);
                    break;
                case "R":
                    this.getR().remove(unePersGrille1);
                    this.getLesPersonnes().remove(unePersGrille1);
                    uneSimu.getR().add(unePersGrille1);
                    uneSimu.getLesPersonnes().add(unePersGrille1);
                    break;
            }
            switch (unePersGrille2.getStatus()) {
                case "S":
                    uneSimu.getS().remove(unePersGrille2);
                    uneSimu.getLesPersonnes().remove(unePersGrille2);
                    this.getS().add(unePersGrille2);
                    this.getLesPersonnes().add(unePersGrille2);
                    break;
                case "E":
                    uneSimu.getE().remove(unePersGrille2);
                    uneSimu.getLesPersonnes().remove(unePersGrille2);
                    this.getE().add(unePersGrille2);
                    this.getLesPersonnes().add(unePersGrille2);
                    break;
                case "I":
                    uneSimu.getI().remove(unePersGrille2);
                    uneSimu.getLesPersonnes().remove(unePersGrille2);
                    this.getI().add(unePersGrille2);
                    this.getLesPersonnes().add(unePersGrille2);
                    break;
                case "R":
                    uneSimu.getR().remove(unePersGrille2);
                    uneSimu.getLesPersonnes().remove(unePersGrille2);
                    this.getR().add(unePersGrille2);
                    this.getLesPersonnes().add(unePersGrille2);
                    break;
            }
        }
    }

    /**
     * @param chance Le taux de réussite
     * @return true si l'experience est réussi, false sinon
     */
    protected boolean chance(float chance) {
        return (random(100) < (chance * 100));
    }


    /**
     * Partie GETTER et SETTER
     */



    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public ArrayList<Personne> getE() {
        return E;
    }

    public void setE(ArrayList<Personne> e) {
        E = e;
    }

    public ArrayList<Integer> getNbE() {
        return nbE;
    }

    public void setNbE(ArrayList<Integer> nbE) {
        this.nbE = nbE;
    }
}
