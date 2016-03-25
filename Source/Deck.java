/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

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
        for (int i=0;i<7;i++) //moet nog aangepast worden via databank
        {
            deck.add(i+1);       
        }
        for (int i=0;i<3;i++)
        {
            deck.add(i+8);               
        }
        schudden(); 
        //System.out.println("start(zonder shuffle)"+deck);        
        //System.out.println("start"+deck);
        //for(int i=0;i<deck.size();i++){
        //System.out.println("start"+deck);//}
    }
    
    public void schudden()      
    {
       Collections.shuffle(deck);
    }
    
 
    //Deck naar hand
    public void voegKaartToeAanHand(int aantalKaarten)
    {
        for(int i=0;i<aantalKaarten;i++){
            //System.out.println(i + "" + deck.get(i));
            hand.add(deck.get(i));
            
            
        }
        //System.out.println("Deck"+deck);
        verwijderKaartenUitDeck(aantalKaarten);
        
    }
    public void verwijderKaartenUitDeck(int aantalKaarten)
    {
        for(int i=0;i<aantalKaarten;i++){
            deck.remove(0);
            //System.out.println("Voor: " +deck.size());            
            //System.out.println("Achter: " + deck.size());           
            //System.out.println("Hand"+hand);
            //System.out.println("Deck"+deck);
        }
        
    //System.out.println("hand"+hand);
    //System.out.println("deck"+deck);
    
    
    }
    //van hand naar Deck (bij het einde van de beurt
    public void legKaartenAf()
    {
        //System.out.println("legKaartenWeg");
        for (int i = 0; i<hand.size();i++)
        {
            aflegstapel.add(hand.get(i)); 
            //System.out.println("Hand"+hand);
            //System.out.println("aflegstapel"+aflegstapel);              
        }
        
        hand.clear();       
        
        //System.out.println("aflegstapel"+aflegstapel+"\nhand"+hand);
    
    }
    //van aflegstapel terug naar deck
    public void kaartenTerugNaarDeck()
    {
        //System.out.println("deck"+deck);
        if (deck.isEmpty()) 
        {
            for (int i = 0; i<aflegstapel.size();i++)
            {
                deck.add(aflegstapel.get(i));
                
            }
            
            aflegstapel.clear();
            schudden();
            
        }   
        else{
            //System.out.println(deck.size());
            //System.out.println("deck nog gevuld");
        }
        //System.out.println("\ndeck"+deck);
        
    }
}
