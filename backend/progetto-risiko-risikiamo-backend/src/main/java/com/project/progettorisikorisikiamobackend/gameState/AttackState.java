package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Turno.*;

public class AttackState extends GameState{

    public AttackState(String name, Turn turno) {
        super(name, turno);
        
    }
public void sposta() {
    
    System.out.println("non è possibile effettuare spostamenti in questo stato");
    

}
public void attacca() {
    // TODO Auto-generated method stub
   super.getTurno().getCurrentPlayer().attack();

}
public void rinforza() {
    // TODO Auto-generated method stub
    System.out.println("non è possibile effettuare rinforzi in questo stato");
   
}
@Override
public String getNameState() {
  return super.getNameState();
}
@Override
public void setGameStatus(GameStatus gameStatus) {
    this.setGameStatus(gameStatus);
}
@Override
public GameStatus getGameStatus() {
    return this.getGameStatus();
}
@Override
public PlayerPlaceHolder getPlayerPlaceHolder() {
    return super.getPlayerPlaceHolder();
}


    
}