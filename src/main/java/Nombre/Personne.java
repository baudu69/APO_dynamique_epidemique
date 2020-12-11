package Nombre;

public class Personne {
    private String status;

    public Personne() {
        this.status = "S";
    }
    public Personne(String status) {
        this.status = status;
    }

    public boolean rencontrerSIR(Personne unePersonne, float beta) {
        if (unePersonne.getStatus().equals("S") && chance(beta)) {
            unePersonne.setStatus("I");
            return true;
        } else
            return false;
    }

    public boolean rencontrerSEIR(Personne unePersonne, float beta) {
        if (unePersonne.getStatus().equals("S") && chance(beta)) {
            unePersonne.setStatus("E");
            return true;
        } else
            return false;
    }

    public boolean estInfecte() {
        return this.status.equals("I");
    }

    private boolean chance(float chance) {
        return (random(100) < (chance * 100));
    }

    public boolean retablissement(float chance) {
        if (random(100) < (chance * 100)) {
            this.status = "R";
            return true;
        }
        else
            return false;
    }

    public boolean incubassion(float chance) {
        if (random(100) < (chance * 100)) {
            this.status = "I";
            return true;
        }
        else
            return false;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int random(int max) {
        return ((int) (Math.random() * max));
    }
}
