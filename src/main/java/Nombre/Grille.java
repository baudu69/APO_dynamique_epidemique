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
    private float tauxEchange;

    private float tauxVaccinationParJour;

    //Grille Totale
    private ArrayList<ArrayList<SEIREvo>> laGrille;

    private ArrayList<ArrayList<Integer>> parametre;

    private App fenetre;

    private boolean confinement;
    private boolean masque;
    private boolean quarantaine;
    private boolean vaccination;

    public Grille(App fenetre, int taille, int nbPersonnes, int nbrInteractions, float alpha, float beta, float gamma, int tempsSimulation, float n, float u, ArrayList<ArrayList<Integer>> parametre, float tauxEchange, float tauxVaccinationParJour) {
        this.taille = taille;
        this.laGrille = new ArrayList<>();
        this.tauxEchange = tauxEchange;
        this.tauxVaccinationParJour = tauxVaccinationParJour;
        for (int i = 0; i < taille; i++) {
            ArrayList<SEIREvo> uneLigne = new ArrayList<>();
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

        this.fenetre=fenetre;
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
            fenetre.getLbl_chargement().setText("Chargement : " + jourActuel + " jours");
            System.out.println("Jour " + jourActuel);
            jourActuel++;
        }
    }

    public void Vacciner() {
        if (this.vaccination) {
            for (ArrayList<SEIREvo> uneLigne : laGrille) {
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
                    for (ArrayList<SEIREvo> uneLigne: laGrille) {
                        for (SEIR uneSimu: uneLigne) {
                            uneSimu.masquer();
                        }
                    }
                else
                    for (ArrayList<SEIREvo> uneLigne: laGrille) {
                        for (SEIR uneSimu: uneLigne) {
                            uneSimu.deMasquer();
                        }
                    }
            }
        });
        this.parametre.get(2).forEach((jour) -> {
            if (jour == jourActuel) {
                this.quarantaine = !this.quarantaine;
                for (ArrayList<SEIREvo> uneLigne: laGrille) {
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
