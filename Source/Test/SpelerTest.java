package Test;
import com.company.*;
import org.junit.Test;

/**
 * Created by Renzie on 9/05/2016.
 */
public class SpelerTest {
    @Test
    public void kanarieTest(){
        System.out.println("ayy lmao");
    }
    @Test
    public void testleegAflegstapelenVoegkaartToe() throws Exception {
        Spel spel = new Spel();
        Speler speler = new Speler("derp",0);
        spel.maakKaarten();
        spel.vulVeldOp();
        spel.starterDeck(spel, speler);
        speler.voegKaartToe(4,speler.getDeck(), speler.getAflegstapel());
        for (Kaart k : speler.getAflegstapel()){
            System.out.println("Aflegstapel :"+ k);
        }
        speler.leegAflegstapel(spel);
       System.out.println("Aflegstapel na leging = " + speler.getAflegstapel().size());
    }

    @Test
    public void addGeld() throws Exception {
        Spel spel = new Spel();
        Speler speler = new Speler("derp",0);
        System.out.println("Geld op veld gelegd: " + speler.getGeld());
        speler.addGeld(10);
        System.out.println("Geld op veld gelegd: " + speler.getGeld());
    }
}
