package com.project.progettorisikorisikiamobackend.gameState;

import java.io.IOException;

import java.util.List;


import com.project.progettorisikorisikiamobackend.Turno.Player;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;



public class GameStateManager {
    private GameState gameState;
    private List<Player> players;
    
    public GameStateManager(List <Player> players) {
       this.players = players;
        this.gameState = new StartState(this, "StartState", new Turn(players), new Map("mappa"));
    }
    public GameStateManager(String nameState, List <Player> players) {
        this.players = players;
            this.gameState = changeState(nameState); 
        

    }
    
    /**
     * @throws IOException
     * 
     */
    
        public GameState changeState(String command) {
           
            switch (command) {
            case "sposta":
                setMoveState(command);
                break;
            case "attacca":
                setAttackState(command);
                break;
            case "rinforza":
                setReinforceState(command);
                break;
            case "fineTurno":
                setEndTurnState(command);
                break;
            default:
                System.out.println("comando non valido");
                break;
            }
            return this.gameState;
        
        
        




    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public void setMoveState(String nameState) {
        this.gameState = new MoveState(this, nameState, getGameState().getTurno());
    }
    public void setAttackState(String nameState) {
        this.gameState = new AttackState(this, nameState, getGameState().getTurno());
    }
    public void setReinforceState(String nameState) {
        this.gameState = new RenforceState(this, nameState, getGameState().getTurno());
    }
    public void setEndTurnState(String nameState) {
        this.gameState = new EndTurnState(this, nameState, getGameState().getTurno());
    }
    
}
