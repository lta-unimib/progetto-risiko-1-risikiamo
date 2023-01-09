package com.project.progettorisikorisikiamobackend.TestTurno;

//jupiter
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.*;

import static org.junit.Assert.assertEquals;

import java.beans.Transient;
import java.util.*;

class TestTurn {
    @Test
    //controllo che il costruttore funzioni
    void testTurn() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        playerList.add(new PlayerPlaceHolder("pippo", "red", 1, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("pluto", "blue", 2, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("paperino", "green", 3, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("topolino", "yellow", 4, 0, 0, 25));
        Turn t = new Turn(playerList, new Dice(6), 0);
        assertEquals(t.getPlayerList(), playerList);
        assertEquals(t.getTurnNumber(), 0);
        assertEquals(t.getCurrentPlayer(), playerList.get(0));
        assertEquals(t.getNextPlayer(), playerList.get(1));
        assertEquals(t.getD().getSides(), 6);
    }
    //controllo setPlayerOrder
    @Test
    void testSetPlayerOrder() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        playerList.add(new PlayerPlaceHolder("pippo", "red", 1, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("pluto", "blue", 2, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("paperino", "green", 3, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("topolino", "yellow", 4, 0, 0, 25));
        Turn t = new Turn(playerList);
        List<PlayerPlaceHolder> playerList2 = new ArrayList<PlayerPlaceHolder>();
        playerList2.add(new PlayerPlaceHolder("pippo", "red", 1, 0, 0, 25));
        playerList2.add(new PlayerPlaceHolder("pluto", "blue", 2, 0, 0, 25));
        playerList2.add(new PlayerPlaceHolder("paperino", "green", 3, 0, 0, 25));
        playerList2.add(new PlayerPlaceHolder("topolino", "yellow", 4, 0, 0, 25));
        assertEquals(t.setPlayerOrder(playerList2, new Dice(6)), playerList);
    }
    //controllo che il metodo nextTurn funzioni
    @Test
    void testNextTurn() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        playerList.add(new PlayerPlaceHolder("pippo", "red", 1, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("pluto", "blue", 2, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("paperino", "green", 3, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("topolino", "yellow", 4, 0, 0, 25));
        Turn t = new Turn(playerList, new Dice(6), 0);
        t.nextTurn();
        assertEquals(t.getTurnNumber(), 1);
        assertEquals(t.getCurrentPlayer(), playerList.get(1));
        assertEquals(t.getNextPlayer(), playerList.get(2));
    }
    //winningPlayer
    @Test
    void testWinningPlayer() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        playerList.add(new PlayerPlaceHolder("pippo", "red", 1, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("pluto", "blue", 2, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("paperino", "green", 3, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("topolino", "yellow", 4, 0, 0, 25));
        Turn t = new Turn(playerList, new Dice(6), 0);
        assertEquals(t.winningPlayer(), null);
        playerList.get(0).setStatoObiettivo(1);
        assertEquals(t.winningPlayer(), playerList.get(0));
    }
    //controllo players in game
    @Test
    void testPlayersInGame() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        playerList.add(new PlayerPlaceHolder("pippo", "red", 1, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("pluto", "blue", 2, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("paperino", "green", 3, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("topolino", "yellow", 4, 0, 0, 25));
        Turn t = new Turn(playerList, new Dice(6), 0);
        assertEquals(t.playersInGame(), 4);
        playerList.get(0).setStatoObiettivo(1);
        assertEquals(t.playersInGame(), 3);
    }
    //controllo che il metodo nextPlayer funzioni
    @Test
    void testNextPlayer() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        playerList.add(new PlayerPlaceHolder("pippo", "red", 1, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("pluto", "blue", 2, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("paperino", "green", 3, 0, 0, 25));
        playerList.add(new PlayerPlaceHolder("topolino", "yellow", 4, 0, 0, 25));
        Turn t = new Turn(playerList, new Dice(6), 0);
        t.nextPlayer();
        assertEquals(t.getCurrentPlayer(), playerList.get(1));
        assertEquals(t.getNextPlayer(), playerList.get(2));
        t.nextPlayer();
        assertEquals(t.getCurrentPlayer(), playerList.get(2));
        assertEquals(t.getNextPlayer(), playerList.get(3));
        t.nextPlayer();
        assertEquals(t.getCurrentPlayer(), playerList.get(3));
        assertEquals(t.getNextPlayer(), playerList.get(0));
    }

}