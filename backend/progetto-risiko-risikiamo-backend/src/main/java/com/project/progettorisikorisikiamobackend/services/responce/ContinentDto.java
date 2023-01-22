package com.project.progettorisikorisikiamobackend.services.responce;

import java.util.List;

import lombok.Data;

@Data
public class ContinentDto {

    private String name;

    private List<TerritoryDto> territory;

    private int bonus;

}
