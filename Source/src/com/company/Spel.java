package com.company;

import java.util.*;
import java.sql.*;

/**
 * Created by Yentl-PC on 24/03/2016.
 */
public class Spel {
    //region Instance Variables
    private List<Kaart> kaarten = new ArrayList<>();
    private List<Kaart> actieveld = new ArrayList<>();
    private List<Kaart> overwinningsveld = new ArrayList<>();
    private List<Kaart> geldveld = new ArrayList<>();
    private List<Speler> spelers = new ArrayList<>();
    private List<Kaart> alleKaarten = new ArrayList<>();
    private List<Integer> stapelskaarten = new ArrayList<>(Collections.nCopies(32, 0));
    //endregion

    //region Properties
    public List<Speler> getSpelers() {
        return spelers;
    }

    public List<Integer> getStapelskaarten() {
        return stapelskaarten;
    }

    public List<Kaart> getGeldveld() {
        return geldveld;
    }

    public List<Kaart> getActieveld() {
        return actieveld;
    }

    public List<Kaart> getAlleKaarten() {
        for (Kaart k : geldveld) {
            alleKaarten.add(k);
        }
        for (Kaart k : overwinningsveld) {
            alleKaarten.add(k);
        }
        for (Kaart k : actieveld) {
            alleKaarten.add(k);
        }
        return alleKaarten;
    }

    private void setStapelskaarten(int kaart, int waarde) {
        stapelskaarten.set(kaart, stapelskaarten.get(kaart) + waarde);
    }
    //endregion

