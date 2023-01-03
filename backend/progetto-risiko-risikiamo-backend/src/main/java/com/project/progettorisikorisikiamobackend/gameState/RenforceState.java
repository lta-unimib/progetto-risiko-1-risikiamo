package main.java.com.project.progettorisikorisikiamobackend.gameState;

import java.util.Scanner;

public class RenforceState extends GameState{
    public RenforceState(GameStatus gameStatus, Turn turno) {
        super(gameStatus, turno);
        // TODO Auto-generated constructor stub
    }
     
    public void sposta() {
    Logger logger = new Logger();
    logger.config("non è possibile effettuare spostamenti in questo stato");
        
    }
    public void attacca() {
        // TODO Auto-generated method stub
        Logger logger = new Logger();
        logger.config("non è possibile effettuare attacchi in questo stato");
        
    }
    public void rinforza() {
        Scanner input = new Scanner(System.in);
        System.out.println("Quanti rinforzi vuoi posizionare?");
        int rinforzi = input.nextInt();
        // TODO Auto-generated method stub
        super.getTurno().getCurrentPlayer().placeReinforcements(rinforzi);
        
    }
    
}