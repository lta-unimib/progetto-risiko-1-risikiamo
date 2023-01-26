package com.project.progettorisikorisikiamobackend.TestTurno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.obiettivi.TotTerritories;
import com.project.progettorisikorisikiamobackend.player.Player;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class TestTurn {

    private List<Player> playerList;
    private Turn turn;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    void setUp() {
        this.playerList = new ArrayList<>();
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.player3 = new Player("Player 3");
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        Map map = new Map("map");

        player1.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, map))));
        player2.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, map))));
        player3.setObiettivi(new ArrayList<>(List.of(new TotTerritories(10, map))));

        this.turn = new Turn(playerList);
    }

    @Test
    void testNextTurn() {

        turn.nextTurn();

        assertEquals(playerList.get(0), turn.getCurrentPlayer());

        turn.nextTurn();

        assertEquals(playerList.get(1), turn.getCurrentPlayer());

        turn.nextTurn();

        assertEquals(playerList.get(2), turn.getCurrentPlayer());
    }

    @Test
    void testIsTurnOfPlayer() {

        turn.nextTurn();

        assertTrue(turn.isTurnOfPlayer(player1));

        turn.nextTurn();

        assertFalse(turn.isTurnOfPlayer(player1));

        assertTrue(turn.isTurnOfPlayer(player2));
    }

    @Test
    void testSetPlayerRandomOrder() {

        ArrayList<Player> expected = new ArrayList<>(turn.getInGamePlayerList());
        for (int i = 0; i < 100; i++) {
            turn.setPlayerRandomOrder();
            if (!expected.equals(new ArrayList<>(turn.getInGamePlayerList())))
                break;
        }
        // ? qui è un pos strano se sorgono problemi sull'ordinamento dei plaire
        // ? cosiderare qui
        assertNotEquals(expected, new ArrayList<>(turn.getInGamePlayerList()));

    }

    @Test
    void testSetStatusPlayer2() {

        turn.defeatPlayer(player1, player2);
        assertEquals(2, turn.getInGamePlayerList().size());
        assertEquals(1, turn.getDefeatedPlayerList().size());
        assertEquals(player2, turn.getDefeatedPlayerList().get(0).getLeft());
        assertEquals(player1, turn.getDefeatedPlayerList().get(0).getRight());

        turn.nextTurn();
        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(player1, player2));
        assertEquals(null, turn.getWinner());
        turn.defeatPlayer(player1, player3);
        assertEquals(player1, turn.getWinner());

    }

    @Test
    void testNextTurn2() {

        turn.nextTurn();
        assertEquals(player1, turn.getCurrentPlayer());
        assertEquals(1, turn.getTurnNumberInGame());
        assertEquals(1, turn.getTurnNumber());

        turn.defeatPlayer(player2, player2);
        turn.nextTurn();
        assertEquals(player3, turn.getCurrentPlayer());
        assertEquals(2, turn.getTurnNumber());

        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(player1, player2));

        assertEquals(player2, turn.getDefeatedBy(player2));

    }

    @Test
    void testAddPlayer() {

        turn.nextTurn();
        assertThrows(IllegalArgumentException.class, () -> turn.addPlayer(player1));
        assertEquals(player1, turn.getCurrentPlayer());
        assertEquals(3, turn.getInGamePlayerList().size());

    }

    @Test
    void setTurnNumberInGame() {

        turn.nextTurn();

        turn.setTurnNumberInGame(4);
        assertEquals(4, turn.getTurnNumberInGame());
        assertEquals(9, turn.getTurnNumber());

        turn.nextTurn();

        assertEquals(4, turn.getTurnNumberInGame());
        assertEquals(10, turn.getTurnNumber());

    }

    @Test
    void setWinTest() {

        turn.nextTurn();

        turn.setWin(player1);
        assertEquals(player1, turn.getWinner());
        assertEquals(1, turn.getTurnNumberInGame());
        assertEquals(1, turn.getTurnNumber());

        turn.nextTurn();

        assertThrows(IllegalArgumentException.class, () -> turn.setWin(player2));
        assertEquals(player1, turn.getWinner());
        assertEquals(1, turn.getTurnNumberInGame());
        assertEquals(1, turn.getTurnNumber());

    }

    @Test
    void test() {
        Player player1 = new Player("Player 1", "red", null, "1");

        Turn turn = new Turn();

        assertThrows(IllegalArgumentException.class, () -> turn.addPlayer(null));
        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(null, null));
        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(player1, null));
        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(null, player1));
        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(player1, player1));
        assertThrows(IllegalArgumentException.class, () -> turn.setWin(null));
        assertThrows(IllegalArgumentException.class, () -> turn.setTurnNumberInGame(-1));
        assertThrows(IllegalArgumentException.class, () -> turn.setTurnNumberInGame(0));

    }

}