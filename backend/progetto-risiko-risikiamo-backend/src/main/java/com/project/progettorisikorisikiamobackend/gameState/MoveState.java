package com.project.progettorisikorisikiamobackend.gameState;
import com.project.progettorisikorisikiamobackend.Turno.*;
public class MoveState  extends GameState{
    
        public MoveState(String name, Turn turno) {
            super( name, turno);
            // TODO Auto-generated constructor stub
        }
    
        @Override
        public void sposta() {
            // TODO Auto-generated method stub
            super.getTurno().getCurrentPlayer().moveArmies();
        }
    
        @Override
        public void attacca() {
            // TODO Auto-generated method stub
            
            System.out.print("non è possibile effettuare attacchi in questo stato");
        }
    
        @Override
        public void rinforza() {
            // TODO Auto-generated method stub
            System.out.print("non è possibile effettuare rinforzi in questo stato");
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



