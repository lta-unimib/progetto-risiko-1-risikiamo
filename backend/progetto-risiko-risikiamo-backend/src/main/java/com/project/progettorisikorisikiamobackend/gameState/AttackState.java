package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Turno.*;

public class AttackState extends GameState{

    public AttackState(GameStateManager ganGameStateManager, String name, Turn turno) {
        super(ganGameStateManager,  name, turno);
        
    }
public void move() {
   super.getGameStateManager().changeState("sposta");
    

}
public void attack() {
    // TODO Auto-generated method stub
   super.getTurno().getCurrentPlayer().attack();

}
public void renforce() {
    // TODO Auto-generated method stub
    System.out.println("non è possibile effettuare rinforzi in questo stato");
   
}
public void endTurn() {
  super.getGameStateManager().changeState("fineTurno");
   
}





    
}