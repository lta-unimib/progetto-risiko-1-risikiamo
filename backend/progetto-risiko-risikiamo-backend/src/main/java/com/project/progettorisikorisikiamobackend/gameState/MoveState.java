package com.project.progettorisikorisikiamobackend.gameState;
import com.project.progettorisikorisikiamobackend.Turno.*;
public class MoveState  extends GameState{
    
        public MoveState(GameStateManager gameStateManager,String name, Turn turno) {
            super(  gameStateManager,name, turno);
            // TODO Auto-generated constructor stub
        }
    
        @Override
        public void move() {
            // TODO Auto-generated method stub
            super.getTurno().getCurrentPlayer().moveArmies();
        }
    
        @Override
        public void attack() {
            // TODO Auto-generated method stub
            
            System.out.print("non è possibile effettuare attacchi in questo stato");
        }
    
        @Override
        public void renforce() {
            // TODO Auto-generated method stub
            System.out.print("non è possibile effettuare rinforzi in questo stato");
        }
    public void endTurn() {
        super.getGameStateManager().changeState("fineTurno");
    }
   


   
    
}



