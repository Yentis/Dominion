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
        Boolean beurt = (Boolean)request.getSession().getAttribute("beurt");
        int teller = 0;

        //temp

        /*if(beurt){
            */request.getSession().setAttribute("huidigespeler", speler1);
            speler1.getHand().add(spel.getActieveld().get(0));/*
            *
        } else {
            request.getSession().setAttribute("huidigespeler", speler2);
        }
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        */
        spel.setSpelerValues(speler1);

        /*while(!spel.spelGedaan()){
            if(teller%2 == 0){
                beurt(speler1, spel, request);
            } else if (teller%2 == 1){
                beurt(speler2, spel, request);
            }
            teller++;
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void beurt(Speler speler, Spel spel, HttpServletRequest request){
        spel.setSpelerValues(speler);
        speler.checkHand();
        boolean beurt = (Boolean)request.getSession().getAttribute("beurt");
        while(beurt){
            //toon kaarten
            request.getSession().setAttribute("huidigespeler", speler);

            /*System.out.println("Kaart kopen | 2");
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
                    break;*/
        }
    }
}
