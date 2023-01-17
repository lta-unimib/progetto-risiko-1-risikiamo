package com.project.progettorisikorisikiamobackend.TestTurno;

//jupiter
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.player.Player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class TestTurn {
    @Test
    void testNextTurn() {

        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Player 1"));
        playerList.add(new Player("Player 2"));
        playerList.add(new Player("Player 3"));

        Turn turn = new Turn(playerList);

        turn.nextTurn();

        assertEquals(playerList.get(0), turn.getCurrentPlayer());

        turn.nextTurn();

        assertEquals(playerList.get(1), turn.getCurrentPlayer());

        turn.nextTurn();

        assertEquals(playerList.get(2), turn.getCurrentPlayer());
    }

    @Test
    void testIsTurnOfPlayer() {

        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        playerList.add(player1);
        playerList.add(player2);

        Turn turn = new Turn(playerList);
        turn.nextTurn();

        assertTrue(turn.isTurnOfPlayer(player1));

        turn.nextTurn();

        assertFalse(turn.isTurnOfPlayer(player1));

        assertTrue(turn.isTurnOfPlayer(player2));
    }

    @Test
    void testSetPlayerRandomOrder() {
        Player player1 = new Player("Player 1", "red", null, null, "1");
        Player player2 = new Player("Player 2", "blue", null, null, "2");
        Player player3 = new Player("Player 3", "green", null, null, "3");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        Turn turn = new Turn(playerList);

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
        Player player1 = new Player("Player 1", "red", null, "1");
        Player player2 = new Player("Player 2", "blue", null, "2");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        Turn turn = new Turn(playerList);

        turn.defeatPlayer(player1, player2);
        assertEquals(1, turn.getInGamePlayerList().size());
        assertEquals(1, turn.getDefeatedPlayerList().size());
        assertEquals(player2, turn.getDefeatedPlayerList().get(0).getLeft());
        assertEquals(player1, turn.getDefeatedPlayerList().get(0).getRight());

        turn.nextTurn();
        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(player1, player2));
        assertEquals(player1, turn.getWinner());
    }

    @Test
    void testNextTurn2() {
        Player player1 = new Player("Player 1", "red", null, "1");
        Player player2 = new Player("Player 2", "blue", null, "2");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        Turn turn = new Turn(playerList);

        turn.nextTurn();
        assertEquals(player1, turn.getCurrentPlayer());
        assertEquals(1, turn.getTurnNumberInGame());
        assertEquals(1, turn.getTurnNumber());

        turn.defeatPlayer(player2, player2);
        turn.nextTurn();
        assertEquals(player1, turn.getCurrentPlayer());
        assertEquals(1, turn.getTurnNumber());

        assertThrows(IllegalArgumentException.class, () -> turn.defeatPlayer(player1, player2));

        assertEquals(player2, turn.getDefeatedBy(player2));

    }

    @Test
    void testAddPlayer() {
        Player player1 = new Player("Player 1", "red", null, "1");
        Player player2 = new Player("Player 2", "blue", null, "2");
        Turn turn = new Turn();
        turn.addPlayer(player1);
        turn.addPlayer(player2);

        turn.nextTurn();
        assertThrows(IllegalArgumentException.class, () -> turn.addPlayer(player1));
        assertEquals(player1, turn.getCurrentPlayer());
        assertEquals(2, turn.getInGamePlayerList().size());

    }

    @Test
    void setTurnNumberInGame() {
        Player player1 = new Player("Player 1", "red", null, "1");
        Player player2 = new Player("Player 2", "blue", null, "2");
        Turn turn = new Turn();
        turn.addPlayer(player1);
        turn.addPlayer(player2);

        turn.nextTurn();

        turn.setTurnNumberInGame(4);
        assertEquals(4, turn.getTurnNumberInGame());
        assertEquals(6, turn.getTurnNumber());

        turn.nextTurn();

        assertEquals(4, turn.getTurnNumberInGame());
        assertEquals(7, turn.getTurnNumber());

    }

    @Test
    void setWinTest() {
        Player player1 = new Player("Player 1", "red", null, "1");
        Player player2 = new Player("Player 2", "blue", null, "2");
        Turn turn = new Turn();
        turn.addPlayer(player1);
        turn.addPlayer(player2);

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

}