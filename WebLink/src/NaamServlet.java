import com.company.Spel;
import com.company.Speler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * Created by Yentl-PC on 17/05/2016.
 */
@WebServlet(name = "NaamServlet", urlPatterns = {"/NaamServlet"})
public class NaamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        String naamspeler1 = request.getParameter("speler1");
        String naamspeler2 = request.getParameter("speler2");

        //Voeg spelers toe
        Spel spel = new Spel();
        request.setAttribute("spel",spel);
        Speler speler1 = new Speler(naamspeler1);
        Speler speler2 = new Speler(naamspeler2);
        spel.addSpeler(speler1);
        spel.addSpeler(speler2);


		//Zet veld op
        try {
            spel.maakKaarten();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        spel.vulVeldOp();
        spel.starterDeck(spel, speler1);
        spel.starterDeck(spel, speler2);
        speler1.voegKaartToe(5, speler1.getDeck(), speler1.getHand());
        speler2.voegKaartToe(5, speler2.getDeck(), speler2.getHand());
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
