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
        System.out.print(deck);
        
        
    }
    
    
    
    //Kaarten Toevoegen aan Deck
   
}
