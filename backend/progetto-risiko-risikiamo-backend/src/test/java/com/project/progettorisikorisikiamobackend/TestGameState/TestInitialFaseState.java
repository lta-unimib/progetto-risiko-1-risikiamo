package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.InitialFaseState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

public class TestInitialFaseState {

    private List<Player> players;
    private Territory ownTerritory;
    private Territory owner;
    private Territory neighbor;
    private Turn turn;

    private IContext context;

    @BeforeEach
    void setUp() {

        Player p1 = new Player("p1");
        p1.setReinforce(4);
        Player p2 = new Player("p2");

        players = List.of(p1, p2);

        ownTerritory = new Territory("test", p1);

        owner = new Territory("owner", p1);
        neighbor = new Territory("neighbor", p1);

        ownTerritory.addNeighbor(neighbor);
        neighbor.addNeighbor(ownTerritory);

        ownTerritory.setOwner(p1);
        neighbor.setOwner(p1);

        turn = new Turn(players);

        context = new mockClass(turn, null);

    }

    @Test
    void testAttack_shouldThrowUnsupportedOperationException() {

        IState initialFaseState = context.getState();

        assertThrows(UnsupportedOperationException.class, () -> initialFaseState.attack(owner, neighbor, 1));
    }

    @Test
    void testMove_shouldThrowUnsupportedOperationException() {
        IState initialFaseState = context.getState();
        assertThrows(UnsupportedOperationException.class, () -> initialFaseState.move(owner, neighbor, 1));
    }

    @Test
    void testPassTurn_shouldThrowUnsupportedOperationException() {
        IState initialFaseState = context.getState();
        assertThrows(UnsupportedOperationException.class, () -> initialFaseState.passTurn());
    }

    @Test
    void testRedeemReinforcementsCard_shouldThrowUnsupportedOperationException() {
        IState initialFaseState = context.getState();
        assertThrows(UnsupportedOperationException.class, () -> initialFaseState.redeemReinforcementsCard());
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

        initialFaseState.placeReinforcements(ownTerritory, 4);
        assertEquals(3, initialFaseState.getReinforcementToPlace());
        assertEquals(4, turn.getCurrentPlayer().getReinforce());
    }

    @Test
    void testPlaceReinforcements_shouldThrowIllegalArgumentException() {

        InitialFaseState initialFaseState = (InitialFaseState) context.getState();
        Player p1 = turn.getCurrentPlayer();
        initialFaseState.placeReinforcements(ownTerritory, 3);

        assertNotEquals(p1, turn.getCurrentPlayer());

    }

    public class mockClass implements IContext {

        Turn turn;

        Map map;
        IState state;

        public mockClass(Turn turn, Map map) {
            this.turn = turn;
            this.map = map;

            this.state = new InitialFaseState(this);
        }

        @Override
        public Turn getTurn() {

            return turn;
        }

        @Override
        public Map getMap() {

            return map;
        }

        @Override
        public void setState(IState state) {
            this.state = state;

        }

        public IState getState() {
            return state;
        }
    }
}
