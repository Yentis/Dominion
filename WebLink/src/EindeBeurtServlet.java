import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "EindeBeurtServlet", urlPatterns = {"/EindeBeurtServlet"})
public class EindeBeurtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer teller = (Integer)request.getSession().getAttribute("teller");
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        speler.beëindigbeurt();
        request.getSession().setAttribute("teller", teller+1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
