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

    private float tauxVaccinationParJour = 0.2f;

    //Grille Totale
    private ArrayList<ArrayList<SEIR>> laGrille;

    private ArrayList<ArrayList<Integer>> parametre;

    private boolean confinement;
    private boolean masque;
    private boolean quarantaine;
    private boolean vaccination;

    public Grille(int taille, int nbPersonnes, int nbrInteractions, float alpha, float beta, float gamma, int tempsSimulation, float n, float u, ArrayList<ArrayList<Integer>> parametre) {
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

        this.parametre = parametre;

        this.confinement = false;
        this.masque = false;
        this.quarantaine = false;
        this.vaccination = false;
    }

    public Grille(int taille, int nbPersonnes, int nbrInteractions, float alpha, float beta, float gamma, int tempsSimulation, ArrayList<ArrayList<Integer>> parametre) {
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

        this.parametre = parametre;

        this.confinement = false;
        this.masque = false;
        this.quarantaine = false;
        this.vaccination = false;
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
            this.CheckMesures();
            this.Vacciner();
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

    public void Vacciner() {
        if (this.vaccination) {
            for (ArrayList<SEIR> uneLigne : laGrille) {
                for (SEIR uneSimu : uneLigne) {
                    for (int i = 0; i <uneSimu.getS().size() ; i++) {
                        Personne unePersonne = uneSimu.getS().get(i);
                        if (chance(tauxVaccinationParJour)) {
                            unePersonne.setStatus("R");
                            uneSimu.getS().remove(unePersonne);
                            uneSimu.getR().add(unePersonne);
                        }
                    }
                }
            }
        }
    }

    public void test() {

        if ((jourActuel > 2) && (jourActuel%10 == 0)) {
            ArrayList<ArrayList<Integer>> lesResult = getLesResultats();
            System.out.println("JeTest");
            if (lesResult.get(2).get(jourActuel - 1) >= 1000 && !confinement) {
                System.out.println("AAAAAAAAAAAAAAA");
                this.confinement = true;
                this.masque = true;
                for (ArrayList<SEIR> uneLigne : laGrille) {
                    for (SEIR uneSimu : uneLigne) {
                        uneSimu.masquer();
                    }
                }
                Confiner();
            } else if (lesResult.get(2).get(jourActuel - 1) <= 1000 && confinement){
                this.confinement = false;
                this.masque = false;
                for (ArrayList<SEIR> uneLigne : laGrille) {
                    for (SEIR uneSimu : uneLigne) {
                        uneSimu.deMasquer();
                    }
                }
                Deonfiner();
            }
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

    private void CheckMesures() {
        this.parametre.get(0).forEach((jour) -> {
            if (jour == jourActuel) {
                System.out.println("Confinement");
                this.confinement = !this.confinement;
                if (this.confinement)
                    this.Confiner();
                else
                    this.Deonfiner();
            }
        });
        this.parametre.get(1).forEach((jour) -> {
            if (jour == jourActuel) {
                this.masque = !this.masque;
                if (this.masque)
                    for (ArrayList<SEIR> uneLigne: laGrille) {
                        for (SEIR uneSimu: uneLigne) {
                            uneSimu.masquer();
                        }
                    }
                else
                    for (ArrayList<SEIR> uneLigne: laGrille) {
                        for (SEIR uneSimu: uneLigne) {
                            uneSimu.deMasquer();
                        }
                    }
            }
        });
        this.parametre.get(2).forEach((jour) -> {
            if (jour == jourActuel) {
                this.quarantaine = !this.quarantaine;
                for (ArrayList<SEIR> uneLigne: laGrille) {
                    for (SEIR uneSimu: uneLigne) {
                        uneSimu.setQuarantaine(!uneSimu.isQuarantaine());
                    }
                }
            }
        });
        this.parametre.get(3).forEach((jour) -> {
            if (jour == jourActuel) {
                this.vaccination = true;
            }
        });
    }

    public void Confiner() {
        this.tauxEchange /= 10;
    }

    public void Deonfiner() {
        this.tauxEchange *= 10;
    }

    private boolean chance(float chance) {
        return (random(100) < (chance * 100));
    }

    private int random(int max) {
        return ((int) (Math.random() * max));
    }


}
