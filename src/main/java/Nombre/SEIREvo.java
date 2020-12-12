package Nombre;

import Taux.genererExcel;

import java.util.ArrayList;

public class SEIREvo extends SEIR {

    //taux de naissance par jour pour 100000 habitants
    private float n;

    //taux de mortalité par jour naturel pour 100000 habitant
    private float u;

    /**
     * @param nbPersonnes Nombre de personne de la simulation
     * @param nbrInteractions Nombre d'interaction par personne avec les gens sur la même case
     * @param alpha Probabilité d'avoir fini l'incubation
     * @param beta Probabilité d'infecter quelq'un
     * @param gamma probabilité de ne plus être infecté
     * @param tempsSimulation Temps de la simulation en Jours
     * @param n taux de naissance par jour pour 100000 habitants
     * @param u taux de mortalité par jour naturel pour 100000 habitant
     */
    public SEIREvo(int nbPersonnes, int nbrInteractions, float alpha, float beta, float gamma, int tempsSimulation, float n, float u) {
        super(nbPersonnes, nbrInteractions, alpha, beta, gamma, tempsSimulation);
        this.n = n;
        this.u = u;
    }

    /**
     * Incrémente la simulation d'un jour
     */
    @Override
    public void incrSimu() {
        incuber();
        infection();
        retablissement();
        naissance();
        mortNaturelle();
        compter();
        this.setJourActuel(this.getJourActuel()+1);
    }

    /**
     * Gère les naissances de la simulation
     */
    protected void naissance() {
        int nbrNaissance = getNbPour100000(n);
        for (int i = 0; i < nbrNaissance; i++) {
            getS().add(new Personne());
        }
    }

    /**
     * Gère les morts naturelles de la simulation
     */
    protected void mortNaturelle() {
        int nbrMort = getNbPour100000(u);
        for (int i = 0; i < nbrMort; i++) {
            int nbrPersonne = getS().size() + getE().size()+getI().size()+getR().size();
            Personne unFuturMort = getLesPersonnes().get(random(nbrPersonne));
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
        genererExcel.SEIEVOint(getNbS(), getNbE(), getNbI(), getNbR());
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
     * @param taux100000 Taux pour 100000habitants
     * @return nbr d'habitants touchés par rapport à cette simulation
     */
    private int getNbPour100000(float taux100000) {
        int nbNaissance = 0;
        float taux = taux100000/100000;
        for (int i = 0; i < 100000; i++) {
            if (chance(taux))
                nbNaissance++;
        }
        return nbNaissance;
    }
}
