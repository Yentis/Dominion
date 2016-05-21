import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yentl-PC on 21/05/2016.
 */
@WebServlet(name = "WijzigGegevensServlet", urlPatterns = {"/WijzigGegevensServlet"})
public class WijzigGegevensServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int acties = Integer.parseInt(request.getParameter("acties"));
        int buys = Integer.parseInt(request.getParameter("buys"));
        int geld = Integer.parseInt(request.getParameter("geld"));
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");

        speler.addActie(acties);
        speler.addGeld(geld);
        speler.addKoop(buys);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
