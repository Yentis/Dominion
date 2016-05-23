
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by niels on 20/05/2016.
 */
@WebServlet(name = "StoppenServlet", urlPatterns = {"/StoppenServlet"})
public class StoppenServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* RequestDispatcher rd = request.getRequestDispatcher("Buildservlet");
        rd.forward(request,response);*/
        response.setContentType("text/plain");
        response.sendRedirect("index.jsp");

    }



}