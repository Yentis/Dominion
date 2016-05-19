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

/*
    @Test
    public void testfestival() throws Exception
    {
        int Startgeld = niels.getGeld();
        niels.addGeld(2);
        int Startactie = niels.getActie();
        niels.addActie(2);
        int Startkaarten = niels.getHand().size();
        niels.voegKaartToe(1, niels.getHand(), niels.getDeck());
        assertEquals(niels.getGeld() -2, Startgeld);
        assertEquals(niels.getActie() -2, Startactie);
        assertEquals(niels.voegKaartToe(niels.getHand().size() -1, Startkaarten));


    }*/
    @Test
    public void RaadzaalTest() throws Exception {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();

        spel.addSpeler(laurens);
        spel.addSpeler(niels);
        spel.starterDeck(spel, laurens);
        spel.starterDeck(spel, niels);

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
}
