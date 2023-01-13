package com.project.progettorisikorisikiamobackend.gameState;

//import
import com.project.progettorisikorisikiamobackend.Turno.*;
import java.util.Scanner;

public class RenforceState extends GameState {
    public RenforceState(GameStateManager gameStateManager, String name) {
        super(gameStateManager, name);
    }

    public void move() {

        // super.getGameStateManager().changeState("sposta");

    }

    public void attack() {

        // super.getGameStateManager().changeState("attacca");
    }

    public void renforce() {

        // super.getTurno().getCurrentPlayer().placeReinforcements(rinforzi);
    }

    public void endTurn() {
        
    }

}