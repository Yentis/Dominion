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
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Geef je naam");
        String input = keyboard.nextLine();
        keyboard.close();
        Speler speler = new Speler(input, 0);
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
     */
    @Test
    public void testKoopKaart() {
        System.out.println("koopKaart");
        Kaart k = null;
        List<Kaart> aflegstapel = null;
        Spel instance = new Spel();
        instance.koopKaart(k, aflegstapel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getStapelskaarten method, of class Spel.
     */
    @Test
    public void testGetStapelskaarten() {
        System.out.println("getStapelskaarten");
        Spel instance = new Spel();
        List<Integer> expResult = null;
        List<Integer> result = instance.getStapelskaarten();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
