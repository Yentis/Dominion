import com.company.Spel;
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
import java.util.List;

/**
 * Created by Renzie on 19/05/2016.
 */
@WebServlet(name = "SpelerServlet", urlPatterns = {"/SpelerServlet"})
public class SpelerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");
        List<Integer> spelerwaarden = new ArrayList<>();
        PrintWriter out = response.getWriter();

        spelerwaarden.add(speler1.getActie());
        spelerwaarden.add(speler1.getKoop());
        spelerwaarden.add(speler1.getGeld());

        String json = gson.toJson(spelerwaarden);

        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
