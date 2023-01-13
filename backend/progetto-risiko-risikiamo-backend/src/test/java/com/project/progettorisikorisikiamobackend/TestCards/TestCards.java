package com.project.progettorisikorisikiamobackend.TestCards;

import com.project.progettorisikorisikiamobackend.Cards.*;
import com.project.progettorisikorisikiamobackend.player.*;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.*;

import static org.junit.Assert.assertEquals;

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
    List<Player> players = new ArrayList<>();
    Player player1 = new Player("player1");
    Player player2 = new Player("player2");
    players.add(player1);
    players.add(player2);
    CardTerritory card1 = new CardTerritory("territorio1", EnumCard.CANNONE);
    CardTerritory card2 = new CardTerritory("territorio2", EnumCard.CANNONE);
    CardTerritory card3 = new CardTerritory("territorio3", EnumCard.CANNONE);
    List<CardTerritory> deckTerritories = new ArrayList<>();
    deckTerritories.add(card1);
    deckTerritories.add(card2);    
    deckTerritories.add(card3);
    DeckTerritories deck = new DeckTerritories(deckTerritories, map, players );
    assertEquals(3, deck.getDeck().size() );
    assertEquals(CardTerritory.class, deck.getDeck().get(0).getClass() );
    assertEquals(CardTerritory.class, deck.getDeck().get(1).getClass() );    
    assertEquals(4, deck.reedemCards(deckTerritories, player2));
}



}