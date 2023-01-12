package com.project.progettorisikorisikiamobackend.domain;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.GameState;
import com.project.progettorisikorisikiamobackend.map.Map;

import lombok.Data;

@Data
public class Game {

    private String id;

    private String name;

    private List<Player> players;

    private Map map;

    private Turn turn;

    private GameState gameState;

    public Game(String name, Map map, String gameId) {
        this.id = gameId;
        this.name = name;
        this.players = new ArrayList<>();
        this.map = map;
        this.turn = null;
        this.gameState = null;
    }

    public void addPlayer(Player player) {
        if (turn == null)
            this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void startGame() {
        // Set turn & State
    }

    public void endGame() {
        // Set turn & State
    }

}