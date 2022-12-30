package com.project.progettorisikorisikiamobackend.TestTurno;



//jupiter
import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.Turno.Dice;
import com.project.progettorisikorisikiamobackend.Turno.iPlayerPlaceHolder;

import static org.junit.Assert.assertEquals;

import java.util.*;

class TestTurn {
   @Test
   //test Constructor
    public void testConstructor(){
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
         assertEquals(0, t.getTurnNumber());
         assertEquals(0, t.getDice());
    }
    //test setTurnNumber
    @Test
    public void testSetTurnNumber(){
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
    //test setDice
    @Test
    public void testSetDice(){
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
        assertEquals(1, t.getDice());
        t.setDice(2);
        assertEquals(2, t.getDice());
        t.setDice(3);
        assertEquals(3, t.getDice());
        t.setDice(4);
        assertEquals(4, t.getDice());
        t.setDice(5);
        assertEquals(5, t.getDice());
        t.setDice(6);
        assertEquals(6, t.getDice());
    }
    //playerListInGame
    @Test
    public void testPlayersInGame(){
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
        assertEquals(4, playerListInGame.size() );
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
    //winningPlayer
    @Test
    public void testWinningPlayer(){
        
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
        assertEquals(null, winningPlayer );
        p1.setIsIngame(false);
        winningPlayer = t.winner(playerList);
        assertEquals(null, winningPlayer );
        p2.setIsIngame(false);
        winningPlayer = t.winner(playerList);
        assertEquals(null, winningPlayer);
        p3.setIsIngame(false);
        winningPlayer = t.winner(playerList);
        assertEquals(null, winningPlayer);
    }
    //nextPlayer
    @Test
    public void testNextPlayer(){
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
        nextPlayer =t.getNextPlayer();
        assertEquals(p3, nextPlayer);
        t.goHeadTurn();
       
        nextPlayer = t.getNextPlayer();
        assertEquals(p4, nextPlayer);
    }
   
}
