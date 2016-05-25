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
                smederij(speler);
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

    //ok
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
    }

    public void kerk(Spel spel, Speler speler, List<String> kaarten) {
        //plaats tot 4 kaarten in de vuilbak
        overloopKaartLijst(spel, speler, kaarten, 4, speler.getVuilbak());
    }

    public void gracht(Speler speler) {
        //trek 2 kaarten
        checkDeck(speler, 2);
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
    }

    //ok
    public void kanselier(Speler speler, int truefalse) {

        speler.addGeld(2);//+2 geld
        //je mag je deck in de aflegstapel gooien
        if (truefalse == 1) {
            speler.voegKaartToe(speler.getDeck().size(), speler.getDeck(), speler.getAflegstapel());
        }
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
    }

    public List<String> schutterij(Spel spel, Speler speler) {
        //+2Geld
        speler.addGeld(2);
        //leg kaarten af tot alle spelers 3 kaarten over heeft
        List<String> legeArray = new ArrayList<>();
        boolean selected = false;

        List<String> kaarten = new ArrayList<>();
        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {
                if (s.getHand().size()>3){
                    System.out.println("speler size: " + s.getHand().size());
                    for (Kaart k2 : s.getHand()) {
                        kaarten.add(k2.getNaam());
                    }
                }
                System.out.println(kaarten);
                overloopKaartLijst(spel, s, kaarten , s.getHand().size()-3, s.getAflegstapel());
            }
        }
        System.out.println("Kaarten van de enemy: " + kaarten);
        return kaarten;
    }

    public void geldverlener(Spel spel, Speler speler, List<String> kaarten) {
        //thrash koper
        //krijg +3 geld
        if (Objects.equals(kaarten.get(0), "Koper")) {
            overloopKaartLijst(spel, speler, kaarten, 1, speler.getVuilbak());
            speler.addGeld(3);
        }
    }

    public String ombouwen(Spel spel, Speler speler, List<String> kaarten) {
        //select kaart -> thrash
        overloopKaartLijst(spel, speler, kaarten, 1, speler.getVuilbak());

        //krijg een kaart die tot 2 meer geld kost
        int trashkaartwaarde = 0;
        for (Kaart k : spel.getAlleKaarten()) {
            if (Objects.equals(k.getNaam(), kaarten.get(0))) {
                trashkaartwaarde = k.getKost();
            }
        }
        trashkaartwaarde = trashkaartwaarde + 2;
        return Integer.toString(trashkaartwaarde);
    }

    //ok
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
                    checkDeck(s, 2);
                    Kaart k = s.getDeck().get(i);
                    System.out.println(k.getNaam());
                    if (Objects.equals(k.getType(), "Geld") && !kaarten.contains(k.getNaam()) && kaarten.size() == 0) {
                        testelenkaarten.add(k.getNaam());
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
        boolean done = false;
        //plaats de gekozen actiekaart in de aflegstapel
        for(int i=0;i<speler.getHand().size();i++){
            Kaart k = speler.getHand().get(i);
            if(Objects.equals(k.getNaam(), kaarten.get(0)) && !done){
                spel.voegKaartToe(1, k, speler.getHand(), speler.getAflegstapel());
                done = true;
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
        //+2 acties +1 buy +2geld
        speler.addActie(2);
        speler.addKoop(1);
        speler.addGeld(2);
    }

    public void laboratorium(Speler speler) {
        //+2 kaarten
        checkDeck(speler, 2);
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
        //+1actie
        speler.addActie(1);
    }

    public List<String> bibliotheek(Speler speler, List<String> kaarten, int eersteinstantie) {
        List<String> kaart = new ArrayList<>();
        boolean done = false;

        if (kaarten.size() > 0) {
            checkDeck(speler, 1);
            speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        }

        if (eersteinstantie != 0) {
            done = true;
        }

        while (speler.getHand().size() <= 7) {
            checkDeck(speler, 1);
            Kaart k = speler.getDeck().get(0);
            if (k.getType().contains("Actie") && done) {
                kaart.add(k.getNaam());
                return kaart;
            } else if (eersteinstantie == 0 && !done) {
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