package Nombre;

import Taux.genererExcel;

import java.util.ArrayList;

public class SEIR extends SIR {

    //Probabilité d'être contagieux
    private float alpha;

    private ArrayList<Personne> E;

    private ArrayList<Integer> nbE;

    public SEIR(int nbPersonnes, int nbrInteractions,float alpha, float beta, float gamma, int tempsSimulation) {
        super(nbPersonnes, nbrInteractions, beta, gamma, tempsSimulation);
        this.alpha = alpha;

        this.E = new ArrayList<>();
        this.nbE = new ArrayList<>();
    }

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

    protected void incrSimu() {
        incuber();
        infection();
        retablissement();
        compter();
        this.setJourActuel(this.getJourActuel()+1);
    }

    protected void infection() {
        ArrayList<Personne> lesInfectes = new ArrayList<>(getI());
        for (Personne unInfecte: lesInfectes) {
            for (int i = 0; i < getNbrInteractions(); i++) {
                Personne unePersRandom = getLesPersonnes().get(random(getLesPersonnes().size()));
                if (unInfecte.rencontrerSEIR(unePersRandom, getBeta())) {
                    this.getS().remove(unePersRandom);
                    this.E.add(unePersRandom);
                }
            }
        }
    }

    protected void incuber() {
        ArrayList<Personne> lesIncubes = new ArrayList<>(E);
        for (Personne unInfecte: lesIncubes) {
            if (unInfecte.incubassion(alpha)) {
                E.remove(unInfecte);
                getI().add(unInfecte);
            }
        }
    }

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
    protected void simuler() {
        compter();
        this.setJourActuel(this.getJourActuel()+1);
        while (getJourActuel() < getTempsSimulation()) {
            incrSimu();
        }
        genererExcel.SEIEVOint(getNbS(), nbE, getNbI(), getNbR());
    }

    public ArrayList<ArrayList<Float>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Float>> listeValeurs = new ArrayList<>();
        return listeValeurs;
    }

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
