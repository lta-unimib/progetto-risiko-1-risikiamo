package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import java.util.List;

import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Territory;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContinentDto {

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private int bonus;

    @NotBlank
    @NonNull
    private List<String> territory;

    public Continent toContinent() {

        Continent continent = new Continent(name, bonus);

        for (int i = 0; i < this.territory.size(); i++) {
            Territory territoryResult = new Territory(this.territory.get(i));
            continent.addTerritory(territoryResult);
        }

        return continent;
    }

}
