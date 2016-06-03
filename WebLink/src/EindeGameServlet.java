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
        response.setContentType("text/plain");
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        if (spel.spelGedaan()){
            System.out.println("spel is gedaan");
            response.sendRedirect("gamepagina.jsp");
        }
    }
}



