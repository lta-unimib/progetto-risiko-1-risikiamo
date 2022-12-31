package com.project.progettorisikorisikiamobackend.TestTurno;

//jupiter
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.Turno.Dice;

import static org.junit.Assert.assertEquals;

import java.util.*;

class TestTurn {
    @Test
    // test Constructor
    public void testConstructor() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        assertEquals(4, t.getPlayerList().size());
        assertEquals(1, t.getTurnNumber());
        assertEquals(6, t.getDiceSides());
    }

    // test setTurnNumber
    @Test
    public void testSetTurnNumber() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
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

    // test setDice
    @Test
    public void testSetDice() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        t.setDice(1);
        assertEquals(1, t.getDiceSides());
        t.setDice(2);
        assertEquals(2, t.getDiceSides());
        t.setDice(3);
        assertEquals(3, t.getDiceSides());
        t.setDice(4);
        assertEquals(4, t.getDiceSides());
        t.setDice(5);
        assertEquals(5, t.getDiceSides());
        t.setDice(6);
        assertEquals(6, t.getDiceSides());
    }

    // test setPlayerList
    @Test
    public void testSetPlayerList() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        List<PlayerPlaceHolder> playerList2 = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p5 = new PlayerPlaceHolder("p5", "red", true, 0, 0, 0);
        PlayerPlaceHolder p6 = new PlayerPlaceHolder("p6", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p7 = new PlayerPlaceHolder("p7", "green", true, 0, 0, 0);
        PlayerPlaceHolder p8 = new PlayerPlaceHolder("p8", "yellow", true, 0, 0, 0);
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
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
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
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        t.setPlayerOrder(playerList, new Dice(6));
        assertEquals(4, t.getPlayerList().size());

    }

    // test getDice
    @Test
    public void testGetDice() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        Dice d = new Dice(6);
        t.setDice(d);
        assertEquals(d, t.getDice());
    }

    // playerListInGame
    @Test
    public void testPlayersInGame() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        List<PlayerPlaceHolder> playerListInGame = t.playersInGame(playerList);
        assertEquals(4, playerListInGame.size());
        p1.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(3, playerListInGame.size());
        p2.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(2, playerListInGame.size());
        p3.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(1, playerListInGame.size());
        p4.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(0, playerListInGame.size());
    }

    // winningPlayer
    @Test
    public void testWinningPlayer() {

        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);

        PlayerPlaceHolder winningPlayer = t.winner(playerList);
        assertEquals(null, winningPlayer);
        p1.setIsIngame(false);
        winningPlayer = t.winner(playerList);
        assertEquals(null, winningPlayer);
        p2.setIsIngame(false);
        winningPlayer = t.winner(playerList);
        assertEquals(null, winningPlayer);
        p3.setIsIngame(false);
        winningPlayer = t.winner(playerList);
        assertEquals(null, winningPlayer);
    }

    // nextPlayer
    @Test
    public void testNextPlayer() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        Turn t = new Turn(playerList);
        PlayerPlaceHolder nextPlayer = t.getNextPlayer();
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
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
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

    // constructor PlayerPlaceHolder
    @Test
    public void testConstructor1() {
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        assertEquals("p1", p1.getPlayerName());
        assertEquals("red", p1.getColor());
        assertEquals(true, p1.getIsIngame());
        assertEquals(0, p1.getObiettivo());
        assertEquals(0, p1.getStatoObiettivo());

    }

    // constructor PlayerPlaceHolder default
    @Test
    public void testConstructor1Default() {
        PlayerPlaceHolder p1 = new PlayerPlaceHolder();
        assertEquals("player", p1.getPlayerName());
        assertEquals("white", p1.getColor());
        assertEquals(false, p1.getIsIngame());

    }

    // constructor
    @Test
    public void testConstructor2() {
        List<PlayerPlaceHolder> playerList = new ArrayList<PlayerPlaceHolder>();
        PlayerPlaceHolder p1 = new PlayerPlaceHolder("p1", "red", true, 0, 0, 0);
        PlayerPlaceHolder p2 = new PlayerPlaceHolder("p2", "blue", true, 0, 0, 0);
        PlayerPlaceHolder p3 = new PlayerPlaceHolder("p3", "green", true, 0, 0, 0);
        PlayerPlaceHolder p4 = new PlayerPlaceHolder("p4", "yellow", true, 0, 0, 0);
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        try {
            Turn t = new Turn(playerList, new Dice(6), 0);
        } catch (Exception e) {
            System.out.println("Exception");
        }

    }

}
