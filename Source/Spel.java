package com.company;

import java.util.*;
import java.sql.*;

/**
 * Created by Yentl-PC on 24/03/2016.
 */
public class Spel {
    private List<Kaart> kaarten = new ArrayList<>();
    private List<Kaart> actieveld = new ArrayList();
    private List<Kaart> overwinningsveld = new ArrayList();
    private List<Kaart> geldveld = new ArrayList();

    public void maakKaarten() throws SQLException {
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dominion", "root", "");
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from kaart");

        int i = 0;
        while (myRs.next()) {
            kaarten.add(i, new Kaart(myRs.getString("naam"), myRs.getInt("kost"), myRs.getString("type"), myRs.getString("omschrijving"), myRs.getInt("waarde")));
            i++;
        }
    }

    public void starterDeck(Spel spel, List<Kaart> deck)
    {
        int aantalkaarten = 0;

        for(int i=0;i<spel.getGeldveld().size();i++){
            if(Objects.equals(spel.getGeldveld().get(i).getNaam(), "Koper") && aantalkaarten < 7){
                deck.add(spel.getGeldveld().get(i));
                aantalkaarten++;
                spel.getGeldveld().remove(i);
            }
        }

        for(int i=0;i<spel.getOverwinningsveld().size();i++){
            if(Objects.equals(spel.getOverwinningsveld().get(i).getNaam(), "Landgoed") && aantalkaarten < 10){
                deck.add(spel.getOverwinningsveld().get(i));
                aantalkaarten++;
                spel.getOverwinningsveld().remove(i);
            }
        }
        schudden(deck);
    }

    public void schudden(List<Kaart> deck)
    {
        Collections.shuffle(deck);
    }


    public void voegKaartToe(int aantalKaarten, List<Kaart> startpunt, List<Kaart> bestemming){
        for(int  i=0; i<aantalKaarten;i++){
            bestemming.add(startpunt.get(i));
        }
        for(int  i=0; i<aantalKaarten;i++){
            startpunt.remove(0);
        }
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
                for(int stapelopvullen=0;stapelopvullen<aantalopstapel;stapelopvullen++){
                    actieveld.add(kaarten.get(value));
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

                for(int stapelopvullen = 0;stapelopvullen<aantalopstapel;stapelopvullen++){
                    overwinningsveld.add(kaarten.get(j));
                    //System.out.println(kaarten.get(j).toString() + "\n");
                }
            }
        }

        //Geldkaarten
        for(int k = 0;k<kaarten.size();k++){
            if(Objects.equals(kaarten.get(k).getType(), "Geld") || Objects.equals(kaarten.get(k).getNaam(), "Vloek")){
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

                for(int stapelopvullen = 0;stapelopvullen < aantalopstapel;stapelopvullen++){
                    geldveld.add(kaarten.get(k));
                    //System.out.println(kaarten.get(k).toString() + "\n");
                }
            }
        }
    }

    public void koopKaart(Kaart k, List<Kaart> aflegstapel){
        aflegstapel.add(k);
        if(getActieveld().contains(k)){
            getActieveld().remove(k);
        } else if (getOverwinningsveld().contains(k)){
            getOverwinningsveld().remove(k);
        } else {
            getGeldveld().remove(k);
        }
    }

    public void verwijderVanVeld(String naam, String type){
        if(Objects.equals(type, "Overwinning")){
            for(int i = 0;i<overwinningsveld.size();i++){
                if(Objects.equals(overwinningsveld.get(i).getNaam(), naam)){
                    overwinningsveld.remove(i);
                }
            }
        } else if (Objects.equals(type, "Geld") || Objects.equals(type, "Vloek")){
            for(int i = 0;i<geldveld.size();i++){
                if(Objects.equals(geldveld.get(i).getNaam(), naam)){
                    geldveld.remove(i);
                }
            }
        } else {
            for(int i = 0;i<actieveld.size();i++){
                if(Objects.equals(actieveld.get(i).getNaam(), naam)){
                    actieveld.remove(i);
                }
            }
        }
    }

    public void setSpelerValues(Speler speler){
        speler.setActie(1);
        speler.setKoop(1);
        speler.setGeld(0);
    }

    public List<Kaart> getGeldveld() {
        return geldveld;
    }

    public List<Kaart> getOverwinningsveld() {
        return overwinningsveld;
    }

    public List<Kaart> getActieveld() {
        return actieveld;
    }
}
