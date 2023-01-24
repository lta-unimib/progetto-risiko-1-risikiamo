package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.InitialFaseState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.obiettivi.TotTerritories;
import com.project.progettorisikorisikiamobackend.player.Player;

public class TestInitialFaseState {

    private List<Player> players;
    private Territory ownTerritory;
    private Territory owner;
    private Territory neighbour;
    private Turn turn;

    private IContext context;

    @BeforeEach
    void setUp() {

        Player p1 = new Player("p1");
        p1.setReinforce(4);
        Player p2 = new Player("p2");

        Map map = new Map("test");
        players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);

        ownTerritory = new Territory("test", p1);

        owner = new Territory("owner", p1);
        neighbour = new Territory("neighbour", p1);

        ownTerritory.addNeighbour(neighbour);
        neighbour.addNeighbour(ownTerritory);

        ownTerritory.setOwner(p1);
        neighbour.setOwner(p1);

        turn = new Turn(players);
        turn.nextTurn();
        p1.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, map))));
        p2.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, map))));
        context = new MockContextClass(turn, map, null);
        context.setState(new InitialFaseState(context));
        List<Player> p = context.getTurn().getInGamePlayerList();
        for (Player player : p) {
            player.setReinforce(4);
        }

    }

    @Test
    void testAttack_shouldThrowIllegalStateException() {

        IState initialFaseState = context.getState();

        assertThrows(IllegalStateException.class, () -> initialFaseState.attack(owner, neighbour, 1));
    }

    @Test
    void testMove_shouldThrowIllegalStateException() {
        IState initialFaseState = context.getState();
        assertThrows(IllegalStateException.class, () -> initialFaseState.move(owner, neighbour, 1));
    }

    @Test
    void testPassTurn_shouldThrowIllegalStateException() {
        IState initialFaseState = context.getState();
        assertThrows(IllegalStateException.class, () -> initialFaseState.endTurn());
    }

    @Test
    void testRedeemReinforcementsCard_shouldThrowIllegalStateException() {
        IState initialFaseState = context.getState();
        assertThrows(IllegalStateException.class, () -> initialFaseState.redeemReinforcementsCard(
                null, null, null));
    }

    @Test
    void testPlaceReinforcements_shouldPlaceReinforcements() {

        InitialFaseState initialFaseState = (InitialFaseState) context.getState();

        assertEquals(3, initialFaseState.getReinforcementToPlace());
        initialFaseState.placeReinforcements(ownTerritory, 1);
        assertEquals(2, initialFaseState.getReinforcementToPlace());
        assertEquals(3, turn.getCurrentPlayer().getReinforce());
    }

    @Test
    void testPlaceReinforcements_shouldChangeState() {

        InitialFaseState initialFaseState = (InitialFaseState) context.getState();
        Player p1 = turn.getCurrentPlayer();
        initialFaseState.placeReinforcements(ownTerritory, 3);

        assertNotEquals(p1, turn.getCurrentPlayer());
    }

    @Test
    void testPlaceReinforcements_shouldThrowIllegalArgumentException() {

        InitialFaseState initialFaseState = (InitialFaseState) context.getState();

        assertThrows(IllegalArgumentException.class, () -> initialFaseState.placeReinforcements(ownTerritory, 4));
        ;

    }

}
