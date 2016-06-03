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
 * Created by Renzie on 19/05/2016.
 */
@WebServlet(name = "KoopServlet", urlPatterns = {"/KoopServlet"})
public class KoopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        Boolean speciaal = Boolean.parseBoolean(request.getParameter("speciaal"));
        String limits = request.getParameter("limits");
        int max;
        List<String> koopopties = new ArrayList<>();

        if (speler.getKoop() > 0 && !speciaal) {
            max = speler.getGeld();
            koopopties = koopoptiesToevoegen(spel, max);
            for(String k : koopopties){
                System.out.println("Koopopties: " + k);
            }
        } else if (speciaal){
            max = Integer.parseInt(limits);
            koopopties = koopoptiesToevoegen(spel, max);
        }

        String json = gson.toJson(koopopties);
        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<String> koopoptiesToevoegen(Spel spel, int max){
        List<String> koopopties = new ArrayList<>();
        for (Kaart k : spel.getAlleKaarten()) {
            if (k.getKost() <= max && !koopopties.contains(k.getNaam())) {
                koopopties.add(k.getNaam());
            }
        }
        return koopopties;
    }
}
