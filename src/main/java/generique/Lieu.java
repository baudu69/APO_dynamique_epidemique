package generique;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Cette classe correspond à un lieu de la grille
 * Elle contient des listes d'objets de types personnes de différents ages
 * 0-10, 10-25, 25-40, 40-60, 60-80, 80-100 appellé respectivement
 * Enfant, Ado, Adute, Parent, Grand-parents, Arrière-grand-parents
 * Toutes ces classes dérivent de la classe Humain
 */
public class Lieu {
    private int nbHumains;
    private ArrayList<Humain> lesEnfants = new ArrayList<>();
    private ArrayList<Humain> lesAdos = new ArrayList<>();
    private ArrayList<Humain> lesAdultes = new ArrayList<>();
    private ArrayList<Humain> lesParents = new ArrayList<>();
    private ArrayList<Humain> lesGParents = new ArrayList<>();
    private ArrayList<Humain> lesAGParents= new ArrayList<>();

    private int nbrInteractMoyEnfant = 10;
    private int nbrInteractMoyAdos = 10;
    private int nbrInteractMoyAdultes = 10;
    private int nbrInteractMoyParents = 10;
    private int nbrInteractMoyGParents = 10;
    private int nbrInteractMoyAGParents = 10;

    private float alpha = 0.02f;
    private float beta = 0.02f;
    private float gamma = 0.02f;

    private int nbJours;

    public Lieu(int nbPersonnesParCat, int nbJours) {
        for (int i=0; i < nbPersonnesParCat; i++) {
            lesEnfants.add(new Humain());
            lesAdos.add(new Humain());
            lesAdultes.add(new Humain());
            lesParents.add(new Humain());
            lesGParents.add(new Humain());
            lesAGParents.add(new Humain());
        }
        lesEnfants.add(new Humain("I"));
        lesAdos.add(new Humain("I"));
        lesAdultes.add(new Humain("I"));
        lesParents.add(new Humain("I"));
        lesGParents.add(new Humain("I"));
        lesAGParents.add(new Humain("I"));

        this.nbJours = nbJours;

    }

    public void lancerSimulation() {
        for (int i = 0; i < nbJours; i++) {
            IncrIncuberSansMelange();
        }
        System.out.println(getNbEnfantInfecte());
    }

    public void IncrIncuberSansMelange() {
        for (Humain unEnfant: lesEnfants) {
            if (unEnfant.getStatus().equals("I")) {
                for (int i=0; i<nbrInteractMoyEnfant; i++) {
                    tentativeInfection(lesEnfants.get(random(lesEnfants.size())));
                }
            }
        }
    }

    public void IncrInfecter() {
        ArrayList<Humain> lesHumains = getTousLesHumains();
        for (Humain unHumain: lesHumains) {
            if (unHumain.getStatus().equals("E")) {
                if (testChance(alpha)) {
                    unHumain.setStatus("I");
                }
            }
        }
    }

    public ArrayList<Humain> getTousLesHumains() {
        ArrayList<Humain> lesHumains = new ArrayList<>(lesEnfants);
        lesHumains.addAll(lesAdos);
        lesHumains.addAll(lesAdultes);
        lesHumains.addAll(lesParents);
        lesHumains.addAll(lesGParents);
        lesHumains.addAll(lesGParents);
        return lesHumains;
    }

    public int getNbEnfantInfecte() {
        int nbEnfantsInfecte = 0;
        for (Humain unEnfant: lesEnfants) {
            if (!unEnfant.getStatus().equals("S"))
                nbEnfantsInfecte++;
        }
        return nbEnfantsInfecte;
    }

    public void tentativeInfection(Humain unHumain) {
        if (unHumain.getStatus().equals("S")) {
            if (testChance(beta))
                unHumain.setStatus("E");
        }
    }

    private int random(int max) {
        return (int) (Math.random() * (max));
    }

    private boolean testChance(float chance) {
        return (random(100)<chance*100);
    }
}
