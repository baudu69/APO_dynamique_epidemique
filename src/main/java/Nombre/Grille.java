package Nombre;

import java.util.ArrayList;

public class Grille {
    //Taille de la grille
    private int taille;

    //Nombre de jour de la simulation
    private int nbJoursSimulation;

    //Jour actuel de la simulation
    private int jourActuel;

    //Taux d'échange avec une case voisine
    private float tauxEchange = 0.1f;

    //Grille Totale
    private ArrayList<ArrayList<SEIR>> laGrille;

    public Grille(int taille, int nbPersonnes, int nbrInteractions, float alpha, float beta, float gamma, int tempsSimulation, float n, float u) {
        this.taille = taille;
        this.laGrille = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            ArrayList<SEIR> uneLigne = new ArrayList<>();
            for (int j = 0; j < taille; j++) {
                uneLigne.add(new SEIREvo(nbPersonnes, nbrInteractions, alpha, beta, gamma, tempsSimulation, n, u));
                uneLigne.get(j).getI().remove(0);
            }
            laGrille.add(uneLigne);
        }
        laGrille.get(0).get(0).getI().add(new Personne("I"));

        this.nbJoursSimulation = tempsSimulation;
        this.jourActuel = 0;
    }

    public Grille(int taille, int nbPersonnes, int nbrInteractions, float alpha, float beta, float gamma, int tempsSimulation) {
        this.taille = taille;
        this.laGrille = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            ArrayList<SEIR> uneLigne = new ArrayList<>();
            for (int j = 0; j < taille; j++) {
                uneLigne.add(new SEIR(nbPersonnes, nbrInteractions, alpha, beta, gamma, tempsSimulation));
                uneLigne.get(j).getI().remove(0);
            }
            laGrille.add(uneLigne);
        }
        laGrille.get(0).get(0).getI().add(new Personne("I"));

        this.nbJoursSimulation = tempsSimulation;
        this.jourActuel = 0;
    }

    public void initSimu() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                laGrille.get(i).get(j).compter();
                laGrille.get(i).get(j).setJourActuel(1);
            }
        }
        jourActuel++;
    }

    public void LancerSimulationComplete() {
        initSimu();
        while(jourActuel < nbJoursSimulation) {
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    laGrille.get(i).get(j).incrSimu();
                    mouvementPersonne(i, j);
                }
            }
            System.out.println("Jour " + this.jourActuel);
            jourActuel++;
        }
    }

    public ArrayList<ArrayList<Integer>> getLesResultats() {
        ArrayList<ArrayList<Integer>> lesResults = new ArrayList<>();
        ArrayList<Integer> lesS = new ArrayList<>();
        ArrayList<Integer> lesE = new ArrayList<>();
        ArrayList<Integer> lesI = new ArrayList<>();
        ArrayList<Integer> lesR = new ArrayList<>();
        for (int i = 0; i < jourActuel; i++) {
            int SJ1 = 0;
            int EJ1 = 0;
            int IJ1 = 0;
            int RJ1 = 0;
            for (int j = 0; j < taille; j++) {
                for (int k = 0; k < taille; k++) {
                    SJ1 += laGrille.get(j).get(k).getNbS().get(i);
                    EJ1 += laGrille.get(j).get(k).getNbE().get(i);
                    IJ1 += laGrille.get(j).get(k).getNbI().get(i);
                    RJ1 += laGrille.get(j).get(k).getNbR().get(i);
                }
            }
            lesS.add(SJ1);
            lesE.add(EJ1);
            lesI.add(IJ1);
            lesR.add(RJ1);
        }
        lesResults.add(lesS);
        lesResults.add(lesE);
        lesResults.add(lesI);
        lesResults.add(lesR);
        return lesResults;
    }

    public void mouvementPersonne(int i, int j) {
        int nbPersonnesEchanges =  0;
        for(int k = 0; k < tauxEchange*laGrille.get(0).get(0).getLesPersonnes().size(); k++) {
            if (chance(tauxEchange))
                nbPersonnesEchanges++;
        }

        if (i - 1 >= 0)
            laGrille.get(i).get(j).EchangerPersonne(laGrille.get(i - 1).get(j), 10);
        if (i + 1 <= taille - 1)
            laGrille.get(i).get(j).EchangerPersonne(laGrille.get(i + 1).get(j), 10);
        if (j - 1 >= 0)
            laGrille.get(i).get(j).EchangerPersonne(laGrille.get(i).get(j - 1), 10);
        if (j + 1 <= taille - 1)
            laGrille.get(i).get(j).EchangerPersonne(laGrille.get(i).get(j + 1), 10);
    }

    private boolean chance(float chance) {
        return (random(100) < (chance * 100));
    }

    private int random(int max) {
        return ((int) (Math.random() * max));
    }


}
