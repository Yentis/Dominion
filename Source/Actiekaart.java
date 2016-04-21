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
    
    public void speelactiekaart(String naam, Speler speler, Spel spel){
        switch(naam){
            case "heks":
                heks();
                break;
            case "kelder":
                kelder(speler);
                break;
            case "kerk":
                kerk(speler);
                break;
            case "gracht":
                gracht(spel, speler);
                break;
            case "kanselier":
                kanselier(spel, speler);
                break;
            case "dorp":
                dorp(spel, speler);
                break;
            case "houthakker":
                houthakker(speler);
                break;
            case "werkplaats":
                werkplaats(spel);
                break;
            case "bureaucraat":
                bureaucraat(spel, speler);
                break;
            case "feest":
                feest(spel, speler);
                break;
            case "schutterij":
                schutterij(spel, speler);
                break;
            case "geldverlener":
                geldverlener(speler);
                break;
            case "ombouwen":
                ombouwen(spel);
                break;
            case "smederij":
                smederij(spel, speler);
                break;
            case "spion":
                spion(spel, speler);
                break;
            case "dief":
                dief(spel);
                break;
            case "troonzaal":
                troonzaal(spel, speler);
                break;
            case "raadzaal":
                raadzaal(spel, speler);
                break;
            case "festival":
                festival(spel, speler);
                break;
            case "laboratorium":
                laboratorium(spel, speler);
                break;
            case "bibliotheek":
                bibliotheek(spel, speler);
                break;
            case "markt":
                markt(spel, speler);
                break;
            case "mijn":
                mijn(spel);
                break;
            case "avonturier":
                avonturier(spel);
                break;
        }
    }

    public void heks(){
        //geef de andere spelers 2 vloekkaarten
        
    }

    public void kelder(Speler speler){
        //+1 actie, selecteer de kaarten die je wilt afleggen, voeg hetzelfde kaarten toe aan de hand vanuit deck
        speler.addActie(1);
        //leg kaarten af
    }

    public void kerk(Speler speler){
        //plaats tot 4 kaarten in de vuilbak
        
    }

    public void gracht(Spel spel, Speler speler)
    {
        spel.voegKaartToe(2, speler.getDeck(), speler.getHand());
        //counter-card
    }

    public void kanselier(Spel spel,Speler speler){
        //+2 geld
        speler.addGeld(2);
        //gooi deck in aflegstapel
        spel.voegKaartToe(deck.size(), speler.getDeck(), speler.getAflegstapel());

    }
    public void dorp(Spel spel,Speler speler){

        //+1 kaart
        spel.voegKaartToe(1, speler.getDeck(), speler.getHand());
        //+2 acties
        speler.addActie(2);

    }
    public void houthakker(Speler speler){
        //+1 koop
        speler.addKoop(1);
        //+2 geld
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
        //deze kaart naar thrash
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
    public void geldverlener(Speler speler){
        //thrash koper
        //krijg +3 geldkaart
    }
    public void ombouwen(Spel spel){
        //select kaart -> thrash
        //krijg een kaart die tot 2 meer geld kost
    }
    public void smederij(Spel spel, Speler speler){
        spel.voegKaartToe(3,speler.getDeck(),speler.getHand());
    }

    public void spion(Spel spel, Speler speler){
        spel.voegKaartToe(1,speler.getDeck(),speler.getHand());
        //elke speler bekijkt de bovenste kaart van de deck en de speler kan beslissen of het naar de aflegstapel gaat
    }
    public void dief(Spel spel){
        //bekijk de 2 bovenste kaarten van elk persoon zijn deck en beslis of je de kaart wilt plaatsen op de aflegstapel
    }
    public void troonzaal(Spel spel, Speler speler){
        //kies een actiekaart
        //effect gekozen actiekaart*2
        String actiekaart = "";
        speelactiekaart(actiekaart, speler, spel);
        speelactiekaart(actiekaart, speler, spel);
    }
    public void raadzaal(Spel spel,Speler speler){
        spel.voegKaartToe(4,speler.getDeck(),speler.getHand());
        //+1 koop
        speler.addKoop(1);
    }

    public void festival(Spel spel,Speler speler){
        //+2 acties +1 kaart +2geld
        speler.addActie(2);
        spel.voegKaartToe(1,speler.getDeck(),speler.getHand());
        speler.addGeld(2);
    }
    public void laboratorium(Spel spel,Speler speler){
        spel.voegKaartToe(2, speler.getDeck(), speler.getHand());
        //+1actie
        speler.addActie(1);
    }
    public void bibliotheek(Spel spel, Speler speler){
        while (speler.getHand().size()<7){
            spel.voegKaartToe(1,speler.getDeck(),speler.getHand());
        }
    }
    public void markt(Spel spel,Speler speler){
        spel.voegKaartToe(1,speler.getDeck(),speler.getHand());
        //+1geld +1 koop +1actie
        speler.addGeld(1);
        speler.addKoop(1);
        speler.addActie(1);
    }
    public void mijn(Spel spel){
        //thrash een geldkaart en geef de geldkaart met 1 waarde meer
    }
    public void avonturier(Spel spel){
        //blijf kaarten trekken tot je 2 geldkaarten krijgt
    }
}
