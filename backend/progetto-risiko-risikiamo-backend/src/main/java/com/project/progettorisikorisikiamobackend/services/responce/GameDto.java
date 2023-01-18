package com.project.progettorisikorisikiamobackend.services.responce;

import lombok.Data;

@Data
public class GameDto {

    private String name;
    private String id;
    private boolean isGameEnded;
    private MapDto map;
    private String turn;
    private String state;

}
