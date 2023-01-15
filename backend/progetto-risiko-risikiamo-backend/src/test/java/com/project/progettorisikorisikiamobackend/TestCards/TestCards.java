package com.project.progettorisikorisikiamobackend.TestCards;

import com.project.progettorisikorisikiamobackend.player.*;

import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.Cards.*;
import com.project.progettorisikorisikiamobackend.map.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;



import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestCards {
@Test
 void testDeckObjectives() {
    Map map = new Map("mappa");
    List<Player> players = new ArrayList<>();
    Player player1 = new Player("player1");
    Player player2 = new Player("player2");
    Player player3 = new Player("player3");
    players.add(player1);
    players.add(player2);
    players.add(player3);
    DeckObjectives deck = new DeckObjectives(map, players);
    assertEquals(9, deck.getDeck().size());
    assertEquals(OpponentDefeated.class, deck.getDeck().get(0).getClass() );
    assertEquals(OpponentDefeated.class, deck.getDeck().get(1).getClass());
    
    deck.draw(player2);
    assertTrue(OpponentDefeated.class == deck.draw(player1).getClass() || TotTerritories.class == deck.draw(player1).getClass()|| ConquestContinent.class == deck.draw(player1).getClass());
    
       


}
@Test
 void testDeckTerritories() {
    Map map = new Map("mappa");
    Continent c1 = new Continent("c1", 1);
    Territory t1 = new Territory("t1");
    Territory t2 = new Territory("t2");
    c1.addTerritory(t2);
    c1.addTerritory(t1);
    map.addContinent(c1, "c1");
    List<Player> players = new ArrayList<>();
    Player player1 = new Player("player1");
    Player player2 = new Player("player2");
    players.add(player1);
    players.add(player2);
    t1.setOwner(player2);
    t2.setOwner(player2);
    
    try{
    DeckTerritories deck = new DeckTerritories( map, players );
    assertEquals(2, deck.getDeck().size() );
    assertEquals(CardTerritory.class, deck.getDeck().get(0).getClass() );
    assertEquals(CardTerritory.class, deck.getDeck().get(1).getClass() ); 
    CardTerritory card = deck.draw();
    assertEquals(1, deck.getDeck().size() );
    assertEquals(CardTerritory.class, card.getClass() );
    assertTrue(t1.getName() == card.getName() || t2.getName() == card.getName());
    }catch(Exception e) {
        System.out.println(e.getMessage());
    }
    try{
    DeckTerritories daReedem = new DeckTerritories( map, players );
    CardTerritory card1 = new CardTerritory("c1", EnumCard.CANNONE);
    CardTerritory card2 = new CardTerritory("c2", EnumCard.CANNONE);
    CardTerritory card3 = new CardTerritory("c3", EnumCard.CANNONE);
   
    List <CardTerritory> cards = new ArrayList<>();
    cards.add(card1);
    cards.add(card2);
    cards.add(card3);
    daReedem.setDeck(cards);
    assertEquals(4, daReedem.reedemCards(card1, card2, card3, player2));
    assertEquals(0, daReedem.getDeck().size());
    Territory t3 = new Territory("t3");
    Territory t4 = new Territory("t4");
    Territory t5 = new Territory("t5");
    Continent c2 = new Continent("c2", 1);
    c2.addTerritory(t3);
    c2.addTerritory(t4);
    c2.addTerritory(t5);
    map.addContinent(c2, "c2");
    t3.setOwner(player2);
    t4.setOwner(player2);
    t5.setOwner(player2);
    CardTerritory card4 = new CardTerritory("t3", EnumCard.CANNONE);
    CardTerritory card5 = new CardTerritory("t4", EnumCard.CANNONE);
    CardTerritory card6 = new CardTerritory("t5", EnumCard.CANNONE);
    List <CardTerritory> cards2 = new ArrayList<>();
    cards2.add(card4);
    cards2.add(card5);
    cards2.add(card6);
   
   daReedem.setDeck(cards2);
    assertEquals(10, daReedem.reedemCards(card4, card5, card6, player2));
    assertEquals(3, daReedem.getDeck().size());
    assertEquals(card4, daReedem.getDeck().get(0));
    assertEquals(card5, daReedem.getDeck().get(1));
    assertEquals(card6, daReedem.getDeck().get(2));
  

    }catch(Exception e) {
        System.out.println(e.getMessage());
    }finally {
        System.out.println("test");
    }
    }
   
@Test
 void testCardTerritory(){
    CardTerritory card = new CardTerritory("t1", EnumCard.CANNONE);
    assertEquals("t1", card.getName());
    assertEquals(EnumCard.CANNONE, card.getType());
    



}
@Test
 void EnumCard(){
    assertEquals("cannone", EnumCard.CANNONE.getType());
    assertEquals("cavallo", EnumCard.CAVALLO.getType());
    assertEquals("fante", EnumCard.FANTE.getType());
    assertEquals("jolly", EnumCard.JOLLY.getType());
}
}