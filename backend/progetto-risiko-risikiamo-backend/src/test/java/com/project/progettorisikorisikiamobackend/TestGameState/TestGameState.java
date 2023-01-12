//package
package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.gameState.AttackState;
import com.project.progettorisikorisikiamobackend.gameState.EndTurnState;
import com.project.progettorisikorisikiamobackend.gameState.GameStateManager;
import com.project.progettorisikorisikiamobackend.gameState.MoveState;
import com.project.progettorisikorisikiamobackend.gameState.RenforceState;
import com.project.progettorisikorisikiamobackend.gameState.StartState;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.player.Player;

class TestGameState {
    // test gameStateManager
    @Test
    void testGameStateManager() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("pippo", "rosso", null, "123"));
        players.add(new Player("pluto", "rosso", null, "123"));
        GameStateManager gameStateManager = new GameStateManager(players);
        assertEquals("StartState", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());

    }

    // test startState
    @Test
    void testStartState() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("pippo", "rosso", null, "123"));
        players.add(new Player("pluto", "verde", null, "123"));
        GameStateManager gameStateManager = new GameStateManager(players);
        assertEquals("StartState", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
    }

    // test moveState
    @Test
    void testchangeState() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("pippo", "rosso", null, "123"));
        players.add(new Player("pluto", "verde", null, "123"));
        GameStateManager gameStateManager = new GameStateManager(players);
        try {
            gameStateManager.changeState("sposta");
        } catch (Exception e) {
            System.out.println("errore");
        }
        assertEquals("sposta", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
        try {
            gameStateManager.changeState("rinforza");
        } catch (Exception e) {
            System.out.println("errore");
        }
        assertEquals("rinforza", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
        try {
            gameStateManager.changeState("attacca");
        } catch (Exception e) {
            System.out.println("errore");
        }
        assertEquals("attacca", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
        try {
            gameStateManager.changeState("fineTurno");
        } catch (Exception e) {
            System.out.println("errore");
        }
        assertEquals("fineTurno", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
        try {
            gameStateManager.changeState("ciao");
        } catch (Exception e) {
            System.out.println("errore");
        }
        assertEquals("fineTurno", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
    }

    @Test
    public void CallingState() {

        List<Player> players = new ArrayList<>();
        players.add(new Player("pippo", "rosso", null, "123"));
        players.add(new Player("pluto", "verde", null, "123"));
        GameStateManager gameStateManager = new GameStateManager(players);

        AttackState attackState = new AttackState(gameStateManager, "attacca", null);
        assertEquals("attacca", attackState.getNameState());
        attackState.attack();
        attackState.endTurn();
        attackState.move();
        attackState.renforce();

        EndTurnState endTurnState = new EndTurnState(gameStateManager, "fineTurno", null);
        assertEquals("fineTurno", endTurnState.getNameState());
        endTurnState.attack();
        endTurnState.endTurn();
        endTurnState.move();
        endTurnState.renforce();

        MoveState moveState = new MoveState(gameStateManager, "sposta", null);
        assertEquals("sposta", moveState.getNameState());
        moveState.attack();
        moveState.endTurn();
        moveState.move();
        moveState.renforce();
        moveState.attack();

        RenforceState renforceState = new RenforceState(gameStateManager, "rinforza", null);
        assertEquals("rinforza", renforceState.getNameState());
        renforceState.attack();
        renforceState.endTurn();
        renforceState.move();
        renforceState.renforce();

        StartState startState = new StartState(gameStateManager, "StartState", null, new Map("mappa"));
        assertEquals("StartState", startState.getNameState());
        startState.attack();
        startState.endTurn();
        startState.move();
        startState.renforce();

    }

}