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
            case "Heks":
                heks(spel, speler);
                break;
            case "Kelder":
                kelder(spel, speler);
                break;
            case "Kerk":
                kerk(speler);
                break;
            case "Gracht":
                gracht(spel, speler);
                break;
            case "Kanselier":
                kanselier(spel, speler);
                break;
            case "Dorps":
                dorps(spel, speler);
                break;
            case "Houthakker":
                houthakker(speler);
                break;
            case "Werkplaats":
                werkplaats(spel);
                break;
            case "Bureaucraat":
                bureaucraat(spel, speler);
                break;
            case "Feest":
                feest(spel, speler);
                break;
            case "Schutterij":
                schutterij(spel, speler);
                break;
            case "Geldverlener":
                geldverlener(speler);
                break;
            case "Ombouwen":
                ombouwen(spel);
                break;
            case "Smederij":
                smederij(spel, speler);
                break;
            case "Spion":
                spion(spel, speler);
                break;
            case "Dief":
                dief(spel);
                break;
            case "Troonzaal":
                troonzaal(spel, speler);
                break;
            case "Raadzaal":
                raadzaal(spel, speler);
                break;
            case "Festival":
                festival(spel, speler);
                break;
            case "Laboratorium":
                laboratorium(spel, speler);
                break;
            case "Bibliotheek":
                bibliotheek(spel, speler);
                break;
            case "Markt":
                markt(spel, speler);
                break;
            case "Mijn":
                mijn(spel);
                break;
            case "Avonturier":
                avonturier(spel);
                break;
        }
    }

    public void heks(Spel spel, Speler speler){
        //geef de andere spelers 2 vloekkaarten
        Kaart vloek = new Kaart();
        for(Kaart k : spel.getOverwinningsveld()){
            if(Objects.equals(k.getNaam(), "Vloek")){
                vloek = k;
            }
        }
        for(Speler s : spel.getSpelers()){
            if(!Objects.equals(s.getNaam(), speler.getNaam())){
                spel.voegKaartToe(2, vloek, speler.getAflegstapel());
            }
        }
    }

    public void kelder(Spel spel, Speler speler){
        //+1 actie
        speler.addActie(1);
        //selecteer de kaarten die je wilt afleggen
        Scanner keyboard = new Scanner(System.in);
        String input = "";
        int aantalkaarten = 0;

        System.out.println("Kies de kaarten die je wilt afleggen, typ 'OK' om door te gaan: \n");
        while(!Objects.equals(input, "OK")){
            int i = 0;
            for(Kaart k: speler.getHand()){
                System.out.println(k.getNaam() + " | " + i);
                input = keyboard.nextLine();
                Kaart afteleggenkaart = speler.getHand().get(Integer.parseInt(input));
                speler.verwijderKaart(afteleggenkaart, i);
                i++;
                aantalkaarten++;
            }
        }
        //trek x nieuwe kaarten
        spel.voegKaartToe(aantalkaarten, speler.getDeck(), speler.getHand());
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
    public void dorps(Spel spel,Speler speler){
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
