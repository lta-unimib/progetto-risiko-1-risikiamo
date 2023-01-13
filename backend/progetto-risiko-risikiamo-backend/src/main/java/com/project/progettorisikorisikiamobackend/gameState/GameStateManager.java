package com.project.progettorisikorisikiamobackend.gameState;

import java.io.IOException;

import java.util.List;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Getter;

@Getter
public class GameStateManager {
    private GameState gameState;
    // ? perchè una lista di player ?? Anche se è una classe provisoria Turn esiste
    // già
    private Turn  turno;

    public GameStateManager(Turn turno) {
        this.turno = turno;
        this.gameState = new StartState(this, "StartState", new Map("mappa"));
    }

    /*public GameStateManager(String nameState, Turn turno) {
        this.turno = turno;
        this.gameState = new GameState(nameState, this); 
            
        };*/

    

    /**
     * @throws IOException
     * 
     */

    public void renforce() {
        gameState.renforce();
    }
    public void move() {
        gameState.move();
    }
    public void attack() {
        gameState.attack();
    }
    public void endTurn() {
        gameState.endTurn();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

   
}

