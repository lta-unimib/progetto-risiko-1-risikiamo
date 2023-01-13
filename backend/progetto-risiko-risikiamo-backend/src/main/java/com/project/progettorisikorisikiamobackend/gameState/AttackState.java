package com.project.progettorisikorisikiamobackend.gameState;



public class AttackState extends GameState {

    public AttackState(GameStateManager ganGameStateManager, String name) {
        super(ganGameStateManager, name);

    }

    public void move() {
        // ? SUPER ???????????? non funziona così Refactoring needed

        // super.getGameStateManager().changeState("sposta");

    }

    public void attack() {

        // super.getGameStateManager().changeState("attaca");

    }

    public void renforce() {

        System.out.println("non è possibile effettuare rinforzi in questo stato");

    }

    public void endTurn() {
        // super.getGameStateManager().changeState("fineTurno");

    }

}