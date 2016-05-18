import com.company.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renzie on 9/05/2016.
 */
public class SpelerTest {
    @Test
    public void testVoegKaartToe() throws Exception {
        Speler speler = new Speler("test");
        int aantalkaarten = 1;
        int aantalkaartenmeerdere = 2;
        List<Kaart> startpunt = new ArrayList<>();
        List<Kaart> bestemming = new ArrayList<>();
        Kaart placeholder = new Kaart();

        for(int i =0;i<3;i++){
            startpunt.add(placeholder);
            bestemming.add(placeholder);
        }

        speler.voegKaartToe(aantalkaarten, startpunt, bestemming);
        assertTrue(bestemming.size() == 4);
        assertTrue(startpunt.size() == 2);
        speler.voegKaartToe(aantalkaartenmeerdere, startpunt, bestemming);
        assertTrue(bestemming.size() == 6);
        assertTrue(startpunt.size() == 0);
    }

    @Test
    public void kanarieTest(){
        System.out.println("ayy lmao");
    }
    @Test
    public void testleegAflegstapelenVoegkaartToe() throws Exception {
        Spel spel = new Spel();
        Speler speler = new Speler("derp");
        spel.maakKaarten();
        spel.vulVeldOp();
        spel.starterDeck(spel, speler);
        speler.voegKaartToe(4,speler.getDeck(), speler.getAflegstapel());
        for (Kaart k : speler.getAflegstapel()){
            System.out.println("Aflegstapel :"+ k);
        }
        speler.leegAflegstapel();
        System.out.println("Aflegstapel na leging = " + speler.getAflegstapel().size());
    }

    @Test
    public void addGeld() throws Exception {
        Spel spel = new Spel();
        Speler speler = new Speler("derp");
        System.out.println("Geld op veld gelegd: " + speler.getGeld());
        speler.addGeld(10);
        System.out.println("Geld op veld gelegd: " + speler.getGeld());
    }
}
