package Nombre;

import Taux.genererExcel;

import java.util.ArrayList;

public class SEIREvo extends SEIR {

    //taux de naissance par jour pour 100000 habitants
    private float n;

    //taux de mortalité par jour naturel pour 100000 habitant
    private float u;

    public SEIREvo(int nbPersonnes, int nbrInteractions, float alpha, float beta, float gamma, int tempsSimulation, float n, float u) {
        super(nbPersonnes, nbrInteractions, alpha, beta, gamma, tempsSimulation);
        this.n = n;
        this.u = u;
    }

    @Override
    protected void incrSimu() {
        incuber();
        infection();
        retablissement();
        naissance();
        mortNaturelle();
        compter();
        this.setJourActuel(this.getJourActuel()+1);
    }

    protected void naissance() {
        int nbrNaissance = getNbPour100000(n);
        for (int i = 0; i < nbrNaissance; i++) {
            getS().add(new Personne());
        }
    }

    protected void mortNaturelle() {
        int nbrMort = getNbPour100000(u);
        for (int i = 0; i < nbrMort; i++) {
            Personne unFuturMort = getLesPersonnes().get(random(getLesPersonnes().size()));
            getLesPersonnes().remove(unFuturMort);
            String status = unFuturMort.getStatus();
            switch (status) {
                case "S":
                    getS().remove(unFuturMort);
                    break;
                case "E":
                    getE().remove(unFuturMort);
                    break;
                case "I":
                    getI().remove(unFuturMort);
                    break;
                case "R":
                    getR().remove(unFuturMort);
                    break;

            }
        }
    }

    @Override
    protected void simuler() {
        compter();
        this.setJourActuel(this.getJourActuel()+1);
        while (getJourActuel() < getTempsSimulation()) {
            incrSimu();
        }
        genererExcel.SEIEVOint(getNbS(), getNbE(), getNbI(), getNbR());
    }

    @Override
    public ArrayList<ArrayList<Float>> LancerSimulation() {
        simuler();
        ArrayList<ArrayList<Float>> listeValeurs = new ArrayList<>();
        return listeValeurs;
    }

    private int getNbPour100000(float taux100000) {
        int nbNaissance = 0;
        float taux = taux100000/100000;
        for (int i = 0; i < 100000; i++) {
            if (getChance(taux))
                nbNaissance++;
        }
        return nbNaissance;
    }

    protected boolean getChance(float chance) {
        return (random(100) < (chance*100));
    }
}
