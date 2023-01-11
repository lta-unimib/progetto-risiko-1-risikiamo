package com.project.progettorisikorisikiamobackend.TestTurno;

//jupiter
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.*;

import static org.junit.Assert.assertEquals;

import java.util.*;

class TestTurn {
    @Test
    // test Constructor
    public void testConstructor() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        assertEquals(4, t.getPlayerList().size());
        assertEquals(1, t.getTurnNumber());
        
    }

    // test setTurnNumber
    @Test
    public void testSetTurnNumber() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        t.setTurnNumber(1);
        assertEquals(1, t.getTurnNumber());
        t.setTurnNumber(2);
        assertEquals(2, t.getTurnNumber());
        t.setTurnNumber(3);
        assertEquals(3, t.getTurnNumber());
        t.setTurnNumber(4);
        assertEquals(4, t.getTurnNumber());
        t.setTurnNumber(5);
        assertEquals(5, t.getTurnNumber());
        t.setTurnNumber(6);
        assertEquals(6, t.getTurnNumber());
        t.setTurnNumber(7);
        assertEquals(7, t.getTurnNumber());
        t.setTurnNumber(8);
        assertEquals(8, t.getTurnNumber());
        t.setTurnNumber(9);
        assertEquals(9, t.getTurnNumber());
        t.setTurnNumber(10);
        assertEquals(10, t.getTurnNumber());
        t.setTurnNumber(11);
        assertEquals(11, t.getTurnNumber());
        t.setTurnNumber(12);
        assertEquals(12, t.getTurnNumber());
        t.setTurnNumber(13);
        assertEquals(13, t.getTurnNumber());
        t.setTurnNumber(14);
        assertEquals(14, t.getTurnNumber());
        t.setTurnNumber(15);
        assertEquals(15, t.getTurnNumber());
        t.setTurnNumber(16);
        assertEquals(16, t.getTurnNumber());
        t.setTurnNumber(17);
        assertEquals(17, t.getTurnNumber());
        t.setTurnNumber(18);
        assertEquals(18, t.getTurnNumber());
    }

    

    // test setPlayerList
    @Test
    public void testSetPlayerList() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        List<Player> playerList2 = new ArrayList<Player>();
        Player p5 = new Player("p5", "red", 0 ,0, 0, 0);
        Player p6 = new Player("p6", "blue", 0, 0, 0, 0);
        Player p7 = new Player("p7", "green", 0, 0, 0, 0);
        Player p8 = new Player("p8", "yellow", 0, 0, 0, 0);
        playerList2.add(p5);
        playerList2.add(p6);
        playerList2.add(p7);
        playerList2.add(p8);
        t.setPlayerList(playerList2);
        assertEquals(4, t.getPlayerList().size());
    }

    // test setcurrentPlayer
    @Test
    public void testSetCurrentPlayer() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        t.setCurrentPlayer(p1);
        assertEquals(p1, t.getCurrentPlayer());
        t.setCurrentPlayer(p2);
        assertEquals(p2, t.getCurrentPlayer());
        t.setCurrentPlayer(p3);
        assertEquals(p3, t.getCurrentPlayer());
        t.setCurrentPlayer(p4);
        assertEquals(p4, t.getCurrentPlayer());
    }

    // setPlayerOrder
    @Test
    public void testSetPlayerOrder() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        t.setPlayerOrder(playerList, new Dice(6));
        assertEquals(4, t.getPlayerList().size());

    }

   
    

    // winningPlayer
    @Test
    public void testWinningPlayer() {

        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        t.setdefeatedPlayer(p1);
        t.setdefeatedPlayer(p2);
        t.setdefeatedPlayer(p3);
        Player winningPlayer = t.winner(playerList);
        assertEquals(p4, winningPlayer);
        
    }

    // nextPlayer
    @Test
    public void testNextPlayer() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        Player nextPlayer = t.getNextPlayer();
        assertEquals(p2, nextPlayer);
        t.goHeadTurn();
        nextPlayer = t.getNextPlayer();
        assertEquals(p3, nextPlayer);
        t.goHeadTurn();

        nextPlayer = t.getNextPlayer();
        assertEquals(p4, nextPlayer);
    }

    // goHeadTurn
    @Test
    public void testGoHeadTurn() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        t.goHeadTurn();
        assertEquals(p2, t.getCurrentPlayer());
        t.goHeadTurn();
        assertEquals(p3, t.getCurrentPlayer());
        t.goHeadTurn();
        assertEquals(p4, t.getCurrentPlayer());
        t.goHeadTurn();
        assertEquals(p1, t.getCurrentPlayer());

    }

    // constructor Player
    @Test
    public void testConstructor1() {
        Player p1 = new Player("p1", "red", 0, 0, 0, 25);
        assertEquals("p1", p1.getPlayerName());
        assertEquals("red", p1.getColor());
        assertEquals(25, p1.getArmies());
        assertEquals(0, p1.getObiettivo());
        assertEquals(0, p1.getStatoObiettivo());

    }

    // constructor Player default
    @Test
    public void testConstructor1Default() {
        Player p1 = new Player();
        assertEquals("", p1.getPlayerName());
        assertEquals("", p1.getColor());
        assertEquals(0, p1.getObiettivo());
        assertEquals(0, p1.getStatoObiettivo());
        assertEquals(25, p1.getArmies());
        assertEquals(0, p1.getPlayerId());
        

    }

    // constructor
    /**
     * testo costruttore
     */
    @Test
    public void testConstructor2() {
        List<Player> playerList = new ArrayList<Player>();
        Player p1 = new Player("p1", "red", 0 ,0, 0, 0);
        Player p2 = new Player("p2", "blue", 0, 0, 0, 0);
        Player p3 = new Player("p3", "green", 0, 0, 0, 0);
        Player p4 = new Player("p4", "yellow", 0, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        try {
            Turn t = new Turn(playerList,1);
            Dice d = new Dice(6);
            assertEquals(d.getSides(), t.getD().getSides());
            assertEquals(p1, t.getPlayerList().get(0));
            assertEquals(4, t.getPlayerList().size());
            assertEquals(1, t.getTurnNumber());
        } catch (Exception e) {
            System.out.println("Exception");
           
        }
        

    }

}
