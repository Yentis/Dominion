import com.company.Spel;
import com.company.Speler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Created by niels on 23/05/2016.
 */
@WebServlet(name = "EindeGameServlet", urlPatterns = {"/EindeGameServlet"})
public class EindeGameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");
        Speler speler2 = (Speler)request.getSession().getAttribute("speler2");
        ArrayList<Integer> Overwinningspunten = new ArrayList<Integer>();
        if (spel.spelGedaan() == true){
            Overwinningspunten.add(speler1.getOverwinningspunten());
            Overwinningspunten.add(speler2.getOverwinningspunten());
            String punten = request.getParameter("overwinningspunten");
            response.sendRedirect("scorepagina.jsp");
        }
        String json = gson.toJson(Overwinningspunten);
        out.print(json);
    }
}



