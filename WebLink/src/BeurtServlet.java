import com.company.Actiekaart;
import com.company.Kaart;
import com.company.Spel;
import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "BeurtServlet", urlPatterns = {"/BeurtServlet"})
public class BeurtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");
        Speler speler2 = (Speler)request.getSession().getAttribute("speler2");
        Integer teller = (Integer)request.getSession().getAttribute("teller");

        if(teller == null){
            teller = 0;
        }

        if(teller%2==0) {
            request.getSession().setAttribute("huidigespeler", speler1);
        } else if (teller%2 == 1){
            request.getSession().setAttribute("huidigespeler", speler2);
       }

        request.getSession().setAttribute("teller", teller);
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        spel.getSpelers().forEach(Speler::checkHand);
        spel.setSpelerValues(speler);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
