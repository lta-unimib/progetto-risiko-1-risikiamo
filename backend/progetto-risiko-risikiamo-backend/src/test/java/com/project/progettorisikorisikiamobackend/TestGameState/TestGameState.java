//package
package com.project.progettorisikorisikiamobackend.TestGameState;
import org.junit.jupiter.api.Test;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.gameState.*;
import com.project.progettorisikorisikiamobackend.map.Map;




import static org.junit.Assert.assertEquals;
import java.beans.Transient;
import java.util.*;

class TestGameState {
    @Test
    void test() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder();
        PlayerPlaceHolder p2 = new PlayerPlaceHolder();
        PlayerPlaceHolder p3 = new PlayerPlaceHolder();
        PlayerPlaceHolder p4 = new PlayerPlaceHolder();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        GameStatus gameStatus = new GameStatus(players);
        assertEquals("StartState", gameStatus.getGameState().getNameState());
        gameStatus.setMoveState("MoveState");
        assertEquals("MoveState", gameStatus.getGameState().getNameState());
        gameStatus.setAttackState("AttackState");
        assertEquals("AttackState", gameStatus.getGameState().getNameState());
        gameStatus.setReinforceState("RenforceState");
        assertEquals("RenforceState", gameStatus.getGameState().getNameState());
    }
    /**
     * 
     */
    @Test
    //start state   
    void testStartState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0,new Dice(6));
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Turn turno = new Turn(players);
        Map map = new Map("mappa");
        GameState startState = new StartState("StartState", turno, map);
        assertEquals("StartState", startState.getNameState());
        startState.sposta();
        startState.attacca();
        startState.rinforza();
    }
    @Test
    //move state
    void testMoveState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0,new Dice(6));
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        GameState moveState = new MoveState("MoveState", new Turn(players));
        assertEquals("MoveState", moveState.getNameState());
        moveState.sposta();
        moveState.attacca();
        moveState.rinforza();
    }
    @Test
    //attack state
    void testAttackState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0,new Dice(6));
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        GameState attackState = new AttackState("AttackState", new Turn(players));
        assertEquals("AttackState", attackState.getNameState());
        attackState.sposta();
        attackState.attacca();
        attackState.rinforza();
    }
    @Test
    //reinforce state
    void testReinforceState() {
        List <PlayerPlaceHolder> players = new ArrayList<>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0,new Dice(6));
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0,new Dice(6));
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        GameState renforceState = new RenforceState("RenforceState", new Turn(players));
        assertEquals("RenforceState", renforceState.getNameState());
        renforceState.sposta();
        renforceState.attacca();
        renforceState.rinforza();
    }
}
