package com.company;

/**
 * Created by Yentl-PC on 22/03/2016.
 */
public class Kaart {
    private int nr;
    private String naam;
    private int kost;
    private String type;
    private String omschrijving;
    private int waarde;

    public Kaart(int nr, String naam, int kost, String type, String omschrijving, int waarde){
        this.nr = nr;
        this.naam = naam;
        this.kost = kost;
        this.type = type;
        this.omschrijving = omschrijving;
        this.waarde = waarde;
    }

    public Kaart(){
        naam = "Placeholder";
        kost = 0;
        type = "Placeholder";
        omschrijving = "Placeholder";
        waarde = 0;
    }

    public int getNr() {
        return nr;
    }

    public int getWaarde() {
        return waarde;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    public String getNaam() {
        return naam;
    }

    public void setKost(int kost) {
        this.kost = kost;
    }
    public int getKost() {
        return kost;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public String getOmschrijving() {
        return omschrijving;
    }

    @Override
    public String toString() {
        return getNaam() + "\n" + getKost() + "\n" + getType() + "\n" + getOmschrijving();
    }
}