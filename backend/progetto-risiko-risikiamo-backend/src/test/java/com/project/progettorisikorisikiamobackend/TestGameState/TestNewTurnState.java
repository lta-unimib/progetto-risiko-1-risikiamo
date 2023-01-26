package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
import com.project.progettorisikorisikiamobackend.Cards.EnumCard;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.NewTurnState;
import com.project.progettorisikorisikiamobackend.gameState.RenforceState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.obiettivi.TotTerritories;
import com.project.progettorisikorisikiamobackend.player.Player;

public class TestNewTurnState {

    private IContext context;
    private Turn turn;
    private Territory ownTerritory;
    private Territory neighbor;

    @BeforeEach
    void setUp() {

        Player p1 = new Player("p1");
        Player p2 = new Player("p2");

        turn = new Turn(new ArrayList<>(List.of(p1, p2)));

        ownTerritory = new Territory("test", p1);
        neighbor = new Territory("neighbor", p1);

        Continent c = new Continent("test", 1);

        c.addTerritory(ownTerritory);
        c.addTerritory(neighbor);
        c.addTerritory(new Territory("test2", p1));
        p1.setReinforce(2);

        Map m = new Map("test");
        m.addContinent(c);
        turn.nextTurn();
        p1.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, m))));
        p2.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, m))));
        context = new MockContextClass(turn, m, null, null, new DeckTerritories(m));
        context.setState(new NewTurnState(context));

    }

    @Test
    public void testNewTurnState() {

        NewTurnState newTurnState = (NewTurnState) context.getState();
        assertThrows(IllegalStateException.class, () -> newTurnState.attack(ownTerritory, neighbor, 1));
        assertThrows(IllegalStateException.class, () -> newTurnState.move(ownTerritory, neighbor, 1));

    }

    @Test
    public void endTurn() {

        NewTurnState newTurnState = (NewTurnState) context.getState();
        Player p = turn.getCurrentPlayer();
        newTurnState.endTurn();

        assertNotEquals(p, context.getTurn().getCurrentPlayer());
    }

    @Test
    public void redeemReinforcementsCard() {

        NewTurnState newTurnState = (NewTurnState) context.getState();
        Player p = turn.getCurrentPlayer();

        List<CardTerritory> cards = new ArrayList<>();
        CardTerritory c;
        for (int i = 0; i < 3; i++) {
            c = context.getDeckTerritories().draw();
            if (c.getType() != EnumCard.JOLLY)
                cards.add(c);
            else
                i--;
        }

        CardTerritory c1 = cards.get(0);
        CardTerritory c2 = cards.get(1);
        CardTerritory c3 = cards.get(2);

        c1.setType(EnumCard.CANNONE);
        c2.setType(EnumCard.CANNONE);
        c3.setType(EnumCard.CANNONE);

        newTurnState.redeemReinforcementsCard(c1, c2, c3);

        assertEquals(14, p.getReinforce());

    }

    @Test
    public void placeReinforcements() {
        NewTurnState newTurnState = (NewTurnState) context.getState();
        Player p = turn.getCurrentPlayer();

        p.setReinforce(2);
        newTurnState.placeReinforcements(ownTerritory, 1);
        assertEquals(1, ownTerritory.getArmy());
        assertEquals(1, p.getReinforce());
        assertEquals(context.getState().getClass(), RenforceState.class);
    }
}
