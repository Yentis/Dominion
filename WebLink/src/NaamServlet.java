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
        System.out.println("hey");
        response.setContentType("text/plain");
        String naamspeler1 = request.getParameter("speler1");
        String naamspeler2 = request.getParameter("speler2");

        //Voeg spelers toe
        Spel spel = new Spel();
        request.getSession().setAttribute("spel", spel);
        Speler speler1 = new Speler(naamspeler1);
        request.getSession().setAttribute("speler1",speler1);
        Speler speler2 = new Speler(naamspeler2);
        spel.addSpeler(speler1);
        spel.addSpeler(speler2);

        response.sendRedirect("BeurtServlet");
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
