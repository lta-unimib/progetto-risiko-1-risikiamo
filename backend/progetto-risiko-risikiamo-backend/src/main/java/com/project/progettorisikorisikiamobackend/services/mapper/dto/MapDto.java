package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MapDto {

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private List<ContinentDto> continent;

    public Map toMap() {

        Map map = new Map(name);

        for (int i = 0; i < this.continent.size(); i++) {
            Continent continentResult = this.continent.get(i).toContinent();
            map.addContinent(continentResult);
        }

        return map;

    }
}
