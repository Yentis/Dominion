package com.company;

/**
 * Created by Yentl-PC on 22/03/2016.
 */
public class Kaart {
    //region Instance Variables
    private int nr;
    private String naam;
    private int kost;
    private String type;
    private String omschrijving;
    private int waarde;
    //endregion

    //region Constructors
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
    //endregion

    //region Properties
    public int getNr() {
        return nr;
    }

    public int getWaarde() {
        return waarde;
    }

    public String getNaam() {
        return naam;
    }

    public int getKost() {
        return kost;
    }

    public String getType() {
        return type;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
    //endregion

    @Override
    public String toString() {
        return getNaam() + "\n" + getKost() + "\n" + getType() + "\n" + getOmschrijving();
    }
}