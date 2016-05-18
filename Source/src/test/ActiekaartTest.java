package test;

import com.company.*;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

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
}