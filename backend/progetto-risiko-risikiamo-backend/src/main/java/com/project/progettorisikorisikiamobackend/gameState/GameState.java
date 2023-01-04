package com.project.progettorisikorisikiamobackend.gameState;
import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.Turno.Turn;

public abstract class GameState {
private GameStatus gameStatus;
private String nameState;
private PlayerPlaceHolder player;
private Turn turno;

 GameState (GameStatus gameStatus, String nameState, PlayerPlaceHolder player, Turn turno) {
    this.gameStatus = gameStatus;
    this.nameState = nameState;
    this.player = player;
    this.turno = turno;

}
 GameState (String nameState, Turn turno) {
    this.nameState = nameState;
    this.turno = turno;
   GameStatus gameStatus = new GameStatus(turno.getPlayerList());
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

public void setPlayerPlaceHolder(PlayerPlaceHolder player){
    this.player = player;
}
public PlayerPlaceHolder getPlayerPlaceHolder(){
    return this.player;
}
public void setGameStatus(GameStatus gameStatus){
    this.gameStatus = gameStatus;
}
public GameStatus getGameStatus(){
    return this.gameStatus;
}
public void setNameState(String nameState){
    this.nameState = nameState;
}
public String getNameState(){
    return this.nameState;
}
public PlayerPlaceHolder getPlayer() {
    return player;
}
public void setPlayer(PlayerPlaceHolder player) {
    this.player = player;
}
public void setTurno(Turn turno) {
    this.turno = turno;
}


}
