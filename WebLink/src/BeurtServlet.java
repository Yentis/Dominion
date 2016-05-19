import com.company.Actiekaart;
import com.company.Kaart;
import com.company.Spel;
import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "BeurtServlet", urlPatterns = {"/BeurtServlet"})
public class BeurtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");
        Speler speler2 = (Speler)request.getSession().getAttribute("speler2");
        int teller = 0;

        while(!spel.spelGedaan()){
            if(teller%2 == 0){
                beurt(speler1, spel);
            } else if (teller%2 == 1){
                beurt(speler2, spel);
            }
            teller++;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                        acties.speelactiekaart(tespelenkaart.getNaam(), speler, spel);
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
