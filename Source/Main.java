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
                System.out.println("Voer je naam in");
                input = keyboard.nextLine();
                Speler speler1 = new Speler(input, 0);
                spel.addSpeler(speler1);
                System.out.println("Voer de naam van je vijand in");
                input = keyboard.nextLine();
                Speler speler2 = new Speler(input, 0);
                spel.addSpeler(speler2);
                spel.maakKaarten();
                spel.vulVeldOp();

                //Geef startkaarten
                spel.starterDeck(spel, speler1.getDeck());
                spel.starterDeck(spel, speler2.getDeck());
                spel.voegKaartToe(5, speler1.getDeck(), speler1.getHand());
                spel.voegKaartToe(5, speler2.getDeck(), speler2.getHand());

                //Start beurt
                while(!spelGedaan(spel)){
                    if(teller%2 == 0){
                        beurt(speler1, spel);
                    } else if (teller%2 == 1){
                        beurt(speler2, spel);
                    }
                    teller++;
                }
            }
            exit = true;
        }
        keyboard.close();
    }

    public void beurt(Speler speler, Spel spel){
        System.out.println("Het is " + speler.getNaam() + " zijn beurt.");

        Actiekaart acties = new Actiekaart();
        spel.setSpelerValues(speler);
        if(speler.getDeck().size() == 0){
            speler.leegAflegstapel(spel);
        } else if (speler.getHand().size()==0) {
            spel.voegKaartToe(5, speler.getDeck(), speler.getHand());
        }
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
                    List<Kaart> kaarten = new ArrayList();
                    for(int i=0;i<speler.getHand().size();i++){
                        kaarten.add(speler.getHand().get(i));
                    }
                    int aantalVerwijderd = 0;
                    for (int j = 0;j<kaarten.size();j++) {
                        Kaart k = kaarten.get(j);
                        if (k.getType().equals("Geld")) {
                            speler.addGeld(k.getWaarde());
                            speler.verwijderKaart(k, j-aantalVerwijderd);
                            aantalVerwijderd++;
                        }
                    }
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
                        acties.speelactiekaart(tespelenkaart.getNaam(), speler, spel);
                        speler.verwijderKaart(tespelenkaart, j-1);
                        speler.addActie(-1);
                    } else {
                        System.out.println("U heeft onvoldoende actiebeurten.");
                    }
                    break;
                case "2":
                    System.out.println(speler.getGeld());
                    if(speler.getKoop() > 0){
                        int i = 0;
                        List<Kaart> koopopties = new ArrayList();
                        for(Kaart k : spel.getGeldveld()){
                            if (k.getKost() <= speler.getGeld() && !koopopties.contains(k)){
                                koopopties.add(k);
                                System.out.println(k.getNaam() + " - " + k.getKost() + " | " + i);
                                i++;
                            }
                        }
                        for(Kaart k : spel.getActieveld()){
                            if (k.getKost() <= speler.getGeld() && !koopopties.contains(k)){
                                koopopties.add(k);
                                System.out.println(k.getNaam() + " - " + k.getKost() + " | " + i);
                                i++;
                            }
                        }
                        for(Kaart k : spel.getOverwinningsveld()){
                            if (k.getKost() <= speler.getGeld() && !koopopties.contains(k)){
                                koopopties.add(k);
                                System.out.println(k.getNaam() + " - " + k.getKost() + " | " + i);
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
                    while(speler.getHand().size()>0)
                    spel.voegKaartToe(1, speler.getHand(), speler.getAflegstapel());
                    beurt = false;
                    break;
            }
        }
    }

    public boolean spelGedaan(Spel spel){
        for(Kaart k : spel.getOverwinningsveld()){
            if(Objects.equals(k.getNaam(), "Provincie")){
                return false;
            }
        }

        //3 stapels leeg

        System.out.println("Game over");
        return true;
    }
}
