package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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

    public void kelder(Speler speler){
        //+1 actie, selecteer de kaarten die je wilt afleggen, voeg hetzelfde kaarten toe aan de hand vanuit deck
        speler.addActie(1);
    }

    public void kerk(Spel spel){
        //plaats tot 4 kaarten in de vuilbak

    }

    public void gracht(Spel spel)
    {
        spel.voegKaartToe(2, deck, hand);
        //counter-card
    }

    public void kanselier(Spel spel,Speler speler){
        //+2 geld
        speler.addGeld(2);
        //gooi deck in aflegstapel
        spel.voegKaartToe(deck.size(),deck,aflegstapel);

    }
    public void dorp(Spel spel,Speler speler){

        //+1kaart ok
        spel.voegKaartToe(1, deck, hand);
        //+2 acties
        speler.addActie(2);

    }
    public void houthakker(Spel spel, Speler speler){
        //+1 koop ok
        speler.addKoop(1);
        //+2 geld ok
        speler.addGeld(2);

    }
    public void werkplaats(Spel spel){
        //neem een kaart met >4 kost
    }
    public void bureaucraat(Spel spel,Speler speler){
        //+1 actiekaart
        speler.addActie(1);
        //elk ander speler toont  een overwinningskaart en plaatst het op zijn deck (of toont een hand zonder overwinningskaarten)


    }
    public void feest(Spel spel,Speler speler){
        //naar thrash
       // speler.verwijderKaart(this,); to be continued
        //neem kaart die max 5 geld kost

    }
    public void schutterij(Spel spel,Speler speler){
        //+2Geld
        speler.addGeld(2);
        //leg kaarten af tot alle spelers 3 kaarten over heeft
        for(Speler s : spel.getSpelers()){
            if(!Objects.equals(s.getNaam(), speler.getNaam())){
                while(s.getHand().size()>3){
                    int i = 0;
                    Scanner keyboard = new Scanner(System.in);
                    String input;

                    System.out.println("Kies de kaarten die je wilt verwijderen: \n");
                    for(Kaart k: speler.getHand()){
                        System.out.println(k.getNaam() + " | " + i);
                        input = keyboard.nextLine();
                        Kaart teverwijderenkaart = s.getHand().get(Integer.parseInt(input));
                        s.verwijderKaart(teverwijderenkaart, i);
                        i++;
                    }
                }
            }
        }
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
    public void raadzaal(Spel spel,Speler speler){
        spel.voegKaartToe(4,deck,hand);
        //+1 koop
        speler.addKoop(1);
    }

    public void festival(Spel spel,Speler speler){
        //+2 acties +1 kaart +2geld
        spel.voegKaartToe(1,deck,hand);
        speler.addGeld(2);
        speler.addActie(2);
    }
    public void laboratorium(Spel spel,Speler speler){
        spel.voegKaartToe(2, deck, hand);
        //+1actie
        speler.addActie(1);
    }
    public void bibliotheek(Spel spel){
        while (hand.size()>7){
            spel.voegKaartToe(1,deck,hand);
        }
    }
    public void markt(Spel spel,Speler speler){
        spel.voegKaartToe(1,deck,hand);
        //+1geld +1 koop +1actie
        speler.addActie(1);
        speler.addKoop(1);
        speler.addGeld(1);
    }
    public void mijn(Spel spel){
        //thrash een geldkaart en geef de geldkaart met 1 waarde meer
    }
    public void avonturier(Spel spel){
        //blijf kaarten trekken tot je 2 geldkaarten krijgt
    }
}
