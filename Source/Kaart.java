package com.company;

/**
 * Created by Yentl-PC on 22/03/2016.
 */
public class Kaart {
    private String naam;
    private int kost;
    private String type;
    private String omschrijving;

    public Kaart(String naam, int kost, String type, String omschrijving){
        this.naam = naam;
        this.kost = kost;
        this.type = type;
        this.omschrijving = omschrijving;
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

    public void voegKaartenToe(int aantal, Deck deck){
        deck.voegKaartenToeAanHand(aantal);
    }

    public void voegActiesToe(int aantal, Beurt beurt){
        beurt.setActies(aantal);
    }

    @Override
    public String toString() {
        return getNaam() + "\n" + getKost() + "\n" + getType() + "\n" + getOmschrijving();
    }
}
