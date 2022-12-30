package com.project.progettorisikorisikiamobackend;
import java.util.*;



public class Turn {
   private List <PlayerPlaceHolder> playerList;
    private PlayerPlaceHolder currentPlayer;
    private PlayerPlaceHolder nextPlayer;
    private int turnNumber;
     private Dice d = new Dice(6);
    
    //costruttore
    public Turn(List <PlayerPlaceHolder> playerList, Dice d,  int turnNumber){
        this.d = d;
        setPlayerList(playerList);
        this.turnNumber = turnNumber;
        this.currentPlayer = playerList.get(turnNumber);
        this.nextPlayer = playerList.get(turnNumber + 1);
       
        
        
    }
    public Turn(List <PlayerPlaceHolder> playerList){
        this.playerList = playerList;
        this.d = new Dice(6);
        this.currentPlayer = playerList.get(0); 
        this.turnNumber = 1;
        this.nextPlayer = playerList.get(1);
       
    }
    //ordina i giocatori in base al lancio del dado
    public List<PlayerPlaceHolder> setPlayerOrder(List<PlayerPlaceHolder> playerList, Dice d){
        int rollMax = 0;
       
     for(int i = 0; i < playerList.size(); i++){
        d.roll();
        if(d.getValue() > rollMax){
            rollMax = d.getValue();
        }
        playerList.get(i).setPlayerId(d.getValue());


     }
     
        
     playerList.sort(Comparator.comparing(PlayerPlaceHolder::getPlayerId).reversed());
     return playerList;
    }
    //get dice
    public Dice getDice(){
        return this.d;
    }
    
    public void setPlayerList(List <PlayerPlaceHolder> playerList){
        this.playerList = playerList;
    }
    public List <PlayerPlaceHolder> getPlayerList(){
        return this.playerList;
    }
    
   
    //ritorna la lista dei giocatori ancora in gioco
    public List <PlayerPlaceHolder> playersInGame(List<PlayerPlaceHolder>playerList){
       List<PlayerPlaceHolder> playerListInGame = new ArrayList<>();
        for(PlayerPlaceHolder p : playerList){
            if(p.getIsIngame()){
                playerListInGame.add(p);
            }
        }
        return playerListInGame;
    }
    //ritorna il giocatore corrente
    public PlayerPlaceHolder getCurrentPlayer(){
        return this.currentPlayer;
    }
    //ritorna il giocatore successivo
    public PlayerPlaceHolder getNextPlayer(){
        return this.nextPlayer;
    }
    //ritorna il numero del turno
    public int getTurnNumber(){
        return this.turnNumber;
    }
    //setta il giocatore corrente
    public void setCurrentPlayer(PlayerPlaceHolder currentPlayer){
     if(currentPlayer.getIsIngame())
            this.currentPlayer = currentPlayer;
        else if(nextPlayer != null)
            this.currentPlayer = playerList.get(getTurnNumber() + 1);
        else 
            this.currentPlayer = playerList.get(0);
    }
    //setta il giocatore successivo
    public void setNextPlayer(List <PlayerPlaceHolder> playerList){
        if(getTurnNumber() > playerList.size() - 1)
            this.nextPlayer = playerList.get(0);
        else if(getTurnNumber() < playerList.size() - 1)
            this.nextPlayer = playerList.get(getTurnNumber() + 1);
         
          
    }
    //setta il numero del turno
    public void goHeadTurn(){
        if(getTurnNumber() < playerList.size() - 1 ){
            
            if(getCurrentPlayer().getIsIngame())
                setCurrentPlayer(playerList.get(turnNumber));
                else
                    setCurrentPlayer(playerList.get(turnNumber + 1));
                if(getNextPlayer().getIsIngame())          
                        setNextPlayer(playerList);  
                
    }         
        else if(getTurnNumber() == playerList.size() - 1 )
            this.nextPlayer = playerList.get(playerList.size() - 1);
        
        else {
        setCurrentPlayer(playerList.get(turnNumber));
        setNextPlayer(playerList);
        }
        this.turnNumber = turnNumber + 1;
    }
    //ritorna il vincitore
    public PlayerPlaceHolder winner(List<PlayerPlaceHolder> playerListInGame){
        if(playerListInGame.size() == 1)
            return playerList.get(0);
        else
            return null;
    }
    //dice chi ha vinto se un giocatore raggiunge l'obiettivo e setta gli altri giocatori come fuori gioco
    public void winningCondition(List<PlayerPlaceHolder> playerList){
    PlayerPlaceHolder p1 = nextPlayer;
        for(PlayerPlaceHolder p : playerList){
        
            if(p.getObiettivo() == p.getStatoObiettivo()){
                if(p1 != p)
                    p1.setIsIngame(false);
            } 
            
                
            


        }
       
               
                
            
    }
}


