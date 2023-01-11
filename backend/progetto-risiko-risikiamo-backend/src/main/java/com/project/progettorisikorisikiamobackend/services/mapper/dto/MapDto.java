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
    private List<String> continent;

    public MapDto(String name, List<String> map) {
        this.name = name;
        this.continent = map;
    }

    public Map MapTo() {

        Map map = new Map(name);

        
        for (int i = 0; i < this.continent.size(); i++) {
            map.addContinent(new Continent(this.continent.get(i),0), this.continent.get(i));
            
        }

}
