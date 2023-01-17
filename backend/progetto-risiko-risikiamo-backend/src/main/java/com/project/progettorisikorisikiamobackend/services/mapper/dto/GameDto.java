package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.domain.Game;

import com.project.progettorisikorisikiamobackend.map.Map;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameDto {

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private MapDto map;

    public Game toGame(String id) {

        Map mapReturn = this.map.toMap();

        return new Game(name, mapReturn, id);

    }

}
