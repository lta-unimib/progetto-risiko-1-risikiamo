package com.project.progettorisikorisikiamobackend.TestCards;

import com.project.progettorisikorisikiamobackend.Cards.*;
import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.Turno.*;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class TestCards {
    @Test
    void testDeckTerritories() {
        List <Card> cards = new ArrayList<>();
        CardTerritory card = new CardTerritory(new Territory("Italia"), "1", "Italia", "Territorio", "image");
        cards.add(card);
        Player player = new Player("p1", "red", 1, 0, 0, 0);
        Player player2 = new Player("p2", "red", 1, 0, 0, 0);

        List <Player> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        Turn turno = new Turn(players);
        DeckTerritories deck = new DeckTerritories(cards, turno);
        deck.draw();
        assertEquals(player, card.getOwner());
        assertEquals("Territory: Italia Value: 1", card.toString());
    }
    
    @Test
    void testDeckObjectives() {
      List<Card> cards = new ArrayList<>();
        PlayerPlaceholder player = new PlayerPlaceholder("p1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("p2");
        List <PlayerPlaceholder> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        CardObjective card = new CardObjective(new OpponentDefeated( player2), "1", "image");
        CardObjective card2 = new CardObjective(new OpponentDefeated( player), "1", "image");
        cards.add(card);
        cards.add(card2);
        DeckObjectives deck = new DeckObjectives(cards, players);
        deck.draw();
        assertEquals(player, card.getOwner());
        assertEquals("Objective: Sconfiggi il giocatore p2", card.toString());
        assertEquals(player2, card2.getOwner());
        assertEquals("Objective: Sconfiggi il giocatore p1", card2.toString());
    }
    
}
