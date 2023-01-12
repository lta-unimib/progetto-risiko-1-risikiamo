//package
package com.project.progettorisikorisikiamobackend.TestGameState;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.gameState.GameStateManager;
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

}