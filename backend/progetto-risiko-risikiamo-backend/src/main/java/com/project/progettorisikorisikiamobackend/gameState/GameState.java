package main.java.com.project.progettorisikorisikiamobackend.gameState;
import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.Turno.Turn;

import main.java.com.project.progettorisikorisikiamobackend.*;
public abstract class GameState {
private GameStatus gameStatus;
private String nameState;
private PlayerPlaceHolder player;
private Turn turno;

public GameState (GameStatus gameStatus, String nameState, PlayerPlaceHolder player, Turn turno) {
    this.gameStatus = gameStatus;
    this.nameState = nameState;
    this.player = player;

}
public GameState (String nameState, Turn turno) {
    this.nameState = nameState;
    this.turno = turno;
   GameStatus gameStatus = new GameStatus();
    this.gameStatus = gameStatus;
    this.player = turno.getCurrentPlayer();

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
}
