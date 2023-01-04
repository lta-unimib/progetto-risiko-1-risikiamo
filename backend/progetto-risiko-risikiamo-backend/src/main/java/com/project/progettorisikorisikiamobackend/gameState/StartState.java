package com.project.progettorisikorisikiamobackend.gameState;
import java.util.ArrayList;
import java.util.List;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.*;

import io.github.uinnn.spigot.placeholder.Placeholder;

public class StartState extends GameState {
Map map;
    public StartState(String name, Turn turno, Map map) {
        super(name, turno);
        this.map = map;
        
    }
public void sposta() {
    // TODO Auto-generated method stub
    System.out.println("non è possibile effettuare spostamenti in questo stato");
}
public void attacca() {
    // TODO Auto-generated method stub
    System.out.println("non è possibile effettuare attacchi in questo stato");
}
public void rinforza() {
    List <PlayerPlaceholder> players = new ArrayList<>();
    for (PlayerPlaceholder p : players){
    map.getTerritories(p);
    }
}
@Override
public String getNameState() {
  return this.getNameState();
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
@Override
public void setPlayerPlaceHolder(PlayerPlaceHolder playerPlaceHolder) {
    super.setPlayerPlaceHolder(playerPlaceHolder);
}
 @Override
public void setNameState(String nameState) {
    super.setNameState(nameState);
}

}
