package com.project.progettorisikorisikiamobackend.services.responce;

import java.util.List;

import lombok.Data;

@Data
public class GameDto {

    private String name;
    private int startingArmies;
    private String id;
    private String winner;
    private MapDto map;
    private String svgMap;
    private List<InternalPlayerDto> players;
    private String currentPlayer;
    private boolean isGameStarted;

}
