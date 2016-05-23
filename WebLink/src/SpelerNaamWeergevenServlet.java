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

/**
 * Created by niels on 23/05/2016.
 */
@WebServlet(name = "SpelerNaamWeergevenServlet", urlPatterns = {"/SpelerNaamWeergevenServlet"})
public class SpelerNaamWeergevenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        ArrayList<String> spelers = new ArrayList<>();
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");
        Speler speler2 = (Speler)request.getSession().getAttribute("speler2");
        spelers.add(speler1.getNaam());
        spelers.add(speler2.getNaam());



        String json = gson.toJson(spelers);
        out.print(json);




    }


}
