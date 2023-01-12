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

}
