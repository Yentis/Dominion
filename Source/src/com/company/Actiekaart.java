package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Renzie on 14/04/2016.
 */
public class Actiekaart {

    public String speelactiekaart(String naam, Speler speler, Spel spel, int truefalse, List<String> kaarten) {
        switch (naam) {
            case "Heks":
                heks(spel, speler);
                break;
            case "Kelder":
                kelder(spel, speler, kaarten);
                break;
            case "Kerk":
                kerk(spel, speler, kaarten);
                break;
            case "Gracht":
                gracht(speler);
                break;
            case "Kanselier":
                kanselier(speler, truefalse);
                break;
            case "Dorps":
                dorps(speler);
                break;
            case "Houthakker":
                houthakker(speler);
                break;
            case "Werkplaats":
                return werkplaats();
            case "Feest":
                return feest(spel, speler);
            case "Geldverlener":
                geldverlener(spel, speler, kaarten);
                break;
            case "Ombouwen":
                return ombouwen(spel, speler, kaarten);
            case "Smederij":
                System.out.println("Smederij pre");
                smederij(speler);
                System.out.println("Smederij after");
                break;
            case "Raadzaal":
                raadzaal(spel, speler);
                break;
            case "Festival":
                festival(speler);
                break;
            case "Laboratorium":
                laboratorium(speler);
                break;
            case "Markt":
                markt(speler);
                break;
            case "Mijn":
                mijn(spel, speler, kaarten);
                break;
            case "Avonturier":
                avonturier(speler);
                break;
        }
        return "0";
    }

    public List<String> speelactiekaartspecial(String naam, Spel spel, Speler speler, List<String> kaarten, int janee) {
        List<String> emptylist = new ArrayList<>();
        switch (naam) {
            case "Dief":
                return dief(spel, speler, kaarten);
            case "Bibliotheek":
                return bibliotheek(speler, kaarten, janee);

            case "Schutterij":
                return schutterij(spel, speler);
            case "Bureaucraat":
                return bureaucraat(spel, speler);
            case "Spion":
                return spion(spel, speler, janee, kaarten);
            case "Troonzaal":
                return troonzaal(spel, speler, kaarten);
        }
        return emptylist;
    }

    public void checkDeck(Speler speler, int aantal) {
        if (speler.getDeck().size() < aantal) {
            speler.leegAflegstapel();
        }
    }

    public int overloopKaartLijst(Spel spel, Speler speler, List<String> kaarten, int maxwaarde, List<Kaart> bestemming) {
        boolean selected = false;
        int aantalkaarten = 0;

        for (String s : kaarten) {
            for (Kaart k : spel.getAlleKaarten()) {
                if (Objects.equals(k.getNaam(), s) && !selected && aantalkaarten < maxwaarde) {
                    spel.voegKaartToe(1, k, speler.getHand(), bestemming);
                    aantalkaarten++;
                    selected = true;
                }
            }
            selected = false;
        }

        return aantalkaarten;
    }

    public Kaart kiesKaart(Speler speler, String input) {
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        for (Kaart k : speler.getHand()) {
            System.out.println(k.getNaam() + " | " + i);
            i++;
        }
        input = keyboard.nextLine();
        Kaart gekozenkaart = speler.getHand().get(Integer.parseInt(input));
        return gekozenkaart;
    }

    public boolean heeftReactiekaart(Speler s) {
        for (Kaart k : s.getHand()) {
            if (Objects.equals(k.getType(), "Actie-Reactie")) {
                return true;
            }
        }
        return false;
    }

    public Kaart duidSpecifiekeKaartAan(String naam, Spel spel) {
        for (Kaart k : spel.getAlleKaarten()) {
            if (Objects.equals(k.getNaam(), naam)) {
                return k;
            }
        }
        return null;
    }

    public Kaart kiesKaartMetSoort(String waarde, String soort, List<Kaart> bron) {
        String input;
        Scanner keyboard = new Scanner(System.in);
        List<Kaart> koopopties = new ArrayList<>();
        int i = 0;

        for (Kaart k : bron) {
            if (!koopopties.contains(k) && checkKaart(soort, waarde, k)) {
                System.out.println(k.getNaam() + " | " + i);
                koopopties.add(k);
                i++;
            }
        }

        input = keyboard.nextLine();
        return koopopties.get(Integer.parseInt(input));
    }

