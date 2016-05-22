import com.company.Kaart;
import com.company.Spel;
import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Created by Renzie on 20/05/2016.
 */
@WebServlet(name = "KoopKaartServlet", urlPatterns = {"/KoopKaartServlet"})
public class KoopKaartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        Kaart teKopenKaart = new Kaart();

        String kaartnaam = request.getParameter("kaart");
        boolean kaartGekocht = false;
        if (speler.getKoop() > 0){
            for(Kaart k : spel.getAlleKaarten()){
                if(Objects.equals(kaartnaam,k.getNaam()) && !kaartGekocht){
                    teKopenKaart = k;
                    spel.koopKaart(teKopenKaart,speler.getAflegstapel());
                    speler.addKoop(-1);
                    kaartGekocht = true;
                    speler.addGeld( - teKopenKaart.getKost());
                    out.print(kaartnaam);

                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
