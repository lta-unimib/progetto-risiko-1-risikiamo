package com.project.progettorisikorisikiamobackend;
import java.util.*;
public class Turn {
   private List <PlayerPlaceHolder> playerList;
 
    Dice d = new Dice(6);
    
    //costruttore
    public Turn(List <PlayerPlaceHolder> playerList, Dice d){
        this.d = d;
        setPlayerList(playerList);

        
        
    }
    public Turn(){
        this.playerList = new ArrayList<PlayerPlaceHolder>();
        
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
    
    public void setPlayerList(List <PlayerPlaceHolder> playerList){
        this.playerList = playerList;
    }
    public List <PlayerPlaceHolder> getPlayerList(){
        return this.playerList;
    }
    
   
    //ritorna la lista dei giocatori ancora in gioco
    public List <PlayerPlaceHolder> playersInGame(List<PlayerPlaceHolder>playerList){
       List<PlayerPlaceHolder> playerListInGame = new ArrayList<PlayerPlaceHolder>();
        for(PlayerPlaceHolder p : playerList){
            if(p.getIsIngame() != false){
                playerListInGame.add(p);
            }
        }
        return playerListInGame;
    }
    //ritorna il giocatore successivo
    public PlayerPlaceHolder nextPlayer(List<PlayerPlaceHolder> playerList, int order){
        PlayerPlaceHolder nextPlayer = playerList.get(order);
        return nextPlayer;
    }
    //ritorna il giocatore corrente
    public PlayerPlaceHolder currentPlayer(List<PlayerPlaceHolder> playerList, int order){
        PlayerPlaceHolder currentPlayer = playerList.get(order);
        return currentPlayer;
    }
    //ritorna il giocatore precedente
    public PlayerPlaceHolder previousPlayer(List<PlayerPlaceHolder> playerList, int order){
        PlayerPlaceHolder previousPlayer = playerList.get(order);
        return previousPlayer;
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
        for(PlayerPlaceHolder p : playerList){
            if(p.getObiettivo() == p.getStatoObiettivo()){
                System.out.println("Il giocatore " + p.getPlayerName() + " ha vinto!");
                
            }


        }
        for(PlayerPlaceHolder p : playerList){
            if(p.getObiettivo() != p.getStatoObiettivo()){
                p.setIsIngame(false); 
                
            }
    }
}

}
