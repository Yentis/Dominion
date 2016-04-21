package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renzie on 14/04/2016.
 */
public class Actiekaart {
    private List<Kaart> deck = new ArrayList();
    private List<Kaart> hand = new ArrayList();
    private List<Kaart> aflegstapel = new ArrayList();

    public void heks(){
        //geef de andere spelers 2 vloekkaarten

    }

    public void kelder(){
        //+1 actie, selecteer de kaarten die je wilt afleggen, voeg hetzelfde kaarten toe aan de hand vanuit deck
    }

    public void kerk(Spel spel){
        //plaats tot 4 kaarten in de vuilbak

    }

    public void gracht(Spel spel)
    {
        spel.voegKaartToe(2, deck, hand);
        //counter-card
    }

    public void kanselier(Spel spel){
        //+2 geld
        //gooi deck in aflegstapel
        spel.voegKaartToe(deck.size(),deck,aflegstapel);

    }
    public void dorp(Spel spel){

        //+1kaart ok
        spel.voegKaartToe(1, deck, hand);
        //+2 acties

    }
    public void houthakker(Spel spel){
        //+1 koop
        //+2 geld

    }
    public void werkplaats(Spel spel){
        //neem een kaart met >4 kost
    }
    public void bureaucraat(Spel spel){
        //+1 actiekaart
        //elk ander speler toont  een overwinningskaart en plaatst het op zijn deck (of toont een hand zonder overwinningskaarten)


    }
    public void feest(Spel spel){
        //naar thrash

        //neem kaart die max 5 geld kost

    }
    public void schutterij(Spel spel){
        //+2Geld
        //leg kaarten af tot alle spelers 3 kaarten over heeft

    }
    public void geldverlener(Spel spel){
        //thrash koper
        //krijg +3 geldkaart
    }
    public void ombouwen(Spel spel){
        //select kaart -> thrash
        //krijg een kaart die tot 2 meer geld kost
    }
    public void smederij(Spel spel){
        spel.voegKaartToe(3,deck,hand);

    }

    public void spion(Spel spel){
        spel.voegKaartToe(1,deck,hand);
        //elke speler bekijkt de bovenste kaart van de deck en de speler kan beslissen of het naar de aflegstapel gaat
    }
    public void dief(Spel spel){
        //bekijk de 2 bovenste kaarten van elk persoon zijn deck en beslis of je de kaart wilt thrashen
    }
    public void troonzaal(Spel spel){
        //kies een actiekaart
        //effect gekozen actiekaart*2
    }
    public void raadzaal(Spel spel){
        spel.voegKaartToe(4,deck,hand);
        //+1 koop
    }

    public void festival(Spel spel){
        //+2 acties +1 kaart +2geld
        spel.voegKaartToe(1,deck,hand);
    }
    public void laboratorium(Spel spel){
        spel.voegKaartToe(2, deck, hand);
        //+1actie
    }
    public void bibliotheek(Spel spel){
        while (hand.size()>7){
            spel.voegKaartToe(1,deck,hand);
        }
    }
    public void markt(Spel spel){
        spel.voegKaartToe(1,deck,hand);
        //+1geld +1 koop +1actie
    }
    public void mijn(Spel spel){
        //thrash een geldkaart en geef de geldkaart met 1 waarde meer
    }
    public void avonturier(Spel spel){
        //blijf kaarten trekken tot je 2 geldkaarten krijgt
    }
}
