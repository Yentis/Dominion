import com.company.Spel;
import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Renzie on 19/05/2016.
 */
@WebServlet(name = "SpelerServlet", urlPatterns = {"/SpelerServlet"})
public class SpelerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Speler speler1 = (Speler)request.getSession().getAttribute("speler1");

        //Voeg spelers toe

        PrintWriter out = response.getWriter();

        out.print(speler1.getActie());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
