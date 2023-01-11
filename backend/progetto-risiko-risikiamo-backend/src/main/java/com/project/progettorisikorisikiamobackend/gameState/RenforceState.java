package com.project.progettorisikorisikiamobackend.gameState;
//import
import com.project.progettorisikorisikiamobackend.Turno.*;
import java.util.Scanner;

public class RenforceState extends GameState{
   public RenforceState(GameStateManager gameStateManager, String name, Turn turno) {
        super(gameStateManager, name, turno);
        // TODO Auto-generated constructor stub
    }
     
    public void move() {
    
    super.getGameStateManager().changeState("sposta");
        
    }
    public void attack() {
        // TODO Auto-generated method stub
        super.getGameStateManager().changeState("attacca");
    }
    public void renforce() {
        Scanner input = new Scanner(System.in);
        System.out.println("Quanti rinforzi vuoi posizionare?");
        int rinforzi = input.nextInt();
        
       
        // TODO Auto-generated method stub
        super.getTurno().getCurrentPlayer().placeReinforcements(rinforzi);
    input.close();    
    }
    public void endTurn() {
        super.getGameStateManager().changeState("fineTurno");
    }

    
  
   
    
   

  
    
    
}