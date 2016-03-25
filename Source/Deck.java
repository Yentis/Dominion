package dominion;

/**
 *
 * @author Renzie
 */
import java.util.*;


public class Deck {
    //Deck maken
    private ArrayList deck = new ArrayList();
    private ArrayList hand = new ArrayList();
    private ArrayList aflegstapel = new ArrayList();
    private ArrayList vuilbak = new ArrayList();
    //startDeck (7copper, 3 estate)


    public void StarterDeck()
    {
        for (int i=0;i<7;i++)
        {
            deck.add("koper");

        }
        for (int i=0;i<3;i++)
        {
            deck.add("landgoed");

        }
    }

    public void schuifelen(){

    }

    //Kaarten Toevoegen aan Deck
    public void setDeck()
    {

    }

    public void voegKaartToeAanDeck(int aantalKaarten, Kaart kaart)
    {
        for(int i=0;i<aantalKaarten;i++){
            deck.add(kaart);
            spel.verwijderVanVeld(i);
        }
    }

    public void voegKaartToeAanHand(int aantalKaarten){
        for(int i=0;i<aantalKaarten;i++){
            hand.add(deck.get(i));
            deck.remove(i);
        }
    }

    public void kaartAfleggen(Kaart kaart){
        int index = hand.indexOf(kaart);
        hand.remove(index);
        aflegstapel.add(kaart);
    }

    public void verwijderKaart(Kaart kaart){
        int index = hand.indexOf(kaart);
        hand.remove(index);
        vuilbak.add(kaart);
    }
}
