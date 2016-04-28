package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Renzie on 14/04/2016.
 */
public class Actiekaart {

    public void speelactiekaart(String naam, Speler speler, Spel spel) {
        switch (naam) {
            case "Heks":
                heks(spel, speler);
                break;
            case "Kelder":
                kelder(speler);
                break;
            case "Kerk":
                kerk(spel, speler);
                break;
            case "Gracht":
                gracht(speler);
                break;
            case "Kanselier":
                kanselier(speler);
                break;
            case "Dorps":
                dorps(speler);
                break;
            case "Houthakker":
                houthakker(speler);
                break;
            case "Werkplaats":
                werkplaats(spel,speler);
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
                ombouwen(spel,speler);
                break;
            case "Smederij":
                smederij(speler);
                break;
            case "Spion":
                spion(speler);
                break;
            case "Dief":
                dief(spel);
                break;
            case "Troonzaal":
                troonzaal(spel, speler);
                break;
            case "Raadzaal":
                raadzaal(speler);
                break;
            case "Festival":
                festival(speler);
                break;
            case "Laboratorium":
                laboratorium(speler);
                break;
            case "Bibliotheek":
                bibliotheek(speler);
                break;
            case "Markt":
                markt(speler);
                break;
            case "Mijn":
                mijn(spel, speler);
                break;
            case "Avonturier":
                avonturier(speler);
                break;
        }
    }

    public void heks(Spel spel, Speler speler) {
        //+2 kaarten
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());

