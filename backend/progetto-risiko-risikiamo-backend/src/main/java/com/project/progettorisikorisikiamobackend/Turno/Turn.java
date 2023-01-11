package com.project.progettorisikorisikiamobackend.Turno;
import java.util.*;
import lombok.*;


public class Turn {
    private @Getter @Setter List <Player> playerList;
   private  @Getter Player currentPlayer;
   private @Getter  Player nextPlayer;
   private @Getter @Setter int turnNumber;
    private @Getter Dice d;
    
    //costruttore
    public Turn(List <Player> playerList,  int turnNumber){
        this.d = new Dice(6);
        setPlayerList(playerList);
        this.turnNumber = turnNumber;
        this.currentPlayer = playerList.get(turnNumber);
        this.nextPlayer = playerList.get(turnNumber + 1);
       
        
        
    }
    public Turn(List <Player> playerList){
        this.playerList = playerList;
        this.d = new Dice(6);
        this.currentPlayer = playerList.get(0); 
        this.turnNumber = 1;
        this.nextPlayer = playerList.get(1);
       
    }
    
    /**ordina i giocatori in base al lancio del dado
     * 
     * @param playerList lista dei giocatori
     * @param d dado
     * @return lista dei giocatori ordinata in base al lancio del dado
     */
    public List<Player> setPlayerOrder(List<Player> playerList, Dice d){
        int rollMax = 0;
       
     for(int i = 0; i < playerList.size(); i++){
        d.roll();
        if(d.getValue() > rollMax){
            rollMax = d.getValue();
        }
        playerList.get(i).setPlayerId(d.getValue());


     }
     
        
     playerList.sort(Comparator.comparing(Player::getPlayerId).reversed());
     return playerList;
    }
    
  
   
  
    
   /**
    * rimouve giocatori dal gioco se si sono arresi,
    * @param defeatedPlayer
    */
 public void setdefeatedPlayer(Player defeatedPlayer){
        this.playerList.remove(defeatedPlayer);
    }
  
   
  
 
    

    //setta il giocatore corrente
    /**
     * 
     * @param currentPlayer giocatore corrente
     */
    public void setCurrentPlayer(Player currentPlayer){
     if(playerList.contains(currentPlayer))
            this.currentPlayer = currentPlayer;
        else if(nextPlayer != null)
            this.currentPlayer = playerList.get(getTurnNumber() + 1);
        else 
            this.currentPlayer = playerList.get(0);
    }
    //setta il giocatore successivo
    /**
     * 
     * @param playerList lista dei giocatori
     */
    public void setNextPlayer(List <Player> playerList){
        if(getTurnNumber() > playerList.size() - 1)
            this.nextPlayer = playerList.get(0);
        else if(getTurnNumber() < playerList.size() - 1)
            this.nextPlayer = playerList.get(getTurnNumber() + 1);
         
          
    }
    //setta il numero del turno
    /**
     * 
     * @param turnNumber numero del turno
     */
    public void goHeadTurn(){
        if(getTurnNumber() < playerList.size() - 1 ){
            if(playerList.contains(currentPlayer))
                setCurrentPlayer(playerList.get(turnNumber));
                else
                    setCurrentPlayer(playerList.get(turnNumber + 1));
                if(playerList.contains(nextPlayer))    
                        setNextPlayer(playerList);  
                
    }         
        else if(getTurnNumber() == playerList.size() - 1 ){
            this.currentPlayer = playerList.get(playerList.size() - 1);
            this.nextPlayer = playerList.get(0);
            this.turnNumber = -1;
}
        else {
        setCurrentPlayer(playerList.get(turnNumber));
        setNextPlayer(playerList);
        }
        
                this.turnNumber = turnNumber + 1;
        
        
    }
   
    //ritorna il vincitore
    /**
     * 
     * @param playerListInGame lista dei giocatori in gioco
     * @return vincitore
     */
    public Player winner(List<Player> playerListInGame){
        if(playerListInGame.size() == 1)
            return playerList.get(0);
        else
            return null;
    }
    //dice chi ha vinto se un giocatore raggiunge l'obiettivo e setta gli altri giocatori come fuori gioco
    /**
     * 
     * @param playerList lista dei giocatori
     */
    public void winningCondition(List<Player> playerList){
    Player p1 = nextPlayer;
        for(Player p : playerList){
        
            if(p.getObiettivo() == p.getStatoObiettivo()
                && p1 != p){
                
                    setdefeatedPlayer(p1);
            } 
            
                
            


        }
       
               
                
            
    }
}


