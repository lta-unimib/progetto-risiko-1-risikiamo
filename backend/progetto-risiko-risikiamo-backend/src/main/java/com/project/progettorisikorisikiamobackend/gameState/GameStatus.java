package main.java.com.project.progettorisikorisikiamobackend.gameState;

import java.util.Scanner;
import lombock.Getter;
import lombok.Setter;
import java.lang.System.Logger;

public class GameStatus {
    private GameState gameState;
    public GameStatus() {
        gameState = new RenforceState(this);
    }
    /**
     * 
     */
    public void changeState() {
        Scanner scanner = new Scanner(System.in);
        Logger logger = new Logger();
        logger.config("Inserisci azione: ");
        String command = scanner.nextLine();
        GameState nextState = gameState.nextState(command);
        this.gameState = nextState;
        System.in.close();
        




    }
    public GameState getGameState() {
        return gameState;
    }
    
}
