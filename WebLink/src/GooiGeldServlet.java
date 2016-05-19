import com.company.Kaart;
import com.company.Spel;
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
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "GooiGeldServlet", urlPatterns = {"/GooiGeldServlet"})
public class GooiGeldServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Spel spel = (Spel)request.getSession().getAttribute("spel");
        List<String> afgedruktekaarten = new ArrayList<>();



        String json = gson.toJson(afgedruktekaarten);
        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
