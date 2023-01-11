package com.project.progettorisikorisikiamobackend.TestCards;

import com.project.progettorisikorisikiamobackend.Cards.*;
import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.obiettivi.*;


import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class TestCards {
    @Test
    void testDeckTerritories() {
        Map <CardTerritory, String> cards = new HashMap<>();
        CardTerritory card = new CardTerritory(new Territory("Italia"), "1");
        cards.put(card, "1");
        DeckTerritories deck = new DeckTerritories(cards);
        assertEquals(deck.draw(), card);
        assertEquals("Territory: Italia Value: 1", card.toString());
    }
    
    @Test
    void testDeckObjectives() {
        Map <CardObjective, String> cards = new HashMap<>();
        PlayerPlaceholder player = new PlayerPlaceholder("p1");
        CardObjective card = new CardObjective(new OpponentDefeated( player));
        cards.put(card, "1");
        DeckObjectives deck = new DeckObjectives(cards);
        assertEquals(deck.draw(), card);
        assertEquals("Objective: Sconfiggi il giocatore p1", card.toString());
    }
}
