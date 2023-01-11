package com.project.progettorisikorisikiamobackend.TestObiettivi;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.project.progettorisikorisikiamobackend.map.*;
import org.junit.jupiter.api.Test;

public class TestOpponentDefeated {
    //Test OpponentDefeated
    @Test
    public void testOpponentDefeated() {
        PlayerPlaceholder player1 = new PlayerPlaceholder("player1");
        PlayerPlaceholder player2 = new PlayerPlaceholder("player2");
        OpponentDefeated obj = new OpponentDefeated(player2);
        assertEquals("Sconfiggi il giocatore player2", obj.getObjDescription());
        assertTrue(obj.isCompleted(player1));
    }
}
