import com.company.Kaart;
import com.company.Spel;
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

/**
 * Created by Yentl-PC on 18/05/2016.
 */
@WebServlet(name = "ActieKaartServlet", urlPatterns = {"/ActieKaartServlet"})
public class ActieKaartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        //System.out.println(request.getAttribute("spel"));
        //List<Kaart> afgedruktekaarten = new ArrayList<>();

        //for(Kaart k : spel.getActieveld()){
           // if(!afgedruktekaarten.contains(k)){
               // out.print(spel.getActieveld().get(0).getNaam());
        List<String> dieven = new ArrayList<>();
        for(int i=0;i<10;i++){
            dieven.add("Dief");
        }
        out.print(dieven);
             //   afgedruktekaarten.add(k);
          //  }
       // }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