        //geef de andere spelers een vloekkaart
        Kaart vloek = new Kaart();
        for (Kaart k : spel.getOverwinningsveld()) {
            if (Objects.equals(k.getNaam(), "Vloek")) {
                vloek = k;
            }
        }
        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam())) {
                spel.voegKaartToe(1, vloek, speler.getAflegstapel());
            }
        }
    }
    
    public void toonKaarten(Speler speler){
        String input;
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        for (Kaart k : speler.getHand()) {
            System.out.println(k.getNaam() + " | " + i);
            i++;
        }
        input = keyboard.nextLine();
        Kaart afteleggenkaart = speler.getHand().get(Integer.parseInt(input));
    }

    public void kelder(Speler speler) {
        //+1 actie
        speler.addActie(1);
        //selecteer de kaarten die je wilt afleggen
        Scanner keyboard = new Scanner(System.in);
        String input = "";
        int aantalkaarten = 0;

        System.out.println("Kies de kaarten die je wilt afleggen, typ 'OK' om door te gaan: \n");
        while (!Objects.equals(input, "OK")) {
            int i = 0;
            for (Kaart k : speler.getHand()) {
                System.out.println(k.getNaam() + " | " + i);
                i++;
            }
            input = keyboard.nextLine();
            Kaart afteleggenkaart = speler.getHand().get(Integer.parseInt(input));
            speler.verwijderKaart(afteleggenkaart, Integer.parseInt(input));
            aantalkaarten++;
        }
        //trek x nieuwe kaarten
        speler.voegKaartToe(aantalkaarten, speler.getDeck(), speler.getHand());
    }

    public void kerk(Spel spel, Speler speler) {
        //plaats tot 4 kaarten in de vuilbak
        int aantalkaarten = 0;
        Scanner keyboard = new Scanner(System.in);
        String input = "";

        System.out.println("Kies max 4 kaarten die je wilt wegsmijten, typ 'OK' om door te gaan: ");
        while (!Objects.equals(input, "OK") || aantalkaarten<4) {
            int i = 0;
            for (Kaart k : speler.getHand()) {
                System.out.println(k.getNaam() + " | " + i);
                i++;
            }
            input = keyboard.nextLine();
            Kaart afteleggenkaart = speler.getHand().get(Integer.parseInt(input));
            spel.voegKaartToe(1, afteleggenkaart, speler.getVuilbak());
            aantalkaarten++;
        }
    }

    public void gracht(Speler speler) {      //not finished
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
        //counter-card
    }

    public void kanselier(Speler speler) {
        //+2 geld
        speler.addGeld(2);
        //je mag je deck in de aflegstapel gooien
        Scanner keyboard = new Scanner(System.in);
        String input = "";
        boolean escape = false;

        while(!escape){
            System.out.println("Wil je je deck in de aflegstapel gooien? J/N");
            input = keyboard.nextLine();
            if(Objects.equals(input, "J") || Objects.equals(input, "j")) {
                speler.voegKaartToe(speler.getDeck().size(), speler.getDeck(), speler.getAflegstapel());
                escape = true;
            } else if (!Objects.equals(input, "N") || !Objects.equals(input, "n")){
                System.out.println("Ongeldige invoer, probeer opnieuw.");
            } else {
                escape = true;
            }
        }
    }

    public void dorps(Speler speler) {
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        speler.addActie(2);
    }

    public void houthakker(Speler speler) {
        speler.addKoop(1);
        speler.addGeld(2);
    }

    public void werkplaats(Spel spel, Speler speler) {
        //neem een kaart met <=4 kost
        String input;
        Scanner keyboard = new Scanner(System.in);
        List<Kaart> koopopties = new ArrayList();

        System.out.println("Kies een kaart met een kost minder dan 4: \n");
        int i = 0;
        for(Kaart k: spel.getAlleKaarten()){
            if(k.getKost()<= 4 && !koopopties.contains(k)){
                System.out.println(k.getNaam() + " | " + i);
                koopopties.add(k);
                i++;
            }
        }
        input = keyboard.nextLine();
        Kaart teOntvangenKaart = koopopties.get(Integer.parseInt(input));
        spel.voegKaartToe(1, teOntvangenKaart, speler.getAflegstapel());
    }

    public void bureaucraat(Spel spel, Speler speler) {
        //+1 actiekaart, plaats deze op je deck
        int i = 0;
        String input = "";
        Scanner keyboard = new Scanner(System.in);
        List<Kaart> koopopties = new ArrayList();

        System.out.println("Kies een actiekaart: ");
        for(Kaart k : spel.getAlleKaarten()){
            if((Objects.equals(k.getType(), "Actie") || Objects.equals(k.getType(), "Actie-Reactie") || Objects.equals(k.getType(), "Actie-Aanval")) && !koopopties.contains(k)){
                System.out.println(k.getNaam() + " | " + i);
                koopopties.add(k);
                i++;
            }
        }
        input = keyboard.nextLine();
        Kaart teOntvangenKaart = koopopties.get(Integer.parseInt(input));
        spel.koopKaart(teOntvangenKaart, speler.getDeck());

        //elk ander speler toont een overwinningskaart en plaatst het op zijn deck (of toont een hand zonder overwinningskaarten)
        koopopties = new ArrayList();

        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam())) {
                System.out.println(s.getNaam() + "\n");
                i = 0;
                for (Kaart k : s.getHand()){
                    if (Objects.equals(k.getType(), "Overwinning")){
                        System.out.println(k.getNaam() + "|" + i);
                        i++;
                    }
                }
                input = keyboard.nextLine();
                Kaart teVerplaatsenKaart = koopopties.get(Integer.parseInt(input));
                spel.voegKaartToe(1, teVerplaatsenKaart, s.getDeck());
                if(i == 0){
                    for(Kaart k : s.getHand()){
                        System.out.println(k.getNaam());
                    }
                }
            }
        }
    }

    public void feest(Spel spel, Speler speler) {    //not finished
        //deze kaart naar thrash

        //neem kaart die max 5 geld kost

    }

    public void schutterij(Spel spel, Speler speler) {
        //+2Geld
        speler.addGeld(2);
        //leg kaarten af tot alle spelers 3 kaarten over heeft
        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam())) {
                while (s.getHand().size() > 3) {
                    int i = 0;
                    Scanner keyboard = new Scanner(System.in);
                    String input;

                    System.out.println("Kies de kaarten die je wilt verwijderen: \n");
                    for (Kaart k : speler.getHand()) {
                        System.out.println(k.getNaam() + " | " + i);
                        i++;
                    }

                    input = keyboard.nextLine();
                    Kaart teverwijderenkaart = s.getHand().get(Integer.parseInt(input));
                    s.verwijderKaart(teverwijderenkaart, Integer.parseInt(input));
                }
            }
        }
    }

    public void geldverlener(Speler speler) {
        //thrash koper
        //krijg +3 geld
        String input;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Kies de kaart die je wilt verwijderen: \n");

        int i = 0;
        for (Kaart k : speler.getHand()) {
            System.out.println(k.getNaam() + " | " + i);
            i++;
        }

        input = keyboard.nextLine();
        Kaart teVerwijderenKaart = speler.getHand().get(Integer.parseInt(input));
        if (Objects.equals(teVerwijderenKaart.getNaam(), "Koper")) {
            speler.addGeld(3);
        } else {
            System.out.println("Dit is geen Geldkaart die je gekozen hebt, probeer opnieuw noob.");
            geldverlener(speler);
        }
    }

    public void ombouwen(Spel spel, Speler speler) {  //not finished
        //select kaart -> thrash
        //krijg een kaart die tot 2 meer geld kost
        String input;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Kies de kaart die je wilt verwijderen: \n");
        int i = 0;
        for (Kaart k : speler.getHand()) {
            System.out.println(k.getNaam() + " | " + i);
            i++;
        }
        input = keyboard.nextLine();
        Kaart teVerwijderenKaart = speler.getHand().get(Integer.parseInt(input));
        int waardeVanTeVerwijderenKaart = teVerwijderenKaart.getWaarde();
        int bereikKost = 2;
        int j = 0;
        for (Kaart k: spel.getAlleKaarten()){
            if (k.getWaarde() <= waardeVanTeVerwijderenKaart + bereikKost){
                System.out.println(k.getNaam() + " | " + j);
                j++;
            }
        }
        System.out.println("Kies de kaart die je wilt nemen: \n");
       /* oe doeje die shit ier
        input = keyboard.nextLine();
        Kaart teOntvangenKaart =
        spel.voegKaartToe(1, teOntvangenKaart, speler.getAflegstapel());
        */
    }

    public void smederij(Speler speler) {
        speler.voegKaartToe(3, speler.getDeck(), speler.getHand());
    }

    public void spion(Speler speler) {  //not finished
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        //elke speler bekijkt de bovenste kaart van de deck en de speler kan beslissen of het naar de aflegstapel gaat


    }

    public void dief(Spel spel) {  // not finished
        //bekijk de 2 bovenste kaarten van elk persoon zijn deck en beslis of je de kaart wilt plaatsen op de aflegstapel


    }

    public void troonzaal(Spel spel, Speler speler) {
        //kies een actiekaart
        //effect gekozen actiekaart*2
        String actiekaart = "";
        speelactiekaart(actiekaart, speler, spel);
        speelactiekaart(actiekaart, speler, spel);
    }

    public void raadzaal(Speler speler) {
        speler.voegKaartToe(4, speler.getDeck(), speler.getHand());
        //+1 koop
        speler.addKoop(1);
    }

    public void festival(Speler speler) {
        //+2 acties +1 kaart +2geld
        speler.addActie(2);
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        speler.addGeld(2);
    }

    public void laboratorium(Speler speler) {
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
        //+1actie
        speler.addActie(1);
    }

    public void bibliotheek(Speler speler) {
        while (speler.getHand().size() < 7) {
            speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        }
    }

    public void markt(Speler speler) {
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        //+1geld +1 koop +1actie
        speler.addGeld(1);
        speler.addKoop(1);
        speler.addActie(1);
    }

    public void mijn(Spel spel, Speler speler) {    //idk if finished
        //thrash een geldkaart en geef de geldkaart met 1 waarde meer

        String input;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Kies de kaart die je wilt verwijderen: \n");
        for (Kaart k : speler.getHand()) {
            int i = 0;
            System.out.println(k.getNaam() + " | " + i);
            input = keyboard.nextLine();
            Kaart teVerwijderenKaart = speler.getHand().get(Integer.parseInt(input));
            i++;
            if (teVerwijderenKaart.getWaarde() > 0 && teVerwijderenKaart.getType() == "Geld") {
                Kaart teOntvangenKaart = new Kaart();
                int waardeVanTeOntvangenKaart = teVerwijderenKaart.getWaarde() + 1;
                for (Kaart kaart : spel.getGeldveld()) {
                    if (Objects.equals(kaart.getWaarde(), waardeVanTeOntvangenKaart)) {
                        teOntvangenKaart = k;
                    }
                }
                spel.voegKaartToe(1, teVerwijderenKaart, speler.getVuilbak());
                spel.voegKaartToe(1, teOntvangenKaart, speler.getAflegstapel());
            } else {
                System.out.println("Je hebt geen geldkaart gekozen, probeer opnieuw (git gud nub)");
                mijn(spel, speler);
            }

        }
    }

    public void avonturier(Speler speler) {  //not checked yet
        //blijf kaarten trekken tot je 2 geldkaarten krijgt
        int maxGeldKaarten = 2;
        int i = 0;
        while (i < maxGeldKaarten) {
            Kaart bovenliggendeKaart = new Kaart();
            bovenliggendeKaart = speler.getDeck().get(0);
            if (!Objects.equals(bovenliggendeKaart.getType(), "Geld")) {
                speler.voegKaartToe(1, speler.getDeck(), speler.getAflegstapel());
            } else {
                speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
                i++;
            }
        }
    }

}
