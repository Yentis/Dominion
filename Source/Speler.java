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

    public Speler(String naam, int overwinningspunten)
    {
        this.naam = naam;
        this.overwinningspunten = overwinningspunten;
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
}
