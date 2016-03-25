package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
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
        
        int j = 0;
        while (myRs.next()) {
            kaarten.add(j, new Kaart(myRs.getString("naam"), myRs.getInt("kost"), myRs.getString("type"), myRs.getString("omschrijving")));
            j++;
        }
    }

    public void vulVeldOp(){
        //Actie kaarten
        int i = 0;
        while(i<10){
            Random rand = new Random();
            int value = rand.nextInt(kaarten.size());
            if((Objects.equals(kaarten.get(value).getType(), "Actie") || Objects.equals(kaarten.get(value).getType(), "Actie-Reactie") || Objects.equals(kaarten.get(value).getType(), "Actie-Aanval") || Objects.equals(kaarten.get(value).getNaam(), "Tuinen")) && actieveld.contains(kaarten.get(value)) == false){
                actieveld.add(kaarten.get(value));
                System.out.println(kaarten.get(value).toString() + "\n");
                i++;
            }
        }

        //Overwinningskaarten
        for(int j = 0;j<kaarten.size();j++){
            if(Objects.equals(kaarten.get(j).getType(), "Overwinning") && !Objects.equals(kaarten.get(j).getNaam(), "Tuinen") && !Objects.equals(kaarten.get(j).getNaam(), "Vloek")){
                overwinningsveld.add(kaarten.get(j));
                System.out.println(kaarten.get(j).toString() + "\n");
            }
        }

        //Geldkaarten
        for(int k = 0;k<kaarten.size();k++){
            if(Objects.equals(kaarten.get(k).getType(), "Geld") || Objects.equals(kaarten.get(k).getNaam(), "Vloek")){
                geldveld.add(kaarten.get(k));
                System.out.println(kaarten.get(k).toString() + "\n");
            }
        }
    }

    public void verwijderVanVeld(int i){
        //
    }
}
