package com.project.progettorisikorisikiamobackend.services.responce;

import java.util.List;

import lombok.Data;

@Data
public class GameDto {

    private String name;
    private String id;
    private String winner;
    private MapDto map;
    private List<String> players;
    private String currentPlayer;

}
