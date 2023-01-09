package com.project.progettorisikorisikiamobackend.TestTurno;

//jupiter
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import com.project.progettorisikorisikiamobackend.Turno.*;

import static org.junit.Assert.assertEquals;


import java.util.*;

class TestTurn {
    @Test
    //controllo che il costruttore funzioni
    void testTurn() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 25);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3, 25);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        Turn t = new Turn(playerList);
        assertEquals(t.getPlayerList(), playerList);
        assertEquals(1, t.getTurnNumber());
        assertEquals(p1, t.getCurrentPlayer());
        assertEquals(p2, t.getNextPlayer());
    }
  
    @Test
    //controllo playersInGame funzioni
    void testPlayersInGame() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 25);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3, 25);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        Turn t = new Turn(playerList);
        t.playersInGame(playerList);
        assertEquals(3, t.getPlayerList().size());
    }
    @Test
    //controllo setCurrentPlayer funzioni
    void testSetCurrentPlayer() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 25);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3, 25);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        Turn t = new Turn(playerList);
        t.setCurrentPlayer(p3);
        assertEquals(t.getCurrentPlayer(), p3);
    }
    @Test
    //controllo setNextPlayer funzioni
    void testSetNextPlayer() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 25);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3, 25);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        Turn t = new Turn(playerList);
        t.setNextPlayer(playerList);
        assertEquals(t.getNextPlayer(), p3);
    }
   @Test
   //controllo winner funzioni
    void testWinner() {
         List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
         PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 25);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3, 25);
         playerList.add(p1);
         playerList.add(p2);
         playerList.add(p3);
         Turn t = new Turn(playerList);
         assertEquals(null, t.winner(playerList)) ;
         t.getPlayerList().remove(t.getPlayerList().get(0));
         t.getPlayerList().remove(t.getPlayerList().get(1));
         PlayerPlaceHolder winner = t.winner(playerList);
            assertEquals(p2, winner);
    }
    @Test
    //controllo goHeadTurn funzioni
    void testGoHeadTurn() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 25);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3, 25);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        Turn t = new Turn(playerList);
        t.goHeadTurn();
        assertEquals(p2, t.getCurrentPlayer() );
        assertEquals(p3, t.getNextPlayer());
    }
    @Test
    //controllo winning condition 
    void testWinningCondition(){
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 1, 3, 25);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        Turn t = new Turn(playerList);
       t.winningCondition(playerList);
        assertEquals(1, t.getPlayerList().size());

    }
    @Test
    //controllo costruttore2 funzioni
    void TestConstructor(){
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2, 25);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3, 25);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        Dice d = new Dice(6);
        Turn t = new Turn(playerList, d, 0);
        assertEquals(0, t.getTurnNumber());
        assertEquals(p1, t.getCurrentPlayer());
        assertEquals(p2, t.getNextPlayer());

    }
    @Test
    // controllo playerPlaceHolder
    void testPlayerPlaceHolder(){
        PlayerPlaceHolder p = new PlayerPlaceHolder("pippo", "red", 1, 0, 1, 25);
        assertEquals("pippo", p.getPlayerName());
        assertEquals("red", p.getColor());
        assertEquals(1, p.getObiettivo());
        assertEquals(0, p.getStatoObiettivo());
        assertEquals(1, p.getPlayerId());
        assertEquals(25, p.getArmies());
       
    }
    @Test
    //controllo playerPlaceHolder2
    void testPlayerPlaceHolder2(){
        PlayerPlaceHolder p = new PlayerPlaceHolder();
        
        assertEquals("", p.getPlayerName());
        assertEquals("", p.getColor());
        assertEquals(0, p.getObiettivo());
        assertEquals(0, p.getStatoObiettivo());
        assertEquals(0, p.getPlayerId());
        assertEquals(25, p.getArmies());
    }

    






}