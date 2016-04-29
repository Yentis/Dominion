package com.company;

import java.lang.reflect.Array;
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
    private List<Kaart> vuilbak = new ArrayList();
    private int geld;
    private int koop;
    private int actie;
    private boolean beurt;

    public void leegAflegstapel(Spel spel){
        for(Kaart k : aflegstapel){
            deck.add(k);
        }
        aflegstapel.clear();
        voegKaartToe(5, deck, hand);
    }

    public void setDeck(List<Kaart> deck) {
        this.deck = deck;
    }

    public void setBeurt(boolean beurt) {
        this.beurt = beurt;
    }

    public List<Kaart> getVuilbak() {
        return vuilbak;
    }

    public int getKoop() {
        return koop;
    }

    public int getActie() {
        return actie;
    }

    public void setGeld(int geld) {
        this.geld = geld;
    }

    public void setKoop(int koop) {
        this.koop = koop;
    }

    public void setActie(int actie) {
        this.actie = actie;
    }

    public void addOverwinningspunten(int x) { overwinningspunten += x; }

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

    public void voegKaartToe(int aantalKaarten, List<Kaart> startpunt, List<Kaart> bestemming){
        for(int  i=0; i<aantalKaarten;i++){
            bestemming.add(startpunt.get(i));
        }
        for(int  i=0; i<aantalKaarten;i++){
            startpunt.remove(0);
        }
    }
}
