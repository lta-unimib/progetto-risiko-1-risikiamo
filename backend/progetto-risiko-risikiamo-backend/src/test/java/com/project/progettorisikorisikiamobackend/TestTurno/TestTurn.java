package com.project.progettorisikorisikiamobackend.TestTurno;

//jupiter
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.util.*;

class TestTurn {
   
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
