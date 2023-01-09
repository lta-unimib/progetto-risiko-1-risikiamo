package com.project.progettorisikorisikiamobackend.TestTurno;

//jupiter
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.*;

import static org.junit.Assert.assertEquals;


import java.util.*;

class TestTurn {
    @Test
    //controllo che il costruttore funzioni
    void testTurn() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3);
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
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3);
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
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3);
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
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3);
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
         PlayerPlaceHolder p1 = new PlayerPlaceHolder("pippo", "red", 1, 0, 1);
         PlayerPlaceHolder p2 = new PlayerPlaceHolder("pluto", "blue", 1, 0, 2);
         PlayerPlaceHolder p3 = new PlayerPlaceHolder("paperino", "green", 1, 0, 3);
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
    






}