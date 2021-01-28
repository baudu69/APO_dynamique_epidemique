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
        naissance();
        mortNaturelle();
        incuber();
        infection();
        retablissement();
        compter();
        this.setJourActuel(this.getJourActuel()+1);
    }

    /**
     * Gère les naissances de la simulation
     */
    protected void naissance() {
        int nbrNaissance = getPourNPers(n);
        for (int i = 0; i < nbrNaissance; i++) {
            getS().add(new Personne());
        }
    }

    /**
     * Gère les morts naturelles de la simulation
     */
    protected void mortNaturelle() {
        int nbrMort = getPourNPers(u);
        if (getLesPersonnes().size() != 0) {
            for (int i = 0; i < nbrMort; i++) {
                //System.out.println(getLesPersonnes().size());
                int rand = random(getLesPersonnes().size() - 1);
                if (rand == 0) rand=1;
                //System.out.println("rand= " + rand + " et size = " + getLesPersonnes().size());
                try {
                    Personne unFuturMort = getLesPersonnes().get(rand);

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
                    getLesPersonnes().remove(unFuturMort);
                } catch (IndexOutOfBoundsException erreur) {
                    System.out.println("Erreur rand= " + rand + " et size = " + getLesPersonnes().size());
                }
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
    private int getPourNPers(float taux100000) {
        int nbNaissance = 0;
        int nbPersonne = getLesPersonnes().size();
        float taux = taux100000/nbPersonne;
        for (int i = 0; i < nbPersonne; i++) {
            if (chance(taux))
                nbNaissance++;
        }
        return nbNaissance;
    }
}
