package com.project.progettorisikorisikiamobackend.TestObiettivi;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.project.progettorisikorisikiamobackend.map.*;
import com.project.progettorisikorisikiamobackend.player.*;
import org.junit.jupiter.api.Test;

public class TestOpponentDefeated {
    //Test OpponentDefeated
    @Test
    public void testOpponentDefeated() {
        Player player1 = new Player("player1", null, null, 0);
        OpponentDefeated obj = new OpponentDefeated(player1);
        Player player2 = new Player("player2", null, obj, 1);
        
        assertEquals("Sconfiggi il giocatore player2", obj.getObjDescription());
        assertTrue(obj.isCompleted(player1));
    }
}
