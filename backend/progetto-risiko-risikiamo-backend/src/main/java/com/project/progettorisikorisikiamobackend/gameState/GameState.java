package com.project.progettorisikorisikiamobackend.gameState;
import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.Turno.Turn;

public abstract class GameState {
private String nameState;
private Turn turno;


public GameState(String nameState, Turn turno) {
    this.nameState = nameState;
    this.turno = turno;
}



public abstract void sposta();
public abstract void attacca();
public abstract void rinforza();
public void setTurn(Turn turno){
    this.turno = turno;
}
public  Turn getTurno()
{
    return this.turno;
}




public void setNameState(String nameState){
    this.nameState = nameState;
}
public String getNameState(){
    return this.nameState;
}





}
