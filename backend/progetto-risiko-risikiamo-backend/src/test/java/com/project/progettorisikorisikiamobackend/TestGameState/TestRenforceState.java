package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        turn = new Turn(List.of(p1, p2));

        ownTerritory = new Territory("test", p1);
        neighbor = new Territory("neighbor", p1);
        turn.nextTurn();
        context = new MockContextClass(turn, new Map("name"), null);
        context.setState(new RenforceState(context));

    }

    @Test
    void testAttack_shouldThrowUnsupportedOperationException() {

        IState renforceState = context.getState();

        assertThrows(UnsupportedOperationException.class, () -> renforceState.attack(ownTerritory, neighbor, 1));
    }

    @Test
    void testMove_shouldThrowUnsupportedOperationException() {

        IState renforceState = context.getState();

        assertThrows(UnsupportedOperationException.class, () -> renforceState.move(ownTerritory, neighbor, 1));
    }

    @Test
    void testEndTurn_shouldThrowUnsupportedOperationException() {

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
