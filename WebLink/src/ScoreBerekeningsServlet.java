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
 * Created by Yentl-PC on 6/06/2016.
 */
@WebServlet(name = "ScoreBerekeningsServlet", urlPatterns = {"/ScoreBerekeningsServlet"})
public class ScoreBerekeningsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        List<String> info = new ArrayList<>();
        Speler speler1 = (Speler) request.getSession().getAttribute("speler1");
        Speler speler2 = (Speler) request.getSession().getAttribute("speler2");

        info.add(speler1.getNaam());
        info.add(speler2.getNaam());

        speler1.berekenScore();
        speler2.berekenScore();

        info.add(Integer.toString(speler1.getOverwinningspunten()));
        info.add(Integer.toString(speler2.getOverwinningspunten()));

        String json = gson.toJson(info);
        out.print(json);
    }
}
