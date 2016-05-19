import com.company.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Renzie on 19/05/2016.
 */
@WebServlet(name = "HandServlet", urlPatterns = {"/HandServlet"})
public class HandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        PrintWriter out = response.getWriter();
        List<String> Hand = new ArrayList<>();

        for(Kaart k : speler1.getHand()){
            Hand.add(k.getNaam());
        }
        String json = gson.toJson(Hand);

        out.print(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
