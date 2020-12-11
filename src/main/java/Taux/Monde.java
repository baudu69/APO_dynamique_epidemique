package Taux;

import java.util.ArrayList;

public class Monde {
    private ArrayList<ArrayList<SEIREvo>> grille;
    private ArrayList<Float> S;
    private ArrayList<Float> E;
    //Nbr de personnes Infectés
    private ArrayList<Float> I;
    //Nbr de personnes guéris ou mortes
    private ArrayList<Float> R;

    private int tempsSimu;
    private int nbCases;

    public Monde(int taille, float E, float I, float beta, float alpha, float gamma, float n, float u, int tempsSimulation) {
        this.grille = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            ArrayList<SEIREvo> uneLigne = new ArrayList<>();
            for (int j = 0; j < taille; j++) {
                uneLigne.add(new SEIREvo(E, I, beta, alpha, gamma, n, u, tempsSimulation));
            }
            grille.add(uneLigne);
        }
        this.S = new ArrayList<>();
        this.E = new ArrayList<>();
        this.I = new ArrayList<>();
        this.R = new ArrayList<>();
        this.tempsSimu = tempsSimulation;
        this.nbCases = taille*taille;
        infecterAleatoire();

    }

    public void LancerSimulation() {
        for (int i = 0; i < tempsSimu; i++) {
            for (ArrayList<SEIREvo> uneLigne: grille) {
                for (SEIREvo unLieu: uneLigne) {
                    unLieu.incrSimuler();
                }
            }
        }
    }

    public void recupererResultat() {
        for (int i = 0; i < tempsSimu; i++) {
            this.S.add(0f);
            this.E.add(0f);
            this.I.add(0f);
            this.R.add(0f);
        }


        for (int i = 0; i < tempsSimu; i++) {
            for (ArrayList<SEIREvo> uneLigne: grille) {
                for (SEIREvo unLieu: uneLigne) {
                    ArrayList<ArrayList<Float>> result = unLieu.getResultSimu();
                    AjouterListe(result.get(0), result.get(1), result.get(2), result.get(3));
                }
            }
        }

        normaliser();
        genererExcel.SEIEVO(S, E, I, R);
    }

    public void AjouterListe(ArrayList<Float> sAdd, ArrayList<Float> eAdd, ArrayList<Float> iAdd, ArrayList<Float> rAdd) {
        for (int i = 0; i < this.S.size(); i++) {
            S.set(i, (this.S.get(i) + sAdd.get(i)));
            E.set(i, (this.E.get(i) + eAdd.get(i)));
            I.set(i, (this.I.get(i) + iAdd.get(i)));
            R.set(i, (this.R.get(i) + rAdd.get(i)));
        }
    }

    public void normaliser() {
        for (int i = 0; i < this.R.size(); i++) {
            S.set(i, (this.S.get(i) / nbCases));
            E.set(i, (this.E.get(i) / nbCases));
            I.set(i, (this.I.get(i) / nbCases));
            R.set(i, (this.R.get(i) / nbCases));
        }
    }

    public void infecterAleatoire() {
        int x = random(grille.size());
        int y = random(grille.get(x).size());
        grille.get(x).get(y).getI().set(0, 0.01f);
    }

    private int random(int max) {
        return (int) (Math.random() * (max));
    }
}
