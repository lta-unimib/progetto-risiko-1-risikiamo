package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import lombok.*;
public abstract class GameState {
private @Getter @Setter String nameState;
private @Getter @Setter Turn turno;
private @Getter GameStateManager gameStateManager;


 GameState(GameStateManager gameStateManager, String nameState, Turn turno) {
    this.nameState = nameState;
    this.turno = turno;
    this.gameStateManager = gameStateManager;
}



public abstract void move();
public abstract void attack();
public abstract void renforce();
public abstract void endTurn();







}
