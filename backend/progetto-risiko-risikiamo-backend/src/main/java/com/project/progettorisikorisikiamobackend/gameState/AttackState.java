package main.java.com.project.progettorisikorisikiamobackend.gameState;
import java.util.List;

import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.map.Territory;
import java.lang.System.Logger;
import main.java.com.project.progettorisikorisikiamobackend.Turno.Turn;
public class AttackState extends GameState{




    public AttackState(GameStatus gameStatus, com.project.progettorisikorisikiamobackend.Turno.Turn turno) {
        super(gameStatus, turno);
        
    }
public void sposta() {
    Logger logger = new Logger();
    logger.config("non è possibile effettuare spostamenti in questo stato");
    

}
public void attacca() {
    // TODO Auto-generated method stub
   super.getTurno().getCurrentPlayer().attack();

}
public void rinforza() {
    // TODO Auto-generated method stub
    Logger logger = new Logger();
    logger.config("non è possibile effettuare rinforzi in questo stato");
    

    
}

    
}