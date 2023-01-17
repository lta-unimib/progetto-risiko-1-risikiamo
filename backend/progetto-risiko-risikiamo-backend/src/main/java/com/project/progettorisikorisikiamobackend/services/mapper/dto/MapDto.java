package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;

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

    @NonNull
    @NotBlank
    private List<NeighborsPairDto> neighbors;

    public Map toMap() {

        Map map = new Map(name);

        for (int i = 0; i < this.continent.size(); i++) {
            Continent continentResult = this.continent.get(i).toContinent();
            map.addContinent(continentResult);
        }

        for (int i = 0; i < this.neighbors.size(); i++) {

            Pair<String, List<String>> pair = this.neighbors.get(i).toPair();
            Territory ter = map.getTerritory(pair.getLeft());
            List<String> neighborsResult = pair.getRight();
            for (int j = 0; j < neighborsResult.size(); j++) {
                Territory neb = map.getTerritory(neighborsResult.get(j));
                ter.addNeighbor(neb);
            }
        }

        return map;

    }

    @Data
    public static class NeighborsPairDto {
        @NonNull
        @NotBlank
        private String territory;
        @NonNull
        @NotBlank
        private List<String> neighbors;

        public Pair<String, List<String>> toPair() {

            return new ImmutablePair<>(territory, neighbors);
        }

    }
}
