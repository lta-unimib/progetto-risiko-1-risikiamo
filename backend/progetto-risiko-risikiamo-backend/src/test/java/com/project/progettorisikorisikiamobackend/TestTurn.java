package com.project.progettorisikorisikiamobackend;

//jupiter
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.util.*;

class TestTurn {
   
    //setPlayerOrder
    
    @Test 
    public void testPlayerOrder(){
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
        List<PlayerPlaceHolder> playerOrder = t.setPlayerOrder(playerList);
        assertEquals(playerOrder.get(0).getPlayerName(), "p4");
        assertEquals(playerOrder.get(1).getPlayerName(), "p3");
        assertEquals(playerOrder.get(2).getPlayerName(), "p2");
        assertEquals(playerOrder.get(3).getPlayerName(), "p1");
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
        assertEquals(playerListInGame.size(), 4);
        p1.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(playerListInGame.size(), 3);
        p2.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(playerListInGame.size(), 2);
        p3.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(playerListInGame.size(), 1);
        p4.setIsIngame(false);
        playerListInGame = t.playersInGame(playerList);
        assertEquals(playerListInGame.size(), 0);
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
        
        PlayerPlaceHolder winningPlayer = t.winningPlayer(playerList);
        assertEquals(winningPlayer, null);
        p1.setIsIngame(false);
        winningPlayer = t.winningPlayer(playerList);
        assertEquals(winningPlayer, null);
        p2.setIsIngame(false);
        winningPlayer = t.winningPlayer(playerList);
        assertEquals(winningPlayer, null);
        p3.setIsIngame(false);
        winningPlayer = t.winningPlayer(playerList);
        assertEquals(winningPlayer, p4);
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
        List<PlayerPlaceHolder> playerOrder = t.setPlayerOrder(playerList);
        PlayerPlaceHolder nextPlayer = t.nextPlayer(playerOrder, p4);
        assertEquals(nextPlayer, p3);
        nextPlayer = t.nextPlayer(playerOrder, p3);
        assertEquals(nextPlayer, p2);
        nextPlayer = t.nextPlayer(playerOrder, p2);
        assertEquals(nextPlayer, p1);
        nextPlayer = t.nextPlayer(playerOrder, p1);
        assertEquals(nextPlayer, p4);
    }
    //previusPlayerInGame
    @Test
    public void testPreviusPlayerInGame(){
        
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
        List<PlayerPlaceHolder> playerOrder = t.setPlayerOrder(playerList);
        PlayerPlaceHolder previusPlayerInGame = t.previusPlayerInGame(playerOrder, p4);
        assertEquals(previusPlayerInGame, p1);
        previusPlayerInGame = t.previusPlayerInGame(playerOrder, p3);
        assertEquals(previusPlayerInGame, p4);
        previusPlayerInGame = t.previusPlayerInGame(playerOrder, p2);
        assertEquals(previusPlayerInGame, p3);
        previusPlayerInGame = t.previusPlayerInGame(playerOrder, p1);
        assertEquals(previusPlayerInGame, p2);
    }
    //currentPlayer
    @Test
    public void testCurrentPlayer(){
        
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
        List<PlayerPlaceHolder> playerOrder = t.setPlayerOrder(playerList);
        PlayerPlaceHolder currentPlayer = t.currentPlayer(playerOrder, p4);
        assertEquals(currentPlayer, p4);
        currentPlayer = t.currentPlayer(playerOrder, p3);
        assertEquals(currentPlayer, p3);
        currentPlayer = t.currentPlayer(playerOrder, p2);
        assertEquals(currentPlayer, p2);
        currentPlayer = t.currentPlayer(playerOrder, p1);
        assertEquals(currentPlayer, p1);
    }

}
