//package
package com.project.progettorisikorisikiamobackend.TestGameState;
import org.junit.jupiter.api.Test;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.gameState.*;
import com.project.progettorisikorisikiamobackend.map.Map;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.*;

class TestGameState {
    //test gameStateManager
    @Test
    void testGameStateManager() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        players.add(new PlayerPlaceHolder("pippo", "rosso", true, 0, 0,0, new Dice(6)));
        players.add(new PlayerPlaceHolder("pluto", "verde", true, 0, 0,0, new Dice(6)));
        GameStateManager gameStateManager = new GameStateManager(players);
        assertEquals("StartState", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());

    }
    //test startState
    @Test
    void testStartState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        players.add(new PlayerPlaceHolder("pippo", "rosso", true, 0, 0,0, new Dice(6)));
        players.add(new PlayerPlaceHolder("pluto", "verde", true, 0, 0,0, new Dice(6)));
        GameStateManager gameStateManager = new GameStateManager(players);
        gameStateManager.getGameState().rinforza();
        assertEquals("StartState", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
    }
    //test moveState
    @Test
    void testMoveState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        players.add(new PlayerPlaceHolder("pippo", "rosso", true, 0, 0,0));
        players.add(new PlayerPlaceHolder("pluto", "verde", true, 0, 0,0));
       GameStateManager gameStateManager = new GameStateManager(players);
       try{
        gameStateManager.changeState("sposta");
       } catch (Exception e) {
           System.out.println("errore");
         }
        assertEquals("sposta", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
    }
    //test attackState
    @Test
    void testAttackState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        players.add(new PlayerPlaceHolder("pippo", "rosso", true, 0, 0,0));
        players.add(new PlayerPlaceHolder("pluto", "verde", true, 0, 0,0));
       GameStateManager gameStateManager = new GameStateManager(players);
       try{
        gameStateManager.changeState("attacca");
       } catch (Exception e) {
           System.out.println("errore");
         }
        assertEquals("attacca", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
    }
    //test renforceState
    @Test
    void testRenforceState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        players.add(new PlayerPlaceHolder("pippo", "rosso", true, 0, 0,0));
        players.add(new PlayerPlaceHolder("pluto", "verde", true, 0, 0,0));
       GameStateManager gameStateManager = new GameStateManager(players);
       try{
        gameStateManager.changeState("rinforza");
       } catch (Exception e) {
           System.out.println("errore");
         }
        assertEquals("rinforza", gameStateManager.getGameState().getNameState());
        assertEquals(players, gameStateManager.getGameState().getTurno().getPlayerList());
    }

}