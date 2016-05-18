package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yentl-PC on 14/04/2016.
 */
public class Speler {
    //region Instance Variables
    private String naam;
    private int overwinningspunten = 0;
    private List<Kaart> deck = new ArrayList<>();
    private List<Kaart> hand = new ArrayList<>();
    private List<Kaart> aflegstapel = new ArrayList<>();
    private List<Kaart> vuilbak = new ArrayList<>();
    private int geld;
    private int koop;
    private int actie;
    private boolean beurt;
    //endregion

    //region Constructors
    public Speler(String naam)
    {
        this.naam = naam;
    }
    //endregion

    //region Properties
    public List<Kaart> getVuilbak() {
        return vuilbak;
    }

    public int getKoop() {
        return koop;
    }

    public int getActie() {
        return actie;
    }

    public List<Kaart> getAflegstapel() {
        return aflegstapel;
    }

    public int getGeld() {
        return geld;
    }

    public int getOverwinningspunten() {
        return overwinningspunten;
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

    public void setGeld(int geld) {
        this.geld = geld;
    }

    public void setKoop(int koop) {
        this.koop = koop;
    }

    public void setActie(int actie) {
        this.actie = actie;
    }
    //endregion

    //region Behaviour
    public void leegAflegstapel(){
        for(Kaart k : aflegstapel){
            deck.add(k);
        }
        aflegstapel.clear();
        voegKaartToe(5, deck, hand);
    }

    public void berekenScore(){
        int score = 0;
        for(Kaart k : aflegstapel){
            score += overwinningswaardeToevoegen(k);
        }
        for(Kaart k : deck){
            score += overwinningswaardeToevoegen(k);
        }
        for(Kaart k : hand){
            score += overwinningswaardeToevoegen(k);
        }
        overwinningspunten=score;
    }

    public int overwinningswaardeToevoegen(Kaart k){
        if(Objects.equals(k.getType(), "Overwinning") || Objects.equals(k.getType(), "Vloek")){
            return k.getWaarde();
        }
        return 0;
    }

    public void addOverwinningspunten(int x) { overwinningspunten += x; }

    public void addKoop(int x) { koop += x;}

    public void addActie(int x) { actie += x;}

    public void addGeld(int x){
        geld += x;
    }

    public void verwijderKaart(Kaart k, int i){
        aflegstapel.add(k);
        hand.remove(i);
    }

    public void voegKaartToe(int aantalKaarten, List<Kaart> startpunt, List<Kaart> bestemming){
        for(int  i=0; i<aantalKaarten;i++){
            bestemming.add(startpunt.get(i));
        }

        for(int i=0; i<aantalKaarten;i++){
            startpunt.remove(0);
        }
    }
    //endregion
}