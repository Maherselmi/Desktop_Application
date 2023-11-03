package org.example;


public class matriel {
    private int id;
    private String type;
    private String marque;
    private String date2;
    private String etat;

    public matriel(int id, String type, String marque, String date2, String etat) {
        this.id = id;
        this.type = type;
        this.marque = marque;
        this.date2 = date2;
        this.etat = etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDatedajout(String datedajout) {
        this.date2 = date2;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMarque() {
        return marque;
    }

    public String getDateaj() {
        return date2;
    }

    public String getEtat() {
        return etat;
    }
}