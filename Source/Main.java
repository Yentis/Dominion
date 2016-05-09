package com.company;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    int teller = 0;

    public static void main(String[] args) {
        try {
            Main obj = new Main();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String[] args) throws Exception {
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
            if (input != null) {
                Spel spel = new Spel();
                System.out.println("Voer de naam van speler 1 in");
                input = keyboard.nextLine();
                Speler speler1 = new Speler(input, 0);
                spel.addSpeler(speler1);
                System.out.println("Voer de naam van speler 2 in");
                input = keyboard.nextLine();
                Speler speler2 = new Speler(input, 0);
                spel.addSpeler(speler2);
                spel.maakKaarten();
                spel.vulVeldOp();

                //Geef startkaarten
                spel.starterDeck(spel, speler1);
                spel.starterDeck(spel, speler2);
                speler1.voegKaartToe(5, speler1.getDeck(), speler1.getHand());
                speler2.voegKaartToe(5, speler2.getDeck(), speler2.getHand());

                //Start beurt
                while (!spelGedaan(spel)) {
                    if (teller % 2 == 0) {
                        spel.beurt(speler1, spel);
                    } else if (teller % 2 == 1) {
                        spel.beurt(speler2, spel);
                    }
                    teller++;
                }
            }
            exit = true;
        }
        keyboard.close();
    }


    public boolean spelGedaan(Spel spel) {
        //provincie stapel leeg
        for (Kaart k : spel.getOverwinningsveld()) {
            if (Objects.equals(k.getNaam(), "Provincie")) {
                return false;
            }
        }

        //3 stapels leeg
        int aantallegestapels = 0;
        for (int i = 1; i < spel.getStapelskaarten().size(); i++) {
            if (spel.getStapelskaarten().get(i) == 1) {
                aantallegestapels++;
            }
        }
        if (aantallegestapels < 3) {
            return false;
        }

        System.out.println("Game over");
        int hoogstescore = 0;
        String winnaar = "";
        for (Speler s : spel.getSpelers()) {
            spel.berekenScore(s);
            int score = s.getOverwinningspunten();
            if (score > hoogstescore) {
                hoogstescore = score;
                winnaar = s.getNaam();
            }
        }
        System.out.println("De winnaar is " + winnaar);
        return true;
    }
}
