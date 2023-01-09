package com.project.progettorisikorisikiamobackend.gameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;



public class GameStateManager {
    private GameState gameState;
    private List<PlayerPlaceHolder> players;
    
    public GameStateManager(List <PlayerPlaceHolder> players) {
       this.players = players;
        this.gameState = new StartState("StartState", new Turn(players), new Map("mappa"));
    }
    public GameStateManager(String nameState, List <PlayerPlaceHolder> players) {
        this.players = players;
         try {
            this.gameState = changeState(nameState); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    /**
     * @throws IOException
     * 
     */
    public GameState changeState() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci azione: ");
        String command = scanner.nextLine();
        switch (command) {
        case "sposta":
            setMoveState(command);
            break;
        case "attacca":
            setAttackState(command);
            break;
        case "rinforza":
            setReinforceState(command);
            break;
        default:
            System.out.println("comando non valido");
            break;
        }
        scanner.close();
        return this.gameState;
    }
        public GameState changeState(String command) throws IOException {
           
            switch (command) {
            case "sposta":
                setMoveState(command);
                break;
            case "attacca":
                setAttackState(command);
                break;
            case "rinforza":
                setReinforceState(command);
                break;
            default:
                System.out.println("comando non valido");
                break;
            }
            return this.gameState;
        
        
        




    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public void setMoveState(String nameState) {
        this.gameState = new MoveState(nameState, getGameState().getTurno());
    }
    public void setAttackState(String nameState) {
        this.gameState = new AttackState(nameState, getGameState().getTurno());
    }
    public void setReinforceState(String nameState) {
        this.gameState = new RenforceState(nameState, getGameState().getTurno());
    }
    
}
