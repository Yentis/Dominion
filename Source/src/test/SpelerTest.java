package test;
import com.company.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import java.util.ArrayList;
import java.util.List;



public class SpelerTest {
    private Speler laurens;

    @Before
    public void setUp(){
        laurens = new Speler("laurens");
    }
    
    
    @Test
    public void kanarieTest(){
        System.out.println("Test");
    }
    
    
    @Test
    public void GeldToevoegen() throws Exception {


        int StartGeld = laurens.getGeld();
        laurens.addGeld(50);
        int EindGeld = laurens.getGeld();
        assertEquals(StartGeld, EindGeld - 50);

    }

    @Test
    public void ActieToevoegen() throws Exception {

        int StartActie = laurens.getActie();
        laurens.addActie(2);
        int EindActie = laurens.getActie();
        assertEquals(StartActie, EindActie - 2);
    }

    @Test
    public void KoopToevoegen() throws Exception {

        int StartKoop = laurens.getKoop();
        laurens.addKoop(2);
        int EindKoop = laurens.getKoop();
        assertEquals(StartKoop, EindKoop - 2);
    }

    @Test
    public void OverwinningspuntenToevoegen() throws Exception {

        int StartOP = laurens.getOverwinningspunten();
        laurens.addOverwinningspunten(2);
        int EindOP = laurens.getOverwinningspunten();
        assertEquals(StartOP, EindOP - 2);
    }


    @Test
    public void testVoegKaartToe() throws Exception {

        int aantalkaarten = 1;
        int aantalkaartenmeerdere = 2;
        List<Kaart> startpunt = new ArrayList<>();
        List<Kaart> bestemming = new ArrayList<>();
        Kaart placeholder = new Kaart();

        for(int i =0;i<3;i++){
            startpunt.add(placeholder);
            bestemming.add(placeholder);
        }

        laurens.voegKaartToe(aantalkaarten, startpunt, bestemming);
        assertTrue(bestemming.size() == 4);
        assertTrue(startpunt.size() == 2);
        laurens.voegKaartToe(aantalkaartenmeerdere, startpunt, bestemming);
        assertTrue(bestemming.size() == 6);
        assertTrue(startpunt.size() == 0);
    }


    @Test
    public void testleegAflegstapelenVoegkaartToe() throws Exception {
        Spel spel = new Spel();
        Speler speler = new Speler("derp");
        spel.maakKaarten();
        spel.vulVeldOp();
        spel.starterDeck(speler);
        speler.voegKaartToe(4,speler.getDeck(), speler.getAflegstapel());
        for (Kaart k : speler.getAflegstapel()){
            System.out.println("Aflegstapel :"+ k);
        }
        speler.leegAflegstapel();
        System.out.println("Aflegstapel na leging = " + speler.getAflegstapel().size());

    }


}
