package com.project.progettorisikorisikiamobackend.services.responce;

import java.util.List;

import lombok.Data;

@Data
public class TerritoryDto {

    private String name;

    private int army;

    private String owner;

    private List<String> neighbours;

}
