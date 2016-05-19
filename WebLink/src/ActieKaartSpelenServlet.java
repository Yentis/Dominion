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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "ActieKaartSpelenServlet", urlPatterns = {"/ActieKaartSpelenServlet"})
public class ActieKaartSpelenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        Actiekaart acties = new Actiekaart();

        if(speler.getActie() > 0){
            boolean kaartgespeeld = false;

            String kaartnaam = request.getParameter("kaart");
            for(Kaart k : spel.getActieveld()){
                System.out.println("Kaartnaam: " + k.getNaam() + " \n Gekozen kaart naam: " + kaartnaam);
                System.out.println(Objects.equals(k.getNaam(), kaartnaam));
                if(Objects.equals(kaartnaam, k.getNaam()) && kaartgespeeld){
                    Kaart tespelenkaart = k;
                    spel.voegKaartToe(1, tespelenkaart, speler.getHand(), speler.getAflegstapel());
                    acties.speelactiekaart(tespelenkaart.getNaam(), speler, spel);
                    speler.addActie(-1);
                    kaartgespeeld = true;
                    out.print(kaartnaam);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
