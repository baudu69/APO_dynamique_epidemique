package generique;

public class Humain {
    private String status;

    public Humain() {
        this.status="S";
    }
    public Humain(String status) {
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
