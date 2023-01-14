package com.project.progettorisikorisikiamobackend.TestCards;

import com.project.progettorisikorisikiamobackend.player.*;

import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.cards.*;
import com.project.progettorisikorisikiamobackend.map.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;



import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestCards {
@Test
public void testDeckObjectives() {
    Map map = new Map("mappa");
    List<Player> players = new ArrayList<>();
    Player player1 = new Player("player1");
    Player player2 = new Player("player2");
    players.add(player1);
    players.add(player2);
    DeckObjectives deck = new DeckObjectives(map, players);
    assertEquals(6, deck.getDeck().size());
    assertEquals(OpponentDefeated.class, deck.getDeck().get(0).getClass() );
    assertEquals(OpponentDefeated.class, deck.getDeck().get(1).getClass());
    deck.draw(player2);
    assertEquals(5, deck.getDeck().size());

}
@Test
public void testDeckTerritories() {
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
    }catch(Exception e) {
        System.out.println(e.getMessage());
    }
}



}