package fr.atos.tp2.dto;

public class CommandeTdo {

    private String reference;
    private String total;
    private String totalMin;
    private String totalMax;


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalMin() {
        return totalMin;
    }

    public void setTotalMin(String totalMin) {
        this.totalMin = totalMin;
    }

    public String getTotalMax() {
        return totalMax;
    }

    public void setTotalMax(String totalMax) {
        this.totalMax = totalMax;
    }
}
