package com.project.progettorisikorisikiamobackend.gameState;
//import
import com.project.progettorisikorisikiamobackend.Turno.*;
import java.util.Scanner;

public class RenforceState extends GameState{
   public RenforceState(String name, Turn turno) {
        super(name, turno);
        // TODO Auto-generated constructor stub
    }
     
    public void sposta() {
    
    System.out.println("non è possibile effettuare spostamenti in questo stato");
        
    }
    public void attacca() {
        // TODO Auto-generated method stub
        System.out.println("non è possibile effettuare attacchi in questo stato");
    }
    public void rinforza() {
        Scanner input = new Scanner(System.in);
        System.out.println("Quanti rinforzi vuoi posizionare?");
        int rinforzi = input.nextInt();
        // TODO Auto-generated method stub
        super.getTurno().getCurrentPlayer().placeReinforcements(rinforzi);
        
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
    @Override
    public void setPlayerPlaceHolder(PlayerPlaceHolder playerPlaceHolder) {
        super.setPlayerPlaceHolder(playerPlaceHolder);
    }
    @Override
    public void setNameState(String nameState) {
       super.setNameState(nameState);
        
    }
    
    
}