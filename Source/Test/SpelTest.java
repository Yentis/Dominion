/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.company.*;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Renzie
 */
public class SpelTest {

    public SpelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kanarieTest() {
        System.out.println("ej twerkt");
    }

    /**
     * Test of addSpeler method, of class Spel.
     */
    @Test
    public void testAddSpeler() {
        System.out.println("addSpeler");
        Spel instance = new Spel();
        Speler speler = new Speler("Test", 0);
        instance.addSpeler(speler);
        System.out.println(speler);
    }

    /**
     * Test of maakKaarten method, of class Spel.
     */
    @Test
    public void testMaakKaarten() throws Exception {

        System.out.println("maakKaarten");
        Spel instance = new Spel();
        instance.maakKaarten();
        instance.vulVeldOp();
        for (Kaart k : instance.getAlleKaarten()) {
            System.out.println(k);
        }
    }


    /**
     * Test of starterDeck method, of class Spel.
     */
    @Test
    public void testStarterDeck() throws SQLException {
        System.out.println("starterDeck");

        Speler speler = new Speler("derp", 0);
        Spel instance = new Spel();
        instance.maakKaarten();
        instance.vulVeldOp();


        instance.starterDeck(instance, speler);
        speler.getDeck().forEach(System.out::println);


    }


    /**
     * Test of voegKaartToe method, of class Spel.
     */
    @Test
    public void testVoegKaartToe() throws SQLException {
        System.out.println("voegKaartToe");
        Speler speler = new Speler("Tester", 0);
        Spel instance = new Spel();
        instance.addSpeler(speler);
        instance.maakKaarten();
        instance.vulVeldOp();
        Kaart kaart = new Kaart();

        List<Kaart> bron = instance.getGeldveld();
        List<Kaart> bestemming = speler.getDeck();
        int aantalKaarten = 1;
        if (Objects.equals(kaart.getNaam(), "Heks")) {
            System.out.println(speler.getDeck());
            instance.voegKaartToe(aantalKaarten, kaart, bron, bestemming);
        }
    }


    /**
     * Test of koopKaart method, of class Spel.
     * Confirmed working - Yentl
     */
    @Test
    public void testKoopKaart() throws SQLException {
        System.out.println("koopKaart");
        Spel instance = new Spel();
        Speler testspeler = new Speler("Test", 0);
        instance.maakKaarten();
        instance.vulVeldOp();
        Kaart k = instance.getAlleKaarten().get(0);
        List<Kaart> aflegstapel = testspeler.getAflegstapel();
        int aantalkaartenvooraf = instance.getStapelskaarten().get(k.getNr());
        instance.koopKaart(k, aflegstapel);
        int aantalkaartenachteraf = instance.getStapelskaarten().get(k.getNr());

        //Kaart wordt op de aflegstapel gelegd
        assertEquals(aflegstapel.get(0), k);

        //Stapel verminderd met 1
        assertEquals(aantalkaartenvooraf, aantalkaartenachteraf+1);
    }

    /**
     * Test of getStapelskaarten method, of class Spel.
     */
    @Test
    public void testGetStapelskaarten() throws SQLException {
        System.out.println("getStapelskaarten");
        Spel instance = new Spel();
        instance.maakKaarten();
        instance.vulVeldOp();
        List<Integer> result = instance.getStapelskaarten();
        for (Integer i : result){
            System.out.println(i);
        }
    }

    /**
     * Test of getAlleKaarten method, of class Spel.
     */
    @Test
    public void testGetKaarten() throws SQLException {
        System.out.println("getAlleKaarten");
        Spel instance = new Spel();
        instance.maakKaarten();
        instance.vulVeldOp();
        List<Kaart> result = instance.getAlleKaarten();
        for (Kaart k : result) {
            System.out.println(k);
        }
    }

    /**
     * Test of getOverwinningsveld method, of class Spel.
     */
    @Test
    public void testGetOverwinningsveld() throws SQLException {
        System.out.println("getOverwinningsveld");
        Spel instance = new Spel();
        instance.maakKaarten();
        instance.vulVeldOp();
        List<Kaart> result = instance.getAlleKaarten();
        for (Kaart k : result) {
            if (Objects.equals(k.getType(), "Overwinning")) {
                System.out.println(k);
            }
        }
// het zou dan ook werken voor de andere types
    }
}
