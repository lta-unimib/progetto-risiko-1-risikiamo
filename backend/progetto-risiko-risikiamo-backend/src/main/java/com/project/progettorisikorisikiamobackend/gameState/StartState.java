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
  return super.getNameState();
}

 @Override
public void setNameState(String nameState) {
    super.setNameState(nameState);
}

}
