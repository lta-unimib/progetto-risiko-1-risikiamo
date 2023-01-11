package com.project.progettorisikorisikiamobackend.gameState;
import java.util.ArrayList;
import java.util.List;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.*;



public class StartState extends GameState {
Map map;
    public StartState(GameStateManager gameStateManager,String name, Turn turno, Map map) {
        super(gameStateManager,name, turno);
        this.map = map;
        
    }
public void move() {
    // TODO Auto-generated method stub
    System.out.println("non è possibile effettuare spostamenti in questo stato");
}
public void attack() {
    // TODO Auto-generated method stub
    System.out.println("non è possibile effettuare attacchi in questo stato");
}
public void renforce() {
   
    List <Player> players = super.getTurno().getPlayerList();
    for (Player p : players){
        while (p.getArmies() > 0){
            p.placeReinforcements(3);
            p.setArmies(p.getArmies() - 3);
        }
   
    
    }
    super.getGameStateManager().changeState("attacca");

}
public void endTurn() {
   super.getGameStateManager().changeState("fineTurno");
}
}
