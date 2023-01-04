package com.project.progettorisikorisikiamobackend.map;
import com.project.progettorisikorisikiamobackend.player.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
    private HashMap<String, Continent> continets;

    public Map(String mapId) {
        this.mapId = mapId;
        continets = new HashMap<>();
    }

    /**
     * Add a continet to the map
     * 
     * @param continet   the continet to add
     * @param continetId the id of the continet
     * @throws IllegalArgumentException if the continet is already in the map
     * @throws IllegalArgumentException if the continet id is already in the map
     * @throws IllegalArgumentException if the continet is null
     * @throws IllegalArgumentException if the continet id is null
     * @author Mauro Zorzin
     */
    public void addContinet(Continent continet, String continetId) throws IllegalArgumentException {
        if (isContinetInMap(continetId)) {
            throw new IllegalArgumentException("Continet already in the map");
        }
        if (isContinetInMap(continet)) {
            throw new IllegalArgumentException("Continet already in the map");
        }

        if (continet == null) {
            throw new IllegalArgumentException("Continet id can't be null");
        }

        if (continetId.isEmpty() || continetId.isBlank()) {
            throw new IllegalArgumentException("Continet id can't be null");
        }

        continets.put(continetId, continet);
    }

    /**
     * Check if the continet is in the map
     * 
     * @param continet the continet to check
     * @return true if the continet is in the map, false otherwise
     * @author Mauro Zorzin
     */
    public boolean isContinetInMap(Continent continet) {
        return continets.containsValue(continet);
    }

    /**
     * Check if the continet is in the map
     * 
     * @param continetId the id of the continet to check
     * @return true if the continet is in the map, false otherwise
     * @author Mauro Zorzin
     */
    public boolean isContinetInMap(String continetId) {
        return continets.containsKey(continetId);
    }

    /**
     * Check if the territory is in the map
     * 
     * @param territory the territory to check
     * @return true if the territory is in the map, false otherwise
     * @author Mauro Zorzin
     */
    public boolean isTerritoryInMap(Territory territory) {
        for (Continent continet : continets.values()) {
            if (continet.isTerritoryInContinet(territory)) {
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
        for (Continent continet : continets.values()) {
            if (continet.isTerritoryInContinet(territoryId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the continet from the map
     * 
     * @param continetId the id of the continet to get
     * @return the continet, null if the continet is not in the map
     * @author Mauro Zorzin
     */
    public Continent getContinet(String continetId) {
        return continets.get(continetId);
    }

    /**
     * Get the territory from the map
     * 
     * @param territory the territory to get
     * @return the territory, null if the territory is not in the map
     * @author Mauro Zorzin
     */
    public Territory getTerritory(String territoryId) {
        for (Continent continet : continets.values()) {
            if (continet.isTerritoryInContinet(territoryId)) {
                return continet.getTerritory(territoryId);
            }
        }
        return null;
    }

    /**
     * Remove the continet from the map
     * 
     * @param continetId the id of the continet to remove
     */
    public void removeContinet(String continetId) {
        continets.remove(continetId);
    }

    /**
     * Remove the continet from the map
     * 
     * @param continet the continet to remove
     */
    public void removeContinet(Continent continet) {
        continets.values().remove(continet);
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
        int ownedContinets = 0;
        for (Continent continet : continets.values()) {
            if (continet.isOwnedBy(player)) {
                ownedContinets++;
            }
        }
        return ownedContinets;
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
        for (Continent continet : continets.values()) {
            ownedTerritories += continet.getNumberOfTerritories(player);
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
        for (Continent continet : continets.values()) {
            territories.addAll(continet.getTerritories(player));
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
        Collection<Continent> continents = new ArrayList<>();
        if (player == null)
            throw new IllegalArgumentException("Player can't be null");

        for (Continent continet : continets.values()) {
            if (continet.isOwnedBy(player)) {
                continents.add(continet);
            }
        }
        return continents;
    }

    /**
     * Return the continents owned by the player
     * 
     * @param player
     * @return the continents owned by the player
     * @author Mauro Zorzin
     */
    public Collection<Continent> getContinents() {
        return continets.values();
    }

}
