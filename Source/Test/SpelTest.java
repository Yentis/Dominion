/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

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
    public void kanarieTest(){
        System.out.println("ej twerkt");
    }
    /**
     * Test of getSpelers method, of class Spel.
     */
    @Test
    public void testGetSpelers() {
        System.out.println("getSpelers");
        Spel instance = new Spel();
        List<Speler> expResult = null;
        List<Speler> result = instance.getSpelers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addSpeler method, of class Spel.
     */
    @Test
    public void testAddSpeler() {
        System.out.println("addSpeler");
        Speler speler = null;
        Spel instance = new Spel();
        instance.addSpeler(speler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        for (Kaart k : instance.getAlleKaarten()){
            System.out.println(k);
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }



    /**
     * Test of setStapelskaarten method, of class Spel.
     */

    @Test
    public void testSetStapelskaarten() {
        System.out.println("setStapelskaarten");
        int kaart = 0;
        int waarde = 0;
        Spel instance = new Spel();
        instance.setStapelskaarten(kaart, waarde);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of starterDeck method, of class Spel.
     */
    @Test
    public void testStarterDeck() {
        System.out.println("starterDeck");
        Spel spel = null;
        Speler speler = new Speler("derp", 0);
        Spel instance = new Spel();


       /* spel.starterDeck(spel, speler);
        speler.getDeck().forEach(System.out::println);
*/

    }

    /**
     * Test of schudden method, of class Spel.
     */
    @Test
    public void testSchudden() {
        System.out.println("schudden");
        List<Kaart> deck = null;
        Spel instance = new Spel();
        instance.schudden(deck);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of voegKaartToe method, of class Spel.
     */
    @Test
    public void testVoegKaartToe(Speler speler, Spel spel) {
        System.out.println("voegKaartToe");

        int aantalKaarten = 1;
        Kaart kaart = spel.getGeldveld().get(1);
        List<Kaart> bron = speler.getDeck();
        List<Kaart> bestemming = speler.getHand();
        Spel instance = new Spel();

        instance.voegKaartToe(aantalKaarten, kaart , bron, bestemming);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of berekenScore method, of class Spel.
     */
    @Test
    public void testBerekenScore() {
        System.out.println("berekenScore");
        Speler speler = null;
        Spel instance = new Spel();
        instance.berekenScore(speler);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vulVeldOp method, of class Spel.
     */
    @Test
    public void testVulVeldOp() {
        System.out.println("vulVeldOp");
        Spel instance = new Spel();
        instance.vulVeldOp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of setSpelerValues method, of class Spel.
     */
    @Test
    public void testSetSpelerValues() {
        System.out.println("setSpelerValues");
        Speler speler = null;
        Spel instance = new Spel();
        instance.setSpelerValues(speler);
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
    public void testGetAlleKaarten() {
        System.out.println("getAlleKaarten");
        Spel instance = new Spel();
        List<Kaart> expResult = null;
        List<Kaart> result = instance.getAlleKaarten();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeldveld method, of class Spel.
     */
    @Test
    public void testGetGeldveld() {
        System.out.println("getGeldveld");
        Spel instance = new Spel();
        List<Kaart> expResult = null;
        List<Kaart> result = instance.getGeldveld();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOverwinningsveld method, of class Spel.
     */
    @Test
    public void testGetOverwinningsveld() {
        System.out.println("getOverwinningsveld");
        Spel instance = new Spel();
        List<Kaart> expResult = null;
        List<Kaart> result = instance.getOverwinningsveld();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
