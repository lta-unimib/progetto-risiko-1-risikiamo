package com.project.progettorisikorisikiamobackend.services.responce;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import lombok.Data;

@Data
public class GameDto {

    private String name;
    private int startingArmies;
    private String id;
    private String winner;
    private MapDto map;
    private List<Pair<String, String>> players;
    private String currentPlayer;

}
