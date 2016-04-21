package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yentl-PC on 14/04/2016.
 */
public class Speler {
    private String naam;
    private int overwinningspunten;
    private List<Kaart> deck = new ArrayList();
    private List<Kaart> hand = new ArrayList();
    private List<Kaart> aflegstapel = new ArrayList();
    private int geld;
    private int koop;
    private int actie;

    public void addKoop(int x) { koop += x;}
    
    public void addActie(int x) { actie += x;}
    
    public List<Kaart> getAflegstapel() {
        return aflegstapel;
    }

    public int getGeld() {
        return geld;
    }

    public int getOverwinningspunten() {
        return overwinningspunten;
    }

    public Speler(String naam, int overwinningspunten)
    {
        this.naam = naam;
        this.overwinningspunten = overwinningspunten;
    }

    public void addGeld(int x){
        geld += x;
    }

    public void addPunten(int x){
        overwinningspunten += x;
    }

    public List<Kaart> getHand() {
        return hand;
    }

    public List<Kaart> getDeck() {
        return deck;
    }

    public String getNaam() {
        return naam;
    }

    public void verwijderKaart(Kaart k, int i){
        aflegstapel.add(k);
        hand.remove(i);
    }
}
