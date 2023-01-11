package com.project.progettorisikorisikiamobackend.domain;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;

import lombok.Data;

@Data
public class Game {

    private String id;

    private ArrayList<Player> players;

    private Map map;

    private Turn turn;

    public Game(List<Player> players, Map map, String gameId) {
        this.id = gameId;
        this.players = (ArrayList<Player>) players;
        this.map = map;
        this.turn = null;
    }

}