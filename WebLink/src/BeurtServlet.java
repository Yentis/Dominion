import com.company.Spel;
import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "BeurtServlet", urlPatterns = {"/BeurtServlet"})
public class BeurtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hoi");
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");
        Speler speler2 = (Speler)request.getSession().getAttribute("speler2");

        try {
            spel.maakKaarten();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        spel.vulVeldOp();
        spel.starterDeck(spel, speler1);
        spel.starterDeck(spel, speler2);
        speler1.vulHand();
        speler2.vulHand();

        response.sendRedirect("gamepagina.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
