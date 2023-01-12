package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Turno.*;

public class AttackState extends GameState {

    public AttackState(GameStateManager ganGameStateManager, String name, Turn turno) {
        super(ganGameStateManager, name, turno);

    }

    public void move() {
        // ? SUPER ???????????? non funziona così

        super.getGameStateManager().changeState("sposta");

    }

    public void attack() {

        super.getGameStateManager().changeState("attaca");

    }

    public void renforce() {

        System.out.println("non è possibile effettuare rinforzi in questo stato");

    }

    public void endTurn() {
        super.getGameStateManager().changeState("fineTurno");

    }

}