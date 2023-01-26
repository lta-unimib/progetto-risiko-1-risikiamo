package com.project.progettorisikorisikiamobackend.TestCards;

//jupiter
import org.junit.jupiter.api.Test;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.obiettivi.ConquestContinent;
import com.project.progettorisikorisikiamobackend.obiettivi.OpponentDefeated;
import com.project.progettorisikorisikiamobackend.obiettivi.TotTerritories;
import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Territory;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import com.project.progettorisikorisikiamobackend.Cards.*;
import com.project.progettorisikorisikiamobackend.obiettivi.Objective;

public class TestCards {
    @Test
    void testDeckObjectives() {
        Map map = new Map("mappa");
        Continent c1 = new Continent("c1", 1);
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");
        Territory t3 = new Territory("t3");
        c1.addTerritory(t3);
        c1.addTerritory(t2);
        c1.addTerritory(t1);
        map.addContinent(c1);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        players.add(player1);
        players.add(player2);
        players.add(player3);
        DeckObjectives deck = new DeckObjectives(map, null, players);
        assertEquals(10, deck.getDeck().size());
        assertEquals(OpponentDefeated.class, deck.getDeck().get(0).getClass());
        assertEquals(OpponentDefeated.class, deck.getDeck().get(1).getClass());

        Objective cartaPescata = deck.draw(player1);
        assertTrue(OpponentDefeated.class == cartaPescata.getClass() || TotTerritories.class == cartaPescata.getClass()
                || ConquestContinent.class == cartaPescata.getClass());

    }

    @Test
    void testDeckTerritories() {
        Map map = new Map("mappa");
        Continent c1 = new Continent("c1", 1);
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");
        c1.addTerritory(t2);
        c1.addTerritory(t1);
        map.addContinent(c1);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        players.add(player1);
        players.add(player2);
        t1.setOwner(player2);
        t2.setOwner(player2);

        try {
            DeckTerritories deck = new DeckTerritories(map);
            assertEquals(4, deck.getDeck().size());
            assertEquals(CardTerritory.class, deck.getDeck().get(0).getClass());
            assertEquals(CardTerritory.class, deck.getDeck().get(1).getClass());
            CardTerritory card = deck.draw();
            assertEquals(3, deck.getDeck().size());
            assertEquals(CardTerritory.class, card.getClass());
            assertTrue(t1.getName() == card.getName() || t2.getName() == card.getName() || card.getName() == null ||
                    card.getName() == "Jolly1" || card.getName() == "Jolly2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testReedemCards() {
        Map map = new Map("mappa");
        Continent c1 = new Continent("c1", 1);
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");
        Territory t22 = new Territory("t22");
        c1.addTerritory(t2);
        c1.addTerritory(t1);
        c1.addTerritory(t22);
        map.addContinent(c1);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        players.add(player1);
        players.add(player2);
        t1.setOwner(player2);
        t2.setOwner(player2);
        t22.setOwner(player2);

        DeckTerritories daReedem = new DeckTerritories(map);
        CardTerritory card1 = new CardTerritory("t1", EnumCard.CANNONE);
        CardTerritory card2 = new CardTerritory("t2", EnumCard.CANNONE);
        CardTerritory card3 = new CardTerritory("t22", EnumCard.CANNONE);

        List<CardTerritory> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        daReedem.draw();
        daReedem.draw();
        daReedem.draw();
        daReedem.draw();
        daReedem.draw();

        assertEquals(4, daReedem.reedemCards(card1, card2, card3, player1));
        assertEquals(3, daReedem.getDeck().size());
        Territory t3 = new Territory("t3");
        Territory t4 = new Territory("t4");
        Territory t5 = new Territory("t5");
        Continent c2 = new Continent("c2", 1);
        c2.addTerritory(t3);
        c2.addTerritory(t4);
        c2.addTerritory(t5);
        map.addContinent(c2);
        t3.setOwner(player2);
        t4.setOwner(player2);
        t5.setOwner(player2);
        CardTerritory card4 = new CardTerritory("t3", EnumCard.CANNONE);
        CardTerritory card5 = new CardTerritory("t4", EnumCard.CANNONE);
        CardTerritory card6 = new CardTerritory("t5", EnumCard.CANNONE);
        List<CardTerritory> cards2 = new ArrayList<>();
        cards2.add(card4);
        cards2.add(card5);
        cards2.add(card6);

        assertEquals(10, daReedem.reedemCards(card4, card5, card6, player2));
        assertEquals(6, daReedem.getDeck().size());
        daReedem.setDeck(cards2);
        assertEquals(card4, daReedem.getDeck().get(0));
        assertEquals(card5, daReedem.getDeck().get(1));
        assertEquals(card6, daReedem.getDeck().get(2));
        CardTerritory card7 = new CardTerritory("t3", EnumCard.FANTE);
        CardTerritory card8 = new CardTerritory("t4", EnumCard.FANTE);
        CardTerritory card9 = new CardTerritory("t5", EnumCard.CANNONE);
        List<CardTerritory> cards3 = new ArrayList<>();
        cards3.add(card7);
        cards3.add(card8);
        cards3.add(card9);
        daReedem.setDeck(cards3);
        assertEquals(0, daReedem.reedemCards(card7, card8, card9, player2));

    }

    @Test
    void testNewReedemCardsJolly() {
        Map map = new Map("mappa");
        Continent c1 = new Continent("c1", 1);
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");
        Territory t3 = new Territory("t3");
        c1.addTerritory(t2);
        c1.addTerritory(t1);
        c1.addTerritory(t3);
        map.addContinent(c1);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        players.add(player1);
        players.add(player2);
        t1.setOwner(player2);
        t2.setOwner(player2);
        t3.setOwner(player2);
        CardTerritory card1 = new CardTerritory("Jolly", EnumCard.JOLLY);
        CardTerritory card2 = new CardTerritory("t2", EnumCard.CANNONE);
        CardTerritory card3 = new CardTerritory("t3", EnumCard.CANNONE);
        List<CardTerritory> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        DeckTerritories daReedem = new DeckTerritories(map);
        daReedem.setDeck(cards);
        assertEquals(16, daReedem.reedemCards(card1, card2, card3, player2));

    }

    @Test
    void testNewReedemCardsAllDifferent() {
        Map map = new Map("mappa");
        Continent c1 = new Continent("c1", 1);
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");
        Territory t3 = new Territory("t3");
        c1.addTerritory(t2);
        c1.addTerritory(t1);
        c1.addTerritory(t3);
        map.addContinent(c1);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        players.add(player1);
        players.add(player2);
        t1.setOwner(player2);
        t2.setOwner(player2);
        t3.setOwner(player2);
        CardTerritory card1 = new CardTerritory("t1", EnumCard.CANNONE);
        CardTerritory card2 = new CardTerritory("t2", EnumCard.CAVALLO);
        CardTerritory card3 = new CardTerritory("t3", EnumCard.FANTE);
        List<CardTerritory> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        DeckTerritories daReedem = new DeckTerritories(map);

        assertEquals(16, daReedem.reedemCards(card1, card2, card3, player2));

    }

    @Test
    void testCardTerritory() {
        CardTerritory card = new CardTerritory("t1", EnumCard.CANNONE);
        assertEquals("t1", card.getName());
        assertEquals(EnumCard.CANNONE, card.getType());

    }

    @Test
    void EnumCard() {
        assertEquals("cannone", EnumCard.CANNONE.getType());
        assertEquals("cavallo", EnumCard.CAVALLO.getType());
        assertEquals("fante", EnumCard.FANTE.getType());
        assertEquals("jolly", EnumCard.JOLLY.getType());
    }
}
