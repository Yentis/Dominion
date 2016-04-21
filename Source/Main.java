package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        boolean beurt = true;
        while (!exit) {
            System.out.println("Welkom bij Dominion\n");
            System.out.println("Druk op ENTER om te starten");
            String input = keyboard.nextLine();
            if(input != null) {
                Spel spel = new Spel();
                System.out.println("Voer je naam in");
                input = keyboard.nextLine();
                Speler speler1 = new Speler(input, 0);
                System.out.println("Voer de naam van je vijand in");
                input = keyboard.nextLine();
                Speler speler2 = new Speler(input, 0);
                spel.maakKaarten();
                spel.vulVeldOp();
                spel.starterDeck(speler1.getDeck());
                spel.starterDeck(speler2.getDeck());
                spel.voegKaartToeAanHand(5, speler1.getHand(), speler1.getDeck());
                System.out.println("\nHet is " + speler1.getNaam() + " zijn beurt");
                System.out.println("Kaarten:\n");
                for(Kaart k: speler1.getHand()){
                    System.out.println(k.getNaam());
                }
                while(beurt){
                    System.out.println("\n");
                    System.out.println("Kies een actie:");
                    System.out.println("Geldkaarten neerleggen | 0");
                    System.out.println("Actiekaart spelen | 1");
                    System.out.println("Kaart kopen | 2");
                    System.out.println("Beurt beëindigen | 3");
                    input = keyboard.nextLine();
                    switch (input){
                        case "0":
                            List<Kaart> kaarten = new ArrayList();
                            for(int i=0;i<speler1.getHand().size();i++){
                                kaarten.add(speler1.getHand().get(i));
                            }
                            int aantalVerwijderd = 0;
                            for (int j = 0;j<kaarten.size();j++) {
                                Kaart k = kaarten.get(j);
                                if (k.getType().equals("Geld")) {
                                    speler1.addGeld(k.getWaarde());
                                    speler1.verwijderKaart(k, j-aantalVerwijderd);
                                    aantalVerwijderd++;
                                }
                            }
                            System.out.println("Geld: " + speler1.getGeld());
                            break;
                        case "1":
                            break;
                        case "2":
                            int i = 0;
                            List<Kaart> koopopties = new ArrayList();
                            for(Kaart k : spel.getGeldveld()){
                                if (k.getKost() <= speler1.getGeld() && !koopopties.contains(k)){
                                    koopopties.add(k);
                                    System.out.println(k.getNaam() + " - " + k.getKost() + " | " + i);
                                    i++;
                                }
                            }
                            for(Kaart k : spel.getActieveld()){
                                if (k.getKost() <= speler1.getGeld() && !koopopties.contains(k)){
                                    koopopties.add(k);
                                    System.out.println(k.getNaam() + " - " + k.getKost() + " | " + i);
                                    i++;
                                }
                            }
                            for(Kaart k : spel.getOverwinningsveld()){
                                if (k.getKost() <= speler1.getGeld() && !koopopties.contains(k)){
                                    koopopties.add(k);
                                    System.out.println(k.getNaam() + " - " + k.getKost() + " | " + i);
                                    i++;
                                }
                            }
                            input = keyboard.nextLine();
                            spel.koopKaart(koopopties.get(Integer.parseInt(input)), speler1.getAflegstapel());
                            speler1.addGeld(-koopopties.get(Integer.parseInt(input)).getKost());
                            System.out.println(speler1.getGeld());
                            break;
                        case "3":
                            beurt = false;
                            break;
                }
                }
            }
            exit = true;
        }
        keyboard.close();
    }
}
