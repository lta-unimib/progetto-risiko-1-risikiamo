package com.project.progettorisikorisikiamobackend.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.project.progettorisikorisikiamobackend.player.Player;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Map class
 * 
 * @author - Mauro Zorzin
 * @version 1.0
 * 
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Map {

    @NonNull
    private final String mapId;
    private HashMap<String, Continent> continents;

    public Map(String mapId) {
        this.mapId = mapId;
        continents = new HashMap<>();
    }

    /**
     * Add a continent to the map
     * 
     * @param continent   the continent to add
     * @param continentId the id of the continent
     * @throws IllegalArgumentException if the continent is already in the map
     * @throws IllegalArgumentException if the continent id is already in the map
     * @throws IllegalArgumentException if the continent is null
     * @throws IllegalArgumentException if the continent id is null
     * @author Mauro Zorzin
     */
    public void addContinent(Continent continent) throws IllegalArgumentException {

        if (isContinentInMap(continent)) {
            throw new IllegalArgumentException("Continent already in the map");
        }

        if (continent == null) {
            throw new IllegalArgumentException("Continent id can't be null");
        }
        String continentId = continent.getName();

        if (isContinentInMap(continentId)) {
            throw new IllegalArgumentException("Continent already in the map");
        }

        if (continentId.isEmpty() || continentId.isBlank()) {
            throw new IllegalArgumentException("Continent id can't be null");
        }

        continents.put(continentId, continent);
    }

    /**
     * Check if the continent is in the map
     * 
     * @param continent the continent to check
     * @return true if the continent is in the map, false otherwise
     * @author Mauro Zorzin
     */
    public boolean isContinentInMap(Continent continent) {
        return continents.containsValue(continent);
    }

    /**
     * Check if the continent is in the map
     * 
     * @param continentId the id of the continent to check
     * @return true if the continent is in the map, false otherwise
     * @author Mauro Zorzin
     */
    public boolean isContinentInMap(String continentId) {
        return continents.containsKey(continentId);
    }

    /**
     * Check if the territory is in the map
     * 
     * @param territory the territory to check
     * @return true if the territory is in the map, false otherwise
     * @author Mauro Zorzin
     */
    public boolean isTerritoryInMap(Territory territory) {
        for (Continent continent : continents.values()) {
            if (continent.isTerritoryInContinent(territory)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the territory is in the map
     * 
     * @param territoryId the id of the territory to check
     * @return true if the territory is in the map, false otherwise
     * @author Mauro Zorzin
     */
    public boolean isTerritoryInMap(String territoryId) {
        for (Continent continent : continents.values()) {
            if (continent.isTerritoryInContinent(territoryId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the continent from the map
     * 
     * @param continentId the id of the continent to get
     * @return the continent, null if the continent is not in the map
     * @author Mauro Zorzin
     */
    public Continent getContinent(String continentId) {
        return continents.get(continentId);
    }

    /**
     * Get the territory from the map
     * 
     * @param territory the territory to get
     * @return the territory, null if the territory is not in the map
     * @author Mauro Zorzin
     */
    public Territory getTerritory(String territoryId) {
        for (Continent continent : continents.values()) {
            if (continent.isTerritoryInContinent(territoryId)) {
                return continent.getTerritory(territoryId);
            }
        }
        return null;
    }

    /**
     * Remove the continent from the map
     * 
     * @param continentId the id of the continent to remove
     */
    public void removeContinent(String continentId) {
        continents.remove(continentId);
    }

    /**
     * Remove the continent from the map
     * 
     * @param continent the continent to remove
     */
    public void removeContinent(Continent continent) {
        continents.values().remove(continent);
    }

    /**
     * Return the number of continents owned by the player
     * 
     * @param player
     * @return the number of continents owned by the player
     * @author Mauro Zorzin
     */
    public int getNumberOfContinent(Player player) {
        if (player == null)
            return 0;
        int ownedContinents = 0;
        for (Continent continent : continents.values()) {
            if (continent.isOwnedBy(player)) {
                ownedContinents++;
            }
        }
        return ownedContinents;
    }

    /**
     * Return the number of territories owned by the player
     * 
     * @param player
     * @return the number of territories owned by the player
     * @author Mauro Zorzin
     */
    public int getNumberOfTerritories(Player player) {
        if (player == null)
            return 0;
        int ownedTerritories = 0;
        for (Continent continent : continents.values()) {
            ownedTerritories += continent.getNumberOfTerritories(player);
        }
        return ownedTerritories;
    }

    /**
     * Return the territories owned by the player
     * 
     * @param player
     * @return the territories owned by the player
     * @author Mauro Zorzin
     */
    public Collection<Territory> getTerritories(Player player) {
        if (player == null)
            return new ArrayList<>();

        Collection<Territory> territories = new ArrayList<>();
        for (Continent continent : continents.values()) {
            territories.addAll(continent.getTerritories(player));
        }
        return territories;
    }

    /**
     * Return the continents owned by the player
     * 
     * @param player
     * @return the continents owned by the player
     * @throws IllegalArgumentException if the player is null
     * @author Mauro Zorzin
     */
    public Collection<Continent> getContinents(Player player) throws IllegalArgumentException {
        Collection<Continent> continentsTemp = new ArrayList<>();
        if (player == null)
            throw new IllegalArgumentException("Player can't be null");

        for (Continent continent : this.continents.values()) {
            if (continent.isOwnedBy(player)) {
                continentsTemp.add(continent);
            }
        }
        return continentsTemp;
    }

}
