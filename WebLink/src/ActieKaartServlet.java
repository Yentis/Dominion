import com.company.Kaart;
import com.company.Spel;
import com.company.Speler;
import com.google.gson.Gson;
import jdk.nashorn.internal.objects.NativeJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yentl-PC on 18/05/2016.
 */
@WebServlet(name = "ActieKaartServlet", urlPatterns = {"/ActieKaartServlet"})
public class ActieKaartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        List<String> afgedruktekaarten = new ArrayList<>();

        for(Kaart k : spel.getActieveld()){
           if(!afgedruktekaarten.contains(k.getNaam())){
               afgedruktekaarten.add(k.getNaam());
          }
       }

        String json = gson.toJson(afgedruktekaarten);
        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