    public boolean checkKaart(String soort, String waarde, Kaart k) {
        if (Objects.equals(soort, "type")) {
            if (Objects.equals(waarde, "Actie")) {
                return Objects.equals(k.getType(), waarde) || Objects.equals(k.getType(), "Actie-Reactie") || Objects.equals(k.getType(), "Actie-Aanval");
            } else {
                return Objects.equals(k.getType(), waarde);
            }
        } else if (Objects.equals(soort, "kost")) {
            return k.getKost() <= Integer.parseInt(waarde);
        }
        return false;
    }

    public void kaartAfleggen(Speler speler, int aantal) {
        String input = "";
        Scanner keyboard = new Scanner(System.in);

        boolean besloten = false;
        while (!besloten) {
            input = keyboard.nextLine();
            if (Objects.equals(input, "J") || Objects.equals(input, "j")) {
                speler.voegKaartToe(aantal, speler.getDeck(), speler.getAflegstapel());
                besloten = true;
            } else if (!Objects.equals(input, "N") || !Objects.equals(input, "n")) {
                System.out.println("Ongeldige invoer, probeer opnieuw.");
            } else {
                besloten = true;
            }
        }
    }

    public void heks(Spel spel, Speler speler) {
        checkDeck(speler, 2);
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand()); //+2 kaarten
        for (Speler s : spel.getSpelers()) { //geef de andere spelers een vloekkaart
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {
                spel.koopKaart(duidSpecifiekeKaartAan("Vloek", spel), s.getAflegstapel());
            }
        }
    }

    public void kelder(Spel spel, Speler speler, List<String> kaarten) {
        //+1 actie
        speler.addActie(1);
        //selecteer de kaarten die je wilt afleggen
        //trek x nieuwe kaarten
        int aantalkaarten = overloopKaartLijst(spel, speler, kaarten, 7, speler.getAflegstapel());
        checkDeck(speler, aantalkaarten);
        speler.voegKaartToe(aantalkaarten, speler.getDeck(), speler.getHand());

        /*String input = "";

        System.out.println("Kies de kaarten die je wilt afleggen, typ 'OK' om door te gaan: \n");
        while (!Objects.equals(input, "OK")) {
            spel.voegKaartToe(1, kiesKaart(speler, input), speler.getHand(), speler.getAflegstapel());
            aantalkaarten++;
        }*/
    }

    public void kerk(Spel spel, Speler speler, List<String> kaarten) {
        //plaats tot 4 kaarten in de vuilbak
        overloopKaartLijst(spel, speler, kaarten, 4, speler.getVuilbak());

        /*
        String input = "";

        System.out.println("Kies max 4 kaarten die je wilt wegsmijten, typ 'OK' om door te gaan: ");
        while (!Objects.equals(input, "OK") || aantalkaarten<4) {
            spel.voegKaartToe(1, kiesKaart(speler, input), speler.getHand(), speler.getVuilbak());
            aantalkaarten++;
        }*/
    }

    public void gracht(Speler speler) {
        //trek 2 kaarten
        checkDeck(speler, 2);
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
    }

    public void kanselier(Speler speler, int truefalse) {

        speler.addGeld(2);//+2 geld
        //je mag je deck in de aflegstapel gooien
        if (truefalse == 1) {
            speler.voegKaartToe(speler.getDeck().size(), speler.getDeck(), speler.getAflegstapel());
        }/*
        System.out.println("Wil je je deck in de aflegstapel gooien? J/N");
        kaartAfleggen(speler, speler.getDeck().size());*/
    }

    public void dorps(Speler speler) {
        checkDeck(speler, 1);
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        speler.addActie(2);
    }

    public void houthakker(Speler speler) {
        speler.addKoop(1);
        speler.addGeld(2);
    }

    public String werkplaats() {
        //neem een kaart met <=4 kost
        return "4";
        /*
        System.out.println("Kies een kaart met een kost van maximum 4: \n");
        spel.koopKaart(kiesKaartMetSoort("4", "kost", spel.getAlleKaarten()), speler.getAflegstapel());*/
    }

    public List<String> bureaucraat(Spel spel, Speler speler) {
        //krijg een zilver kaart
        boolean done = false;

        Kaart kaart = new Kaart();
        for(Kaart k : spel.getGeldveld()){
            if(Objects.equals(k.getNaam(), "Zilver") && !done){
                kaart = k;
                done = true;
            }
        }
        spel.koopKaart(kaart, speler.getDeck());

        //elke andere speler toont een overwinningskaart en plaatst het op zijn deck (of toont een hand zonder overwinningskaarten)
        List<String> kaarten = new ArrayList();
        int i = 0;

        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {

                List<Kaart> handspeler = new ArrayList<>();

                for(Kaart k : s.getHand()){
                    handspeler.add(k);
                }

                for (Kaart k : handspeler) {
                    if (Objects.equals(k.getType(), "Overwinning")) {
                        kaarten.add(k.getNaam());
                        spel.voegKaartToe(1, k, s.getHand(), s.getDeck());
                        i++;
                    }
                }
                if (i == 0) {
                    for (Kaart k2 : s.getHand()) {
                        kaarten.add(k2.getNaam());
                    }
                }
            }
        }
        return kaarten;
    }

    public String feest(Spel spel, Speler speler) {
        //deze kaart naar trash
        spel.voegKaartToe(1, duidSpecifiekeKaartAan("Feest", spel), speler.getHand(), speler.getVuilbak());

        //neem kaart die max 5 geld kost
        return "5";

        /*
        System.out.println("Kies een kaart met een kost van maximum 5: \n");
        kiesKaartMetSoort("5", "kost", spel.getAlleKaarten());*/
    }


    public List<String> schutterij(Spel spel, Speler speler) {

        //+2Geld
        speler.addGeld(2);
        //leg kaarten af tot alle spelers 3 kaarten over heeft
        List<String> kaarten = new ArrayList<>();
        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {

                List<Kaart> handspeler = new ArrayList<>();
                System.out.println("speler size: " + s.getHand().size());
                for (Kaart k : s.getHand()) {
                    kaarten.add(k.getNaam());
                }
            }
        }
        System.out.println(kaarten);
        return kaarten;




                /*while (s.getHand().size() > 3) {


                    String input = "";

                    System.out.println("Kies de kaarten die je wilt afleggen: \n");
                    spel.voegKaartToe(1, kiesKaart(speler, input), s.getHand(), s.getAflegstapel());
                }*/


    }


    public void geldverlener(Spel spel, Speler speler, List<String> kaarten) {
        //thrash koper
        //krijg +3 geld
        if (Objects.equals(kaarten.get(0), "Koper")) {
            overloopKaartLijst(spel, speler, kaarten, 1, speler.getVuilbak());
            speler.addGeld(3);
        }

        /*
        String input = "";
        System.out.println("Kies de koperkaart die je wilt verwijderen: \n");
        Kaart teVerwijderenKaart = kiesKaart(speler, input);

        if (Objects.equals(teVerwijderenKaart.getNaam(), "Koper")) {
            spel.voegKaartToe(1, teVerwijderenKaart, speler.getHand(), speler.getVuilbak());
            speler.addGeld(3);
        } else {
            System.out.println("Dit is geen koperkaart, probeer opnieuw.");
            geldverlener(spel, speler);
        }*/
    }

    public String ombouwen(Spel spel, Speler speler, List<String> kaarten) {
        //select kaart -> thrash
        overloopKaartLijst(spel, speler, kaarten, 1, speler.getVuilbak());

        /*
        String input = "";
        System.out.println("Kies een kaart om weg te smijten: ");
        Kaart gekozenkaart = kiesKaart(speler, input);
        int trashkaartwaarde = gekozenkaart.getWaarde();
        spel.voegKaartToe(1, gekozenkaart, speler.getHand(), speler.getVuilbak());*/

        //krijg een kaart die tot 2 meer geld kost
        int trashkaartwaarde = 0;
        for (Kaart k : spel.getAlleKaarten()) {
            if (Objects.equals(k.getNaam(), kaarten.get(0))) {
                trashkaartwaarde = k.getKost();
            }
        }
        trashkaartwaarde = trashkaartwaarde + 2;
        return Integer.toString(trashkaartwaarde);

        //System.out.println("Kies de kaart die je wilt nemen: \n");
        //kiesKaartMetSoort(trashkaartwaarde + "2", "kost", spel.getAlleKaarten());
    }

    public void smederij(Speler speler) {
        checkDeck(speler, 3);
        speler.voegKaartToe(3, speler.getDeck(), speler.getHand());
    }

    public List<String> spion(Spel spel, Speler speler, int janee, List<String> kaarten) {
        List<String> kaart = new ArrayList<>();

        if(janee != 0){
            //+1 kaart
            checkDeck(speler, 1);
            speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
            //+1 actie
            speler.addActie(1);
            //elke speler bekijkt de bovenste kaart van zijn deck en de speler kan beslissen of het naar de aflegstapel gaat

            for (Speler s : spel.getSpelers()) {
                if (!heeftReactiekaart(s) || Objects.equals(s.getNaam(), speler.getNaam())) {
                    checkDeck(s, 1);
                    Kaart k = s.getDeck().get(0);
                    kaart.add(s.getNaam());
                    kaart.add(k.getNaam());
                }
            }
        } else {
            Speler vijand = new Speler("placeholder");
            for(Speler s : spel.getSpelers()){
                if(!Objects.equals(s.getNaam(), speler.getNaam())){
                    vijand = s;
                }
            }
            Speler huidig = new Speler("placeholder");
            for(String s : kaarten){
                if(Objects.equals(s, "jezelf")){
                    huidig = speler;
                } else if(Objects.equals(s, "de vijand")){
                    huidig = vijand;
                } else {
                    huidig.voegKaartToe(1, huidig.getDeck(), huidig.getAflegstapel());
                }
            }

        }
        /*
        System.out.println(speler.getNaam() + ", wil je " + k.getNaam() + " van " + s.getNaam() + " wegleggen? J/N");
        kaartAfleggen(speler, 1);*/
        return kaart;
    }

    public List<String> dief(Spel spel, Speler speler, List<String> kaarten) {

        /*Each other player reveals the top 2 cards of his deck.
        If they revealed any Treasure cards, they trash one of them that you choose.
        You may gain any or all of these trashed cards.
        They discard the other revealed cards.*/
        List<String> testelenkaarten = new ArrayList<>();

        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {
                for (int i = 0; i < 2; i++) {
                    Kaart k = s.getDeck().get(i);
                    System.out.println(k.getNaam());
                    if (Objects.equals(k.getType(), "Geld") && !kaarten.contains(k.getNaam()) && kaarten.size() == 0) {
                        testelenkaarten.add(k.getNaam());
                        /*
                        System.out.println("Wil je deze kaart stelen en op je aflegstapel plaatsen? J/N\n");
                        kaartAfleggen(speler, 1);*/
                    } else if (Objects.equals(k.getType(), "Geld") && kaarten.contains(k.getNaam())) {
                        spel.voegKaartToe(1, k, s.getDeck(), speler.getAflegstapel());
                    }
                }
            }
        }
        return testelenkaarten;
    }

    //todo
    public List<String> troonzaal(Spel spel, Speler speler, List<String> kaarten) {
        //kies een actiekaart
        System.out.println("kaarten size: " + kaarten.size());
        List<String> message = new ArrayList<>();
        if(kaarten.size() == 0){
            message.add("Kiesiets");
            return message;
        } else if(kaarten.size() == 1) {
            System.out.println("kaarten size is 1: " + kaarten.get(0));
            //effect gekozen actiekaart*2
            switch(kaarten.get(0)){
                case "Dief":
                case "Bibliotheek":
                case "Schutterij":
                case "Bureaucraat":
                case "Spion":
                case "Troonzaal":
                    return speelactiekaartspecial(kaarten.get(0), spel, speler, message, 2);
                default:
                    System.out.println("first run");
                    speelactiekaart(kaarten.get(0), speler, spel, 2, message);
                    System.out.println("second run");
                    speelactiekaart(kaarten.get(0), speler, spel, 2, message);
                    break;
            }
        }
        return message;
    }

    public void raadzaal(Spel spel, Speler speler) {
        //+4 kaarten
        checkDeck(speler, 4);
        speler.voegKaartToe(4, speler.getDeck(), speler.getHand());
        //+1 koop
        speler.addKoop(1);
        //andere spelers trekken 1 kaart
        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam())) {
                s.voegKaartToe(1, s.getDeck(), s.getHand());
            }
        }
    }

    public void festival(Speler speler) {
        //+2 acties +1 kaart +2geld
        speler.addActie(2);
        checkDeck(speler, 1);
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        speler.addGeld(2);
    }

    public void laboratorium(Speler speler) {
        //+2 kaarten
        checkDeck(speler, 2);
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
        //+1actie
        speler.addActie(1);
    }

    public List<String> bibliotheek(Speler speler, List<String> kaarten, int janee) {
        List<String> kaart = new ArrayList<>();
        boolean done = false;

        if (kaarten.size() > 0) {
            checkDeck(speler, 1);
            speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        }

        if (janee != 0) {
            done = true;
        }

        while (speler.getHand().size() <= 7) {
            checkDeck(speler, 1);
            Kaart k = speler.getDeck().get(0);
            if (k.getType().contains("Actie") && done) {
                kaart.add(k.getNaam());
                return kaart;
            } else if (janee == 0 && !done) {
                speler.voegKaartToe(1, speler.getDeck(), speler.getAflegstapel());
                done = true;
            } else {
                speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
            }
        }
        return kaart;
    }

    public void markt(Speler speler) {
        //+1 kaart
        checkDeck(speler, 1);
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        //+1geld +1 koop +1actie
        speler.addGeld(1);
        speler.addKoop(1);
        speler.addActie(1);
    }

    public void mijn(Spel spel, Speler speler, List<String> kaarten) {
        //thrash een geldkaart en geef de geldkaart met 1 waarde meer
        int kost = 0;
        boolean done = false;
        for (Kaart k : spel.getGeldveld()) {
            if (Objects.equals(kaarten.get(0), k.getNaam()) && Objects.equals(k.getType(), "Geld") && !done) {
                overloopKaartLijst(spel, speler, kaarten, 1, speler.getVuilbak());
                kost = k.getKost();
                done = true;
            }
        }
        done = false;
        switch (kost) {
            case 0:
                for (int i = 0; i < spel.getGeldveld().size(); i++) {
                    if (Objects.equals(spel.getGeldveld().get(i).getNaam(), "Zilver") && !done) {
                        spel.koopKaart(spel.getGeldveld().get(i), speler.getHand());
                        done = true;
                        i++;
                    }
                }
                System.out.println("second check");
                break;
            case 3:
            case 6:
                for (int i = 0; i < spel.getGeldveld().size(); i++) {
                    if (Objects.equals(spel.getGeldveld().get(i).getNaam(), "Goud") && !done) {
                        spel.koopKaart(spel.getGeldveld().get(i), speler.getHand());
                        done = true;
                        i++;
                    }
                }
                break;
            default:
                break;
        }

        /*
        String input = "";
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Kies de kaart die je wilt verwijderen: \n");
        Kaart gekozenkaart = kiesKaartMetSoort("Geld", "type", speler.getHand());
        int kaartkost = gekozenkaart.getKost();
        spel.voegKaartToe(1, gekozenkaart, speler.getHand(), speler.getVuilbak());

        System.out.println("Kies een kaart om te kopen: \n");
        Kaart tekopenkaart = kiesKaartMetSoort(kaartkost + "3", "kost", speler.getHand());

        while(!Objects.equals(tekopenkaart.getType(), "Geld")){
            System.out.println("Dit is geen geldkaart");
            tekopenkaart = kiesKaartMetSoort(kaartkost + "3", "kost", speler.getHand());
        }

        spel.koopKaart(tekopenkaart, speler.getHand());*/
    }

    public void avonturier(Speler speler) {
        //blijf kaarten trekken tot je 2 geldkaarten krijgt
        int maxGeldKaarten = 2;
        int i = 0;
        while (i < maxGeldKaarten) {
            checkDeck(speler, 1);
            Kaart bovenliggendeKaart = speler.getDeck().get(0);
            if (!Objects.equals(bovenliggendeKaart.getType(), "Geld")) {
                speler.voegKaartToe(1, speler.getDeck(), speler.getAflegstapel());
            } else {
                speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
                i++;
            }
        }
    }
}