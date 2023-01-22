package com.project.progettorisikorisikiamobackend.TestObiettivi;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TestOpponentDefeated {
    // Test OpponentDefeated
    @Test
    public void testOpponentDefeated() {
        Player player1 = new Player("player1", null, null, "0");
        Player player2 = new Player("player2", null, null, "0");
        OpponentDefeated obj = new OpponentDefeated(player2, null, new Map("mappa"));
        assertEquals("Sconfiggi il giocatore player2", obj.getObjDescription());
        assertTrue(!obj.isCompleted(player1));

    }

    @Test
    public void testOpponentDefeated2() {
        Player player1 = new Player("player1", "null", null, "0");
        Player player2 = new Player("player2", "x", null, "0");
        Turn turn = new Turn();
        turn.addPlayer(player1);
        turn.addPlayer(player2);
        OpponentDefeated obj = new OpponentDefeated(player2, turn, new Map("mappa"));
        assertEquals("Sconfiggi il giocatore player2", obj.getObjDescription());
        turn.defeatPlayer(player1, player2);
        assertTrue(obj.isCompleted(player1));
    }
}
