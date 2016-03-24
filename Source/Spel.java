package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Yentl-PC on 24/03/2016.
 */
public class Spel {
    private List<Kaart> kaarten = new ArrayList();
    private ArrayList veld = new ArrayList();

    public void maakKaarten(){
        Kaart vloek = new Kaart("Vloek", 0, "Overwinning", "-1 Overwinningspunten\n");
        Kaart provincie = new Kaart("Provincie", 8, "Overwinning", "6 Overwinningspunten\n");
        Kaart heks = new Kaart("Heks", 5, "Actie", "+2 Kaarten\nAlle andere spelers krijgen een Vloek kaart.\n");
        Kaart kelder = new Kaart("Kelder", 2, "Actie", "+1 Actie\nLeg een aantal kaarten af.\n+1 Kaart voor elke afgelegde kaart.\n");
        Kaart kerk = new Kaart("Kerk", 2, "Actie", "Plaats tot 4 kaarten in de vuilbak.\n");
        Kaart gracht = new Kaart("Gracht", 2, "Actie-Reactie", "+2 Kaarten\nWanneeer een andere speler een aanvalskaart speelt, mag je deze kaart tonen om de aanval te annuleren.\n");
        Kaart kanselier = new Kaart("Kanselier", 3, "Actie", "+2 Geld\nJe mag onmiddelijk je deck in de aflegstapel plaatsen.\n");
        Kaart dorp = new Kaart("Dorp", 3, "Actie", "+1 Kaart\n+2 Acties\n");
        Kaart houthakker = new Kaart("Houthakker", 3, "Actie", "+1 Koop\n+2 Geld\n");
        Kaart werkplaats = new Kaart("Werkplaats", 3, "Actie", "Neem een kaart die hoogstens 4 Geld kost.\n");
        Kaart bureaucraat = new Kaart("Bureaucraat", 4, "Actie-Aanval", "Neem een actiekaart; plaats het bovenop je deck.\n Elke andere speler toont een overwinningskaart van zijn hand en plaatst het op zijn deck (of toont een hand zonder overwinningskaarten)\n");
        Kaart feest = new Kaart("Feest", 4, "Actie","Leg deze kaart in de vuilbakstapel. Neem een kaart die hoogstens 5 Geld kost.\n");
        Kaart tuinen = new Kaart("Tuinen", 4, "Overwinning", "Geeft 1 Overwinningspunt per 10 kaarten in je deck (afgerond naar beneden)\n");

        kaarten.add(vloek);
        kaarten.add(provincie);
        kaarten.add(heks);
        kaarten.add(kelder);
        kaarten.add(kerk);
        kaarten.add(gracht);
        kaarten.add(kanselier);
        kaarten.add(dorp);
        kaarten.add(houthakker);
        kaarten.add(werkplaats);
        kaarten.add(bureaucraat);
        kaarten.add(feest);
        kaarten.add(tuinen);
    }

    public void vulVeldOp(){
        //Actie kaarten
        int i = 0;
        while(i<10){
            Random rand = new Random();
            int value = rand.nextInt(13);

            if((kaarten.get(value).getType() == "Actie" || kaarten.get(value).getType() == "Actie-Reactie" || kaarten.get(value).getType() == "Actie-Aanval" || kaarten.get(value).getNaam() == "Tuinen") && veld.contains(kaarten.get(value)) == false){
                veld.add(kaarten.get(value));
                i++;
            }
        }
        for(int j = 0;j<veld.size();j++){
            System.out.println(veld.get(j));
        }
    }

    public void verwijderVanVeld(int i){
        veld.remove(i);
    }
}
