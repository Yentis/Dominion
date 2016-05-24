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
            }
            for(Kaart k : spel.getActieveld()){
                if(kaartnaam.contains(k.getNaam()) && !kaartgespeeld){
                    Kaart tespelenkaart = k;
                    if(speciaal){
                        afteprintenkaarten = acties.speelactiekaartspecial(kaartnaam, spel, speler, kaarten, Integer.parseInt(janee));
                        if(afteprintenkaarten.size() == 0 || Objects.equals(kaartnaam, "Bureaucraat")){
                            spel.voegKaartToe(1, tespelenkaart, speler.getHand(), speler.getAflegstapel());
                            speler.addActie(-1);
                        }
                        array = afteprintenkaarten.toArray(new String[0]);
                    } else {
                        spel.voegKaartToe(1, tespelenkaart, speler.getHand(), speler.getAflegstapel());
                        result = acties.speelactiekaart(tespelenkaart.getNaam(), speler, spel, Integer.parseInt(janee), kaarten);
                        speler.addActie(-1);
                    }
                    List<Object> results = new ArrayList<>();
                    results.add(kaartnaam);
                    results.add(result);
                    results.add(array);
                    kaartgespeeld = true;
                    String json = gson.toJson(results);
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
