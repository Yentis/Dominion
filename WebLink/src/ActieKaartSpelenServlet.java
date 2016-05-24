import com.company.Actiekaart;
import com.company.Kaart;
import com.company.Spel;
import com.company.Speler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "ActieKaartSpelenServlet", urlPatterns = {"/ActieKaartSpelenServlet"})
public class ActieKaartSpelenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        Actiekaart acties = new Actiekaart();
        System.out.println("first check");
        if(speler.getActie() > 0){
            boolean kaartgespeeld = false;

            String kaartnaam = request.getParameter("kaart");
            String janee = request.getParameter("janee");
            Boolean speciaal = Boolean.parseBoolean(request.getParameter("speciaal"));
            List<String> kaarten = new ArrayList<>();
            String[] lijstkaarten = new String[0];
            String[] array = new String[0];
            String result = "";
            List<String> afteprintenkaarten = new ArrayList<>();
            if(!Objects.equals(request.getParameter("lijstkaarten"), "")){
                lijstkaarten = request.getParameterValues("lijstkaarten[]");
            }
            for(int i=0;i<lijstkaarten.length;i++){
                kaarten.add(lijstkaarten[i]);
                System.out.println("Kaart " + i + ": " + kaarten.get(i));
            }
            System.out.println("second check");
            System.out.println("janee: " + janee);
            for(Kaart k : spel.getActieveld()){
                System.out.println("third check");
                if(kaartnaam.contains(k.getNaam()) && !kaartgespeeld){
                    System.out.println("fourth check");
                    Kaart tespelenkaart = k;
                    if(speciaal){
                        System.out.println("speciaal");
                        afteprintenkaarten = acties.speelactiekaartspecial(kaartnaam, spel, speler, kaarten, Integer.parseInt(janee));
                        if(afteprintenkaarten.size() == 0){
                            System.out.println("fourth check 2");
                            spel.voegKaartToe(1, tespelenkaart, speler.getHand(), speler.getAflegstapel());
                            speler.addActie(-1);
                        }
                        array = afteprintenkaarten.toArray(new String[0]);
                    } else {
                        System.out.println("fifth check");
                        spel.voegKaartToe(1, tespelenkaart, speler.getHand(), speler.getAflegstapel());
                        System.out.println("sixth check");
                        System.out.println("result: " +result);
                        System.out.println("tespelenkaart: " + tespelenkaart.getNaam());
                        result = acties.speelactiekaart(tespelenkaart.getNaam(), speler, spel, Integer.parseInt(janee), kaarten);
                        System.out.println("result 2: " +result);
                        System.out.println("seventh check");
                        speler.addActie(-1);
                        System.out.println("eighth check");
                    }
                    System.out.println("ninth check");
                    List<Object> results = new ArrayList<>();
                    results.add(kaartnaam);
                    results.add(result);
                    results.add(array);
                    kaartgespeeld = true;
                    String json = gson.toJson(results);
                    System.out.println("tenth check");
                    out.print(json);
                }
            }
        } else {
            out.print("");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
