//package
package test.java.com.project.progettorisikorisikiamobackend.TestGameState;
import org.junit.jupiter.api.Test;


import com.project.progettorisikorisikiamobackend.*;

import static org.junit.Assert.assertEquals;

import java.beans.Transient;
import java.util.*;

class TestGameState {
    @Test
    // test constructor
    void testConstructor() {
        GameStatus gameStatus = new GameStatus();
        Turn turno = new Turn();
        GameState gameState = new MoveState(gameStatus, turno);
        assertEquals(gameState.getClass(), MoveState.class);
    }

    @Test 
    // Test per verificare che il cambio di stato funzioni correttamente
    void testChangeState() {
        GameStatus gameStatus = new GameStatus();
        Turn turno = new Turn();
        PlayerPlaceHolder player = turno.getCurrentPlayer();
        GameState gameState = new MoveState(gameStatus, turno);
        gameState.sposta();
        gameState.attacca();
        gameState.rinforza();
        gameState = new AttackState(gameStatus, turno);
        gameState.sposta();
        gameState.attacca();
        gameState.rinforza();
        gameState = new ReinforceState(gameStatus, turno);
        gameState.sposta();
        gameState.attacca();
        gameState.rinforza();
        assertEquals(gameState.getClass(), ReinforceState.class);
    }
    @Test
    //Test verifica stato attacco
    void testAttackState() {
        GameStatus gameStatus = new GameStatus();
        Turn turno = new Turn();
        PlayerPlaceHolder player = turno.getCurrentPlayer();
        GameState gameState = new AttackState(gameStatus, turno);
        gameState.sposta();
        gameState.attacca();
        gameState.rinforza();
        assertEquals(gameState.getClass(), AttackState.class);
    }
    @Test
    //Test verifica stato rinforzo
    void testReinforceState() {
        GameStatus gameStatus = new GameStatus();
        Turn turno = new Turn();
        PlayerPlaceHolder player = turno.getCurrentPlayer();
        GameState gameState = new ReinforceState(gameStatus, turno);
        gameState.sposta();
        gameState.attacca();
        gameState.rinforza();
        assertEquals(gameState.getClass(), ReinforceState.class);
    }
    @Test
    //Test verifica stato spostamento
    void testMoveState() {
        GameStatus gameStatus = new GameStatus();
        Turn turno = new Turn();
        PlayerPlaceHolder player = turno.getCurrentPlayer();
        GameState gameState = new MoveState(gameStatus, turno);
        gameState.sposta();
        gameState.attacca();
        gameState.rinforza();
        assertEquals(gameState.getClass(), MoveState.class);
    }
}
