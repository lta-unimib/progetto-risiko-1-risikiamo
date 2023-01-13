package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import lombok.*;

public abstract class GameState {
    private @Getter @Setter String nameState;
    // ? Non ha senso qui turno ??? Così viene creato un turno a ogni stato ??
    
    private @Getter GameStateManager gameStateManager;

    GameState(GameStateManager gameStateManager, String nameState) {
        this.nameState = nameState;
        this.gameStateManager = gameStateManager;
    }

    public abstract void move();

    public abstract void attack();

    public abstract void renforce();

    public abstract void endTurn();

}
