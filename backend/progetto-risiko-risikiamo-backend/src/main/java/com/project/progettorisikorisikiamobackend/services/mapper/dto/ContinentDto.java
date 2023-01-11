package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import java.util.ArrayList;
import java.util.HashMap;
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
    private List<List<String>> territory;

    public Continent toContinent() {

        Continent continent = new Continent(name, bonus);

        HashMap<Territory, List<String>> t = new HashMap<>();

        for (int i = 0; i < this.territory.size(); i++) {
            t.put(new Territory(this.territory.get(i).get(0)), this.territory.get(i));
        }
        for (Territory territoryResult : t.keySet()) {
            List<String> list = t.get(territoryResult);
            int i;
            for (i = 1; i < list.size(); i++) {
                t.keySet().stream().filter(territory1 -> territory1.getName().equals(list.get(i)))
                        .forEach(territory1 -> territoryResult.addNeighbor(territory1));
            }
            continent.addTerritory(territoryResult);
        }

        return continent;
    }

}
