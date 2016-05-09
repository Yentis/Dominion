package com.company;

import java.util.*;
import java.sql.*;

/**
 * Created by Yentl-PC on 24/03/2016.
 */
public class Spel {
    private List<Kaart> kaarten = new ArrayList<>();
    private List<Kaart> actieveld = new ArrayList<>();
    private List<Kaart> overwinningsveld = new ArrayList<>();
    private List<Kaart> geldveld = new ArrayList<>();
    private List<Speler> spelers = new ArrayList<>();
    private List<Kaart> alleKaarten = new ArrayList<>();
    private List<Integer> stapelskaarten = new ArrayList<>(Collections.nCopies(32, 0));

    public List<Speler> getSpelers() {
        return spelers;
    }

    public void addSpeler(Speler speler){
        spelers.add(speler);
    }

    public void maakKaarten() throws SQLException {
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dominion", "root", "");
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from kaart");

        int i = 0;
        while (myRs.next()) {
            kaarten.add(i, new Kaart(myRs.getInt("kaartnr")-1, myRs.getString("naam"), myRs.getInt("kost"), myRs.getString("type"), myRs.getString("omschrijving"), myRs.getInt("waarde")));
            i++;
        }
    }

    public void setStapelskaarten(int kaart, int waarde) {
        stapelskaarten.set(kaart, stapelskaarten.get(kaart) + waarde);
    }

    public void starterDeck(Spel spel, Speler speler)
    {
        int aantalkaarten = 0;

        for(int i=0;i<spel.getGeldveld().size();i++){
            if(Objects.equals(spel.getGeldveld().get(i).getNaam(), "Koper") && aantalkaarten < 7){
                speler.getDeck().add(spel.getGeldveld().get(i));
                setStapelskaarten(25, -1);
                aantalkaarten++;
                getGeldveld().remove(i);
            }
        }

        for(int i=0;i<spel.getOverwinningsveld().size();i++){
            if(Objects.equals(spel.getOverwinningsveld().get(i).getNaam(), "Landgoed") && aantalkaarten < 10){
                speler.getDeck().add(spel.getOverwinningsveld().get(i));
                setStapelskaarten(28, -1);
                aantalkaarten++;
                spel.getOverwinningsveld().remove(i);
            }
        }
        schudden(speler.getDeck());
    }

    public void schudden(List<Kaart> deck)
    {
        Collections.shuffle(deck);
    }

    public void voegKaartToe(int aantalKaarten, Kaart kaart, List<Kaart> bron, List<Kaart> bestemming){
        for(int  i=0; i<aantalKaarten;i++){
            bestemming.add(kaart);
        }
        bron.remove(kaart);
    }

    public void berekenScore(Speler speler){
        int score = 0;
        for(Kaart k : speler.getAflegstapel()){
            if(Objects.equals(k.getType(), "Overwinning") || Objects.equals(k.getType(), "Vloek")){
                score += k.getWaarde();
            }
        }
        for(Kaart k : speler.getDeck()){
            if(Objects.equals(k.getType(), "Overwinning") || Objects.equals(k.getType(), "Vloek")){
                score += k.getWaarde();
            }
        }
        for(Kaart k : speler.getHand()){
            if(Objects.equals(k.getType(), "Overwinning") || Objects.equals(k.getType(), "Vloek")){
                score += k.getWaarde();
            }
        }
        speler.addOverwinningspunten(score);
    }

    public void vulVeldOp(){
        //Actie kaarten
        int i = 0;
        int aantalactiekaarten = 100;
        while(i<aantalactiekaarten){
            Random rand = new Random();
            int value = rand.nextInt(kaarten.size());
            if((Objects.equals(kaarten.get(value).getType(), "Actie") || Objects.equals(kaarten.get(value).getType(), "Actie-Reactie") || Objects.equals(kaarten.get(value).getType(), "Actie-Aanval") || Objects.equals(kaarten.get(value).getNaam(), "Tuinen")) && actieveld.contains(kaarten.get(value)) == false){
                int aantalopstapel = 10;
                setStapelskaarten(kaarten.get(value).getNr(), 1);
                for(int stapelopvullen=0;stapelopvullen<aantalopstapel;stapelopvullen++){
                    actieveld.add(kaarten.get(value));
                    setStapelskaarten(kaarten.get(value).getNr(), 1);
                    //System.out.println(kaarten.get(value).toString() + "\n");
                    i++;
                }
            }
        }

        //Overwinningskaarten
        for(int j = 0;j<kaarten.size();j++){
            if((Objects.equals(kaarten.get(j).getType(), "Overwinning") || Objects.equals(kaarten.get(j).getType(), "Vloek")) && !Objects.equals(kaarten.get(j).getNaam(), "Tuinen")){
                int aantalopstapel;
                if(Objects.equals(kaarten.get(j).getNaam(), "Landgoed")){
                    aantalopstapel = 14;
                } else if (Objects.equals(kaarten.get(j).getType(), "Vloek")) {
                    aantalopstapel = 10;
                } else {
                    aantalopstapel = 8;
                }

                setStapelskaarten(kaarten.get(j).getNr(), 1);
                for(int stapelopvullen = 0;stapelopvullen<aantalopstapel;stapelopvullen++){
                    overwinningsveld.add(kaarten.get(j));
                    setStapelskaarten(kaarten.get(j).getNr(), 1);
                    //System.out.println(kaarten.get(j).toString() + "\n");
                }
            }
        }

        //Geldkaarten
        for(int k = 0;k<kaarten.size();k++){
            if(Objects.equals(kaarten.get(k).getType(), "Geld")){
                int aantalopstapel;
                switch(kaarten.get(k).getNaam()) {
                    case "Koper":
                        aantalopstapel = 60;
                        break;
                    case "Zilver":
                        aantalopstapel = 40;
                        break;
                    case "Goud":
                        aantalopstapel = 30;
                        break;
                    default: aantalopstapel = 0;
                        break;
                }

                setStapelskaarten(kaarten.get(k).getNr(), 1);
                for(int stapelopvullen = 0;stapelopvullen < aantalopstapel;stapelopvullen++){
                    geldveld.add(kaarten.get(k));
                    setStapelskaarten(kaarten.get(k).getNr(), 1);
                    //System.out.println(kaarten.get(k).toString() + "\n");
                }
            }
        }
    }

