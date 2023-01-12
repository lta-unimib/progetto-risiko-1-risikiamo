package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.interf.AGameState;

public class EndTurnState extends AGameState {

    public EndTurnState(GameStateManager gameStateManager, String nameState, Turn turno) {
        super(gameStateManager, nameState, turno);

    }

    @Override
    public void move() {
        System.out.println("non è possibile effettuare spostamenti in questo stato");
    }

    @Override
    public void attack() {
        System.out.println("non è possibile effettuare attacchi in questo stato");
    }

    @Override
    public void renforce() {
        System.out.println("non è possibile effettuare rinforzi in questo stato");
    }

    public void endTurn() {
        // super.getTurno().getCurrentPlayer().drawCard();
        // super.getTurno().setCurrentPlayer(super.getTurno().getNextPlayer());
        // super.getGameStateManager().changeState("rinforza");
    }
}
