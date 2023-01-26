package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.ActionState;
import com.project.progettorisikorisikiamobackend.gameState.RenforceState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.obiettivi.TotTerritories;
import com.project.progettorisikorisikiamobackend.player.Player;

public class TestRenforceState {

    private IContext context;
    private Turn turn;
    private Territory ownTerritory;
    private Territory neighbor;

    @BeforeEach
    void setUp() {

        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Map map = new Map("name");

        turn = new Turn(new ArrayList<>(List.of(p1, p2)));

        ownTerritory = new Territory("test", p1);
        neighbor = new Territory("neighbor", p1);
        turn.nextTurn();
        p1.setReinforce(1);
        p2.setReinforce(1);
        p1.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, map))));
        p2.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, map))));
        context = new MockContextClass(turn, map, null);
        context.setState(new RenforceState(context));

    }

    @Test
    void testAttack_shouldThrowIllegalStateException() {

        IState renforceState = context.getState();

        assertThrows(IllegalStateException.class, () -> renforceState.attack(ownTerritory, neighbor, 1));
    }

    @Test
    void testMove_shouldThrowIllegalStateException() {

        IState renforceState = context.getState();

        assertThrows(IllegalStateException.class, () -> renforceState.move(ownTerritory, neighbor, 1));
    }

    @Test
    void testEndTurn_shouldThrowIllegalStateException() {

        IState renforceState = context.getState();

        Player p1 = turn.getCurrentPlayer();

        renforceState.endTurn();

        assertNotEquals(p1, turn.getCurrentPlayer());
    }

    @Test
    void testRenforce_shouldChangeTurn() {

        IState renforceState = context.getState();

        Player p1 = turn.getCurrentPlayer();

        p1.setReinforce(1);

        renforceState.placeReinforcements(ownTerritory, 1);

        assertEquals(ActionState.class, context.getState().getClass());

    }

}