    //region Behaviour
    public void maakKaarten() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dominion", "root", "|NX!hhCq*VpweFw6\"Af0Tme%6l=Iae2U");
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from kaart");

        int i = 0;
        while (myRs.next()) {
            kaarten.add(i, new Kaart(myRs.getInt("kaartnr") - 1, myRs.getString("naam"), myRs.getInt("kost"), myRs.getString("type"), myRs.getString("omschrijving"), myRs.getInt("waarde")));
            i++;
        }
    }

    public void addSpeler(Speler speler) {
        spelers.add(speler);
    }

    private void schudden(List<Kaart> deck) {
        Collections.shuffle(deck);
    }

    public void voegKaartToe(int aantalKaarten, Kaart kaart, List<Kaart> bron, List<Kaart> bestemming) {
        for (int i = 0; i < aantalKaarten; i++) {
            bestemming.add(kaart);
        }
        bron.remove(kaart);
    }

    public void setSpelerValues(Speler speler) {
        speler.setActie(1);
        speler.setKoop(1);
        speler.setGeld(0);
    }

    public void starterDeck(Speler speler) {
        int aantalkaarten = 0;

        aantalkaarten = voegKoperKaartenToe(aantalkaarten, speler);
        voegLandgoedKaartenToe(aantalkaarten, speler);

        schudden(speler.getDeck());
    }

    private int voegKoperKaartenToe(int aantalkaarten, Speler speler){
        for(int i=0;i<geldveld.size();i++){
            if(Objects.equals(geldveld.get(i).getNaam(), "Koper") && aantalkaarten < 7){

                speler.getDeck().add(geldveld.get(i));
                setStapelskaarten(25, -1);
                aantalkaarten++;
                geldveld.remove(i);
            }
        }
        return aantalkaarten;
    }

    private void voegLandgoedKaartenToe(int aantalkaarten, Speler speler){
        for(int i=0;i<overwinningsveld.size();i++){
            if(Objects.equals(overwinningsveld.get(i).getNaam(), "Landgoed") && aantalkaarten < 10){
                speler.getDeck().add(overwinningsveld.get(i));
                setStapelskaarten(28, -1);
                aantalkaarten++;
                overwinningsveld.remove(i);
            }
        }
    }

    public void vulVeldOp() {
        vulActieKaartenOp();
        vulOverwinningsKaartenOp();
        vulGeldKaartenOp();
    }

    private void stapelsOpvullen(int value, List<Kaart> bestemming, int aantalopstapel){
        setStapelskaarten(kaarten.get(value).getNr(), 1);
        for (int stapelopvullen = 0; stapelopvullen < aantalopstapel; stapelopvullen++) {
            bestemming.add(kaarten.get(value));
            setStapelskaarten(kaarten.get(value).getNr(), 1);
        }
    }

    private void vulActieKaartenOp() {
        //10 actiekaarten op het veld
        while (actieveld.size() < 99) {
            Random rand = new Random();
            int value = rand.nextInt(kaarten.size());
            //Als het een actiekaart of tuin kaart is en het nog niet bestaat
            if ((kaarten.get(value).getType().contains("Actie") || Objects.equals(kaarten.get(value).getNaam(), "Tuinen")) && actieveld.contains(kaarten.get(value)) == false) {
                //10 exemplaren per actiekaart
                stapelsOpvullen(value, actieveld, 10);
            }
        }
    }

    private void vulOverwinningsKaartenOp() {
        for (int i = 0; i < kaarten.size(); i++) {
            //Overwinningskaart of Vloek kaart, geen Tuin
            if ((Objects.equals(kaarten.get(i).getType(), "Overwinning") || Objects.equals(kaarten.get(i).getType(), "Vloek")) && !Objects.equals(kaarten.get(i).getNaam(), "Tuinen")) {
                int aantalopstapel;
                //Landgoed heeft 14 exemplaren
                switch(kaarten.get(i).getNaam()){
                    case "Landgoed":
                        aantalopstapel = 14;
                        break;
                    case "Vloek":
                        aantalopstapel = 10;
                        break;
                    default:
                        aantalopstapel = 2;
                        break;
                }
                stapelsOpvullen(i, overwinningsveld, aantalopstapel);
            }
        }
    }

    private void vulGeldKaartenOp() {
        for (int i = 0; i < kaarten.size(); i++) {
            if (Objects.equals(kaarten.get(i).getType(), "Geld")) {
                int aantalopstapel;
                switch (kaarten.get(i).getNaam()) {
                    case "Koper":
                        aantalopstapel = 60;
                        break;
                    case "Zilver":
                        aantalopstapel = 40;
                        break;
                    case "Goud":
                        aantalopstapel = 30;
                        break;
                    default:
                        aantalopstapel = 0;
                        break;
                }
                stapelsOpvullen(i, geldveld, aantalopstapel);
            }
        }
    }

    public void koopKaart(Kaart k, List<Kaart> aflegstapel) {
        aflegstapel.add(k);

        if (actieveld.contains(k)) {
            actieveld.remove(k);
        } else if (overwinningsveld.contains(k)) {
            overwinningsveld.remove(k);
        } else if (geldveld.contains(k)) {
            geldveld.remove(k);
        }

        setStapelskaarten(k.getNr(), -1);
    }

    public boolean spelGedaan() {
        return provinciesOp() || drieStapelsLeeg();
    }

    private boolean provinciesOp() {
        System.out.println("provincie check start");
        for (Kaart k : overwinningsveld) {
            if (Objects.equals(k.getNaam(), "Provincie")) {
                System.out.println("provincie detected");
                System.out.println("provincie check over");
                return false;
            }
        }
        return true;
    }

    private boolean drieStapelsLeeg() {
        int aantallegestapels = 0;
        for (int i = 1; i < stapelskaarten.size(); i++) {
            if (stapelskaarten.get(i) == 1) {
                aantallegestapels++;
            }
        }
        return aantallegestapels >= 3;
    }

    public String winnaar() {
        int hoogstescore = 0;
        String winnaar = "";
        for (Speler s : spelers) {
            s.berekenScore();
            int score = s.getOverwinningspunten();
            if (score > hoogstescore) {
                hoogstescore = score;
                winnaar = s.getNaam();
            }
        }
        return winnaar;
    }

    public void geefStartKaarten(Speler speler){
        starterDeck(speler);
        speler.vulHand();
    }
    //endregion
}
