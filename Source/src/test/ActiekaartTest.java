package test;
import com.company.*;

import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by niels on 18/05/2016.
 */
public class ActiekaartTest {
    private Speler Niels;

@Before
public void setUp()
    {
     Niels = new Speler("niels");
    }


@Test
    public void testhouthakker() throws Exception
    {
        int Startgeld = Niels.getGeld();
        Niels.addGeld(2);
        int Startkoop = Niels.getKoop();
        Niels.addKoop(1);
        assertEquals(Niels.getGeld() -2,   Startgeld );
        assertEquals(Niels.getKoop() -1, Startkoop);
    }


@Test
    public void testfestival() throws Exception
    {
        int Startgeld = Niels.getGeld();
        Niels.addGeld(2);
        int Startactie = Niels.getActie();
        Niels.addActie(2);
        int Startkaarten = Niels.getHand().size();
        Niels.voegKaartToe(1, Niels.getHand(), Niels.getDeck());
        assertEquals(Niels.getGeld() -2, Startgeld);
        assertEquals(Niels.getActie() -2, Startactie);
        assertEquals(Niels.voegKaartToe(Niels.getHand().size() -1, Startkaarten);


    }
@Test
    public void RaadzaalTest() throws Exception {
        Spel spel = new Spel();
        spel.maakKaarten();
        spel.vulVeldOp();
        Speler laurens = new Speler("speler1");
        Speler niels = new Speler("speler2");
        spel.addSpeler(laurens);
        spel.addSpeler(niels);
        spel.starterDeck(spel, laurens);
        spel.starterDeck(spel, niels);

        int HandVoorRaadzaal = laurens.getHand().size();
        int HandVoorRaadzaalSpeler2 = niels.getHand().size();

        laurens.voegKaartToe(4, laurens.getDeck(), laurens.getHand());
        for(Speler s : spel.getSpelers()){
            if(!Objects.equals(s.getNaam(), laurens.getNaam())){
                s.voegKaartToe(1, s.getDeck(), s.getHand());
            }
        }

        int HandNaRaadzaal = laurens.getHand().size();
        int HandNaRaadzaalSpeler2 = niels.getHand().size();

        int StartKoop = laurens.getKoop();
        laurens.addKoop(1);
        int EindKoop = laurens.getKoop();

        //CHECK

        assertEquals(StartKoop, EindKoop - 1);
        assertEquals(HandVoorRaadzaal , HandNaRaadzaal - 4);
        assertEquals(HandVoorRaadzaalSpeler2 , HandNaRaadzaalSpeler2 - 1);
        
    }