    public void koopKaart(Kaart k, List<Kaart> aflegstapel){
        aflegstapel.add(k);
        if(actieveld.contains(k)){
            actieveld.remove(k);
            setStapelskaarten(k.getNr(), -1);
        } else if (overwinningsveld.contains(k)){
            overwinningsveld.remove(k);
            setStapelskaarten(k.getNr(), -1);
        } else if (geldveld.contains(k)) {
            geldveld.remove(k);
            setStapelskaarten(k.getNr(), -1);
        }
    }

    public void setSpelerValues(Speler speler){
        speler.setActie(1);
        speler.setKoop(1);
        speler.setGeld(0);
    }

    public List<Integer> getStapelskaarten() {
        return stapelskaarten;
    }

    public List<Kaart> getAlleKaarten() {
        for(Kaart k : geldveld){
            alleKaarten.add(k);
        }
        for(Kaart k : overwinningsveld){
            alleKaarten.add(k);
        }
        for(Kaart k : actieveld){
            alleKaarten.add(k);
        }
        return alleKaarten;
    }

    public List<Kaart> getGeldveld() {
        return geldveld;
    }

    public List<Kaart> getOverwinningsveld() {
        return overwinningsveld;
    }

    public void keuzeMaken(Speler speler){
        System.out.println("Acties: " + speler.getActie());
        System.out.println("Koop: " + speler.getKoop());
        System.out.println("Geld: " + speler.getGeld() + "\n");
        System.out.println("Kaarten:\n");
        for (Kaart k : speler.getHand()) {
            System.out.println(k.getNaam());
        }
        System.out.println("\n");
        System.out.println("Kies een actie:");
        System.out.println("Geldkaarten neerleggen | 0");
        System.out.println("Actiekaart spelen | 1");
        System.out.println("Kaart kopen | 2");
        System. out.println("Beurt beeindigen | 3");

    }

    public void beurt(Speler speler, Spel spel) {
        System.out.println("Het is " + speler.getNaam() + " zijn beurt.");

        Actiekaart acties = new Actiekaart();
        spel.setSpelerValues(speler);
        if (speler.getDeck().size() == 0) {
            speler.leegAflegstapel(spel);
        } else if (speler.getHand().size() == 0) {
            speler.voegKaartToe(5, speler.getDeck(), speler.getHand());
        }
        boolean beurt = true;
        Scanner keyboard = new Scanner(System.in);
        while (beurt) {
            keuzeMaken(speler);
            String input = keyboard.nextLine();
            switch (input) {
                case "0":
                    List<Kaart> kaarten = new ArrayList<>();
                    for (int i = 0; i < speler.getHand().size(); i++) {
                        kaarten.add(speler.getHand().get(i));
                    }
                    int aantalVerwijderd = 0;
                    for (int j = 0; j < kaarten.size(); j++) {
                        Kaart k = kaarten.get(j);
                        if (k.getType().equals("Geld")) {
                            speler.addGeld(k.getWaarde());
                            speler.verwijderKaart(k, j - aantalVerwijderd);
                            aantalVerwijderd++;
                        }
                    }
                    break;
                case "1":
                    if (speler.getActie() > 0) {
                        int i = 0;
                        int j = 0;
                        List<Kaart> actiekaarten = new ArrayList<>();
                        System.out.println("Kies een actiekaart: \n");
                        for (Kaart k : speler.getHand()) {
                            if (Objects.equals(k.getType(), "Actie") || Objects.equals(k.getType(), "Actie-Reactie") || Objects.equals(k.getType(), "Actie-Aanval")) {
                                System.out.println(k.getNaam() + " | " + i);
                                actiekaarten.add(k);
                            }
                            j++;
                        }
                        input = keyboard.nextLine();
                        Kaart tespelenkaart = actiekaarten.get(Integer.parseInt(input));
                        spel.voegKaartToe(1, tespelenkaart, speler.getHand(), speler.getAflegstapel());
                        acties.speelactiekaart(tespelenkaart.getNaam(), speler, spel);
                        speler.addActie(-1);
                    } else {
                        System.out.println("U heeft onvoldoende actiebeurten.");
                    }
                    break;
                case "2":
                    System.out.println(speler.getGeld());
                    if (speler.getKoop() > 0) {
                        int i = 0;
                        List<Kaart> koopopties = new ArrayList<>();
                        for (Kaart k : spel.getAlleKaarten()) {
                            if (k.getKost() <= speler.getGeld() && !koopopties.contains(k)) {
                                koopopties.add(k);
                                System.out.println(k.getNaam() + " - Kost: " + k.getKost() + "| Aantal nog beschikbaar: " + (spel.getStapelskaarten().get(k.getNr()) - 1) + " | " + i);
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
                    while (speler.getHand().size() > 0)
                        speler.voegKaartToe(1, speler.getHand(), speler.getAflegstapel());
                    beurt = false;
                    break;
            }
        }
    }
}
