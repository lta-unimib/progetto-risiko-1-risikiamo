package com.project.progettorisikorisikiamobackend.gameState;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
public class EndTurnState extends GameState {
    
        public EndTurnState(GameStateManager gameStateManager, String nameState, Turn turno) {
            super(gameStateManager, nameState, turno);
            // TODO Auto-generated constructor stub
        }
    
        @Override
        public void move() {
            // TODO Auto-generated method stub
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
    super.getTurno().getCurrentPlayer().drawCard();
    super.getTurno().setCurrentPlayer(super.getTurno().getNextPlayer());
    super.getGameStateManager().changeState("rinforza");
    }
}
