/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        deck.
        System.out.print(deck);
        
    }
    
    public void schuifelen
    
    //Kaarten Toevoegen aan Deck
    public void setDeck()
    {
        
    
    
    }
    public void voegKaartToeAanHand(int aantalKaarten)
    {
        for(int i=0;i<aantalKaarten;i++){
            hand.add(deck.get(i));
            deck.remove(i);
        }
    
    }
}
