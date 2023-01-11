package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import java.util.List;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameDto {

    @NonNull
    @NotBlank
    private List<String> players;

    @NonNull
    @NotBlank
    private String mapName;

    @NonNull
    @NotBlank
    private List<List<String>> map;

    public Game toGame() {
        Map map = new Map(mapName);

    }

}
