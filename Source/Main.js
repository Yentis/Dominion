package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Spel spel = new Spel();
        Deck speler1 = new Deck();
        Deck speler2 = new Deck();
        spel.maakKaarten();
        spel.vulVeldOp();
        speler1.starterDeck(spel);
        speler2.starterDeck(spel);

        speler1.voegKaartToeAanHand(5);
        System.out.println(speler1.getDeck());

    }
}
