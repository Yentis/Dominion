import com.company.Speler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yentl-PC on 19/05/2016.
 */
@WebServlet(name = "NaamServlet", urlPatterns = {"/NaamServlet"})
public class NaamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Speler speler = (Speler)request.getSession().getAttribute("huidigespeler");
        PrintWriter out = response.getWriter();
        out.print(speler.getNaam());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}