import com.company.Kaart;
import com.company.Spel;
import com.company.Speler;
import org.junit.Test;
import java.util.*;
/**
 * Created by Renzie on 9/05/2016.
 */
public class ActieKaartTest {
    @Test
    public void speelactiekaart() throws Exception {
        Spel spel = new Spel();
        spel.maakKaarten();
        Speler speler = new Speler("derp");
        spel.starterDeck(spel, speler);

        for (Kaart k : spel.getAlleKaarten()) {
            spel.voegKaartToe(3,k,speler.getHand(), speler.getAflegstapel());
            if (Objects.equals(k.getType(), "Actie") || Objects.equals(k.getType(), "Actie-Reactie") || Objects.equals(k.getType(), "Actie-Aanval")) {
                System.out.println(k.getNaam() + " | " );

            }
        }
    }
}