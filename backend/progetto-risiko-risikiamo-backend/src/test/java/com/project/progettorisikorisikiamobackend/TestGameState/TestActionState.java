package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.ActionState;
import com.project.progettorisikorisikiamobackend.gameState.NewTurnState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

public class TestActionState {

    private IContext context;
    private Turn turn;
    private Territory ownTerritory;
    private Territory neighbor;
    private Map map;

    @BeforeEach
    void setUp() {

        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Continent c = new Continent("c", 1);
        turn = new Turn(List.of(p1, p2));

        ownTerritory = new Territory("test", p1);
        neighbor = new Territory("neighbor", p1);

        map = new Map("name");
        c.addTerritory(ownTerritory);
        c.addTerritory(neighbor);

        ownTerritory.addNeighbor(neighbor);
        neighbor.addNeighbor(ownTerritory);

        map.addContinent(c, c.getName());

        List<Player> players = new ArrayList<>();
        players.addAll(turn.getPlayerList().keySet());
        context = new MockContextClass(turn, map, null, null, new DeckTerritories(map, players));
        context.setState(new ActionState(context));

    }

    @Test
    void test_shouldThrowUnsupportedOperationException() {

        IState actionState = context.getState();

        assertThrows(UnsupportedOperationException.class, () -> actionState.redeemReinforcementsCard(null, null, null));
        assertThrows(UnsupportedOperationException.class, () -> actionState.placeReinforcements(ownTerritory, 0));

    }

    @Test
    void testMove() {

        IState actionState = context.getState();

        Player p1 = turn.getCurrentPlayer();
        ownTerritory.addArmy(2);

        actionState.move(ownTerritory, neighbor, 1);

        assertNotEquals(p1, context.getTurn().getCurrentPlayer());
        assertEquals(context.getState().getClass(), NewTurnState.class);

    }

    @Test
    void testEndTurn() {

        IState actionState = context.getState();

        Player p1 = turn.getCurrentPlayer();

        actionState.endTurn();

        assertNotEquals(p1, context.getTurn().getCurrentPlayer());
        assertEquals(context.getState().getClass(), NewTurnState.class);

    }

    @Test
    void testAttack() {

        ActionState actionState = (ActionState) context.getState();

        Player p1 = turn.getCurrentPlayer();

        int size = context.getDeckTerritories().getDeck().size();

        assertEquals(2, context.getDeckTerritories().getDeck().size());

        neighbor.setOwner(new Player("p2"));

        ownTerritory.addArmy(20);
        neighbor.addArmy(1);

        while (ownTerritory.getArmy() > 1 && neighbor.getOwner() != p1) {
            actionState.attack(ownTerritory, neighbor, 1);
        }

        actionState.endTurn();

        assertNotEquals(p1, context.getTurn().getCurrentPlayer());
        assertEquals(context.getState().getClass(), NewTurnState.class);

        assertTrue(actionState.isHaveConquest());
        assertEquals(1, p1.getCarte().size());

        assertEquals(size - 1, context.getDeckTerritories().getDeck().size());

    }

    @Test
    void testAttack2() {

        IState actionState = context.getState();

        Player p1 = turn.getCurrentPlayer();

        int size = context.getDeckTerritories().getDeck().size();

        neighbor.setOwner(new Player("p2"));

        ownTerritory.addArmy(2);
        neighbor.addArmy(20);
        actionState.attack(ownTerritory, neighbor, 1);

        actionState.endTurn();

        assertNotEquals(p1, context.getTurn().getCurrentPlayer());
        assertEquals(context.getState().getClass(), NewTurnState.class);

        assertEquals(size, context.getDeckTerritories().getDeck().size());

    }

}
