package com.company;

import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Main {
    int teller = 0;

    public static void main(String[] args) {
        try
        {
            Main obj = new Main ();
            obj.run(args);
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    public void run (String[] args) throws Exception
    {
        startGame();
    }

    public void startGame() throws SQLException {
        //Initializeer toetsenbord
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Welkom bij Dominion\n");
            System.out.println("Druk op ENTER om te starten");
            String input = keyboard.nextLine();

            //Maak spel
            if(input != null) {
                Spel spel = new Spel();
                System.out.println("Voer de naam van speler 1 in");
                input = keyboard.nextLine();
                Speler speler1 = new Speler(input);
                spel.addSpeler(speler1);
                System.out.println("Voer de naam van speler 2 in");
                input = keyboard.nextLine();
                Speler speler2 = new Speler(input);
                spel.addSpeler(speler2);
                spel.maakKaarten();
                spel.vulVeldOp();

                //Geef startkaarten
                spel.geefStartKaarten(speler1);
                spel.geefStartKaarten(speler2);

                //Start beurt
                while(!spel.spelGedaan()){
                    if(teller%2 == 0){
                        beurt(speler1, spel);
                    } else if (teller%2 == 1){
                        beurt(speler2, spel);
                    }
                    teller++;
                }
                System.out.println("Game over");
                System.out.println("De winnaar is " + spel.winnaar());
            }
            exit = true;
        }
        keyboard.close();
    }

    public void beurt(Speler speler, Spel spel){
        System.out.println("Het is " + speler.getNaam() + " zijn beurt.");
        Actiekaart acties = new Actiekaart();
        spel.setSpelerValues(speler);
        speler.checkHand();
        boolean beurt = true;
        Scanner keyboard = new Scanner(System.in);
        while(beurt){
            System.out.println("Acties: " + speler.getActie());
            System.out.println("Koop: " + speler.getKoop());
            System.out.println("Geld: " + speler.getGeld() + "\n");
            System.out.println("Kaarten:\n");
            for(Kaart k: speler.getHand()){
                System.out.println(k.getNaam());
            }
            System.out.println("\n");
            System.out.println("Kies een actie:");
            System.out.println("Geldkaarten neerleggen | 0");
            System.out.println("Actiekaart spelen | 1");
            System.out.println("Kaart kopen | 2");
            System.out.println("Beurt beeindigen | 3");
            String input = keyboard.nextLine();
            switch (input){
                case "0":
                    speler.plaatsGeldkaartenOpVeld();
                    break;
                case "1":
                    if(speler.getActie() > 0){
                        int i = 0;
                        int j = 0;
                        List<Kaart> actiekaarten = new ArrayList();
                        System.out.println("Kies een actiekaart: \n");
                        for(Kaart k: speler.getHand()){
                            if(Objects.equals(k.getType(), "Actie") || Objects.equals(k.getType(), "Actie-Reactie") || Objects.equals(k.getType(), "Actie-Aanval")){
                                System.out.println(k.getNaam() + " | " + i);
                                actiekaarten.add(k);
                            }
                            j++;
                        }
                        input = keyboard.nextLine();
                        Kaart tespelenkaart = actiekaarten.get(Integer.parseInt(input));
                        spel.voegKaartToe(1, tespelenkaart, speler.getHand(), speler.getAflegstapel());
                        List<String> kaarten = new ArrayList<>();
                        acties.speelactiekaart(tespelenkaart.getNaam(), speler, spel, 1, kaarten);
                        speler.addActie(-1);
                    } else {
                        System.out.println("U heeft onvoldoende actiebeurten.");
                    }
                    break;
                case "2":
                    System.out.println(speler.getGeld());
                    if(speler.getKoop() > 0){
                        int i = 0;
                        List<Kaart> koopopties = new ArrayList<>();
                        for(Kaart k : spel.getAlleKaarten()){
                            if (k.getKost() <= speler.getGeld() && !koopopties.contains(k)){
                                koopopties.add(k);
                                System.out.println(k.getNaam() + " - Kost: " + k.getKost() + "| Aantal nog beschikbaar: " + (spel.getStapelskaarten().get(k.getNr())-1) + " | " + i);
                                i++;
                            }
                        }
                        input = keyboard.nextLine();
                        Kaart tekopenkaart = koopopties.get(Integer.parseInt(input));
                        spel.koopKaart(tekopenkaart, speler.getAflegstapel());
                        speler.addGeld(-tekopenkaart.getKost());
                        System.out.println("Geld: " + speler.getGeld());
                        speler.addKoop(-1);
                    } else {
                        System.out.println("U heeft onvoldoende koopbeurten.");
                    }
                    break;
                case "3":
                    speler.beëindigbeurt();
                    beurt = false;
                    break;
            }
        }
    }
}