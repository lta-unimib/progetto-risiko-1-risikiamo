package com.project.progettorisikorisikiamobackend.services.responce;

import java.util.List;

import lombok.Data;

@Data
public class MapDto {

    private String name;

    private List<ContinentDto> continents;

}
