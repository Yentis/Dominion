import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Laurens Visser on 23/05/2016.
 */
@WebServlet(name = "AflegstapelServlet", urlPatterns = {"/AflegstapelServlet"})
public class AflegstapelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topAflegstapel;
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        if (speler.getAflegstapel().size() == 0){
            topAflegstapel = "";
        }
        else{
            topAflegstapel = speler.getAflegstapel().get(speler.getAflegstapel().size()-1).getNaam();
        }

        PrintWriter out = response.getWriter();
        out.print(topAflegstapel);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
