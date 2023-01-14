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

        assertEquals(playerList.get(0), turn.getCurrentPlayer());

        turn.nextTurn();

        assertEquals(playerList.get(1), turn.getCurrentPlayer());

        turn.nextTurn();

        assertEquals(playerList.get(2), turn.getCurrentPlayer());
    }

    @Test
    void testSetStatusPlayer() {

        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        playerList.add(player1);
        playerList.add(player2);

        Turn turn = new Turn(playerList);
        turn.setStatusPlayer(player1, EnumTurn.WIN);

        assertEquals(EnumTurn.WIN, turn.getPlayerList().get(player1));

        assertEquals(EnumTurn.LOST, turn.getPlayerList().get(player2));
    }

    @Test
    void testIsTurnOfPlayer() {

        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        playerList.add(player1);
        playerList.add(player2);

        Turn turn = new Turn(playerList);

        assertTrue(turn.isTurnOfPlayer(player1));

        turn.nextTurn();

        assertFalse(turn.isTurnOfPlayer(player1));

        assertTrue(turn.isTurnOfPlayer(player2));
    }

    @Test
    void testSetPlayerRandomOrder() {
        Player player1 = new Player("Player 1", "red", null, "1");
        Player player2 = new Player("Player 2", "blue", null, "2");
        Player player3 = new Player("Player 3", "green", null, "3");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        Turn turn = new Turn(playerList);

        ArrayList<Player> expected = new ArrayList<>(turn.getPlayerList().keySet());
        for (int i = 0; i < 100; i++) {
            turn.setPlayerRandomOrder();
            if (!expected.equals(new ArrayList<>(turn.getPlayerList().keySet())))
                break;
        }
        assertEquals(expected, (new ArrayList<>(turn.getPlayerList().keySet())));

    }

    @Test
    void testSetStatusPlayer2() {
        Player player1 = new Player("Player 1", "red", null, "1");
        Player player2 = new Player("Player 2", "blue", null, "2");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        Turn turn = new Turn(playerList);

        turn.setStatusPlayer(player1, EnumTurn.DEFETED);
        HashMap<Player, EnumTurn> expected = new HashMap<>();
        expected.put(player1, EnumTurn.DEFETED);
        expected.put(player2, EnumTurn.INGAME);
        assertEquals(expected, turn.getPlayerList());

        assertThrows(IllegalArgumentException.class,
                () -> turn.setStatusPlayer(new Player("Player 3", "green", null, "3"), EnumTurn.DEFETED));
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
        assertEquals(player2, turn.getCurrentPlayer());
        assertEquals(1, turn.getTurnNumber());

        turn.setStatusPlayer(player2, EnumTurn.DEFETED);
        turn.nextTurn();
        assertEquals(player1, turn.getCurrentPlayer());
        assertEquals(2, turn.getTurnNumber());

        turn.setStatusPlayer(player1, EnumTurn.DEFETED);
        assertThrows(IllegalStateException.class, turn::nextTurn);
    }

}