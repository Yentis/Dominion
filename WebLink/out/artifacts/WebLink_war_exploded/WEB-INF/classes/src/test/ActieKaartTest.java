package test;
import com.company.*;

import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by niels & laurens ;)  on 18/05/2016.
 */
public class ActiekaartTest {
    private Speler niels;
    private Speler laurens;

    @Before
    public void setUp()
    {
        laurens = new Speler("speler1");
        niels = new Speler("speler2");


    }

    @Test
    public void testhouthakker() throws Exception
    {
        int Startgeld = niels.getGeld();
        niels.addGeld(2);
        int Startkoop = niels.getKoop();
        niels.addKoop(1);
        assertEquals(niels.getGeld() -2,   Startgeld );
        assertEquals(niels.getKoop() -1, Startkoop);
    }

    @Test
    public void testsmidse() throws Exception
    {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();

        spel.addSpeler(niels);
        spel.geefStartKaarten(niels);

        int Handvoorsmidse = niels.getHand().size();

        Actiekaart actiekaart = new Actiekaart();
        actiekaart.smederij(niels);

        assertEquals(niels.getHand().size() -3, Handvoorsmidse);

    }
    @Test
    public void testlaboratorium() throws  Exception
    {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();

        spel.addSpeler(niels);
        spel.geefStartKaarten(niels);

        int Startactie = niels.getActie();
        int handvoorlaboratorium = niels.getHand().size();

        Actiekaart actiekaart = new Actiekaart();
        actiekaart.laboratorium(niels);

        assertEquals(niels.getActie() -1, Startactie);
        assertEquals(niels.getHand().size() -2, handvoorlaboratorium);

    }

    @Test
    public void testfestival() throws Exception
    {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();
        spel.addSpeler(niels);
        spel.geefStartKaarten(niels);

        int Startgeld = niels.getGeld();
        int Startactie = niels.getActie();
        int Handvoorfestival = niels.getHand().size();
        // Execute
        Actiekaart actiekaart = new Actiekaart();
        actiekaart.festival(niels);
        //Check
        assertEquals(niels.getGeld() -2, Startgeld);
        assertEquals(niels.getActie() -2, Startactie);
        assertEquals(niels.getHand().size() -1, Handvoorfestival);


    }




    @Test
    public void RaadzaalTest() throws Exception {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();


        spel.addSpeler(laurens);
        spel.addSpeler(niels);
        spel.geefStartKaarten(laurens);
        spel.geefStartKaarten(niels);

        int HandVoorRaadzaal = laurens.getHand().size();
        int HandVoorRaadzaalSpeler2 = niels.getHand().size();
        int StartKoop = laurens.getKoop();


        Actiekaart actiekaart = new Actiekaart();
        actiekaart.raadzaal(spel, laurens);

        int HandNaRaadzaal = laurens.getHand().size();
        int HandNaRaadzaalSpeler2 = niels.getHand().size();
        int EindKoop = laurens.getKoop();

        //CHECK
        assertEquals(StartKoop, EindKoop - 1);
        assertEquals(HandVoorRaadzaal , HandNaRaadzaal - 4);
        assertEquals(HandVoorRaadzaalSpeler2 , HandNaRaadzaalSpeler2 - 1);

    }

    @Test
    public void Avonturiertest() throws Exception
    {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();

        spel.addSpeler(niels);
        spel.starterDeck(niels);

        int Handvooravonturier = niels.getHand().size();

        Actiekaart actiekaart = new Actiekaart();
        actiekaart.avonturier(niels);

        assertEquals(niels.getHand().size() -2, Handvooravonturier);

    }


    @Test
    public void TuinenTest() throws Exception {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();
        spel.addSpeler(laurens);
        spel.geefStartKaarten(laurens);

        int StartOverwinningspunten = laurens.getOverwinningspunten();
        System.out.println(StartOverwinningspunten);

        Actiekaart actiekaart = new Actiekaart();
        actiekaart.tuinen(laurens);

        int EindOverwinningspunten = laurens.getOverwinningspunten();
        assertEquals(StartOverwinningspunten, EindOverwinningspunten - 1);

    }

    /*@Test
    public void SchutterijTest() throws Exception {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();

        spel.addSpeler(laurens);
        spel.addSpeler(niels);
        spel.geefStartKaarten(spel, laurens);
        spel.geefStartKaarten(spel, niels);


        int GeldvoorSchutterij = laurens.getGeld();
        int HandVoorSchutterijSpeler2 = niels.getHand().size();
        System.out.println(HandVoorSchutterijSpeler2);

        Actiekaart actiekaart = new Actiekaart();
        actiekaart.schutterij(spel, laurens);

        int HandNaSchutterijSpeler2 = niels.getHand().size();
        int GeldNaSchutterij = laurens.getGeld();


        //CHECK
        assertTrue(HandNaSchutterijSpeler2 == 3);
        assertEquals(GeldNaSchutterij - 2, GeldvoorSchutterij);


    }*/
}
