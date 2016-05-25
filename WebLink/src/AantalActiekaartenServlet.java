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

/**
 * Created by Laurens Visser
 */
@WebServlet(name = "AantalActiekaartenServlet", urlPatterns = {"/AantalActiekaartenServlet"})
public class AantalActiekaartenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        PrintWriter out = response.getWriter();
        List<Integer> kaartnr = new ArrayList<>();
        List<Integer> aantalKaarten = new ArrayList<>();
        List<String> kaartnaam = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        for(Kaart k : spel.getAlleKaarten()){
            if (!kaartnr.contains(k.getNr())){
                kaartnr.add(k.getNr());
            }
        }
        System.out.println(kaartnr);
        for(Kaart k : spel.getAlleKaarten()){
            if (!kaartnaam.contains(k.getNaam())){
                kaartnaam.add(k.getNaam());
            }
        }
        System.out.println(kaartnaam);
        System.out.println(spel.getStapelskaarten());
        for(int i : kaartnr){
            if (spel.getStapelskaarten().get(i) != 0){
                aantalKaarten.add(spel.getStapelskaarten().get(i)-1);
            }
        }
        System.out.println(aantalKaarten);
        results.add(kaartnaam);
        results.add(aantalKaarten);
        String json = gson.toJson(results);
        out.print(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
