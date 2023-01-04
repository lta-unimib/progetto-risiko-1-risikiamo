package com.project.progettorisikorisikiamobackend.gameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.progettorisikorisikiamobackend.Turno.PlayerPlaceHolder;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;

import com.project.progettorisikorisikiamobackend.gameState.MoveState;

public class GameStatus {
    private GameState gameState;
    private List<PlayerPlaceHolder> players;
    
    public GameStatus(List <PlayerPlaceHolder> players) {
       this.players = players;
        this.gameState = new StartState("StartState", new Turn(players), new Map("mappa"));
    }
    public GameStatus() {
        this.players = new ArrayList<>();
        this.gameState = new StartState("StartState", new Turn(players), new Map("mappa"));
    }
    /**
     * @throws IOException
     * 
     */
    public void changeState() throws IOException {
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
