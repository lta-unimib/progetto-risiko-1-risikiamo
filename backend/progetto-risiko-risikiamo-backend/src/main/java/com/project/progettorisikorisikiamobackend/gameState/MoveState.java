package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Turno.*;

public class MoveState extends GameState {

    public MoveState(GameStateManager gameStateManager, String name, Turn turno) {
        super(gameStateManager, name, turno);

    }

    @Override
    public void move() {

        // Perchè qui non c'è il chenge state?
        // super.getTurno().getCurrentPlayer().moveArmies();
    }

    @Override
    public void attack() {

        System.out.print("non è possibile effettuare attacchi in questo stato");
    }

    @Override
    public void renforce() {

        System.out.print("non è possibile effettuare rinforzi in questo stato");
    }

    public void endTurn() {
        // super.getGameStateManager().changeState("fineTurno");
    }

}
