package com.project.progettorisikorisikiamobackend.Map;

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
    private HashMap<String, Continet> continets;

    public Map(String mapId) {
        this.mapId = mapId;
        continets = new HashMap<>();
    }

    /**
     * Add a continet to the map
     * 
     * @param continet   the continet to add
     * @param continetId the id of the continet
     */
    public void addContinet(Continet continet, String continetId) {
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
     */
    public boolean isContinetInMap(Continet continet) {
        return continets.containsValue(continet);
    }

    /**
     * Check if the continet is in the map
     * 
     * @param continetId the id of the continet to check
     * @return true if the continet is in the map, false otherwise
     */
    public boolean isContinetInMap(String continetId) {
        return continets.containsKey(continetId);
    }

    /**
     * Check if the territory is in the map
     * 
     * @param territory the territory to check
     * @return true if the territory is in the map, false otherwise
     */
    public boolean isTerritoryInMap(Territory territory) {
        for (Continet continet : continets.values()) {
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
     */
    public boolean isTerritoryInMap(String territoryId) {
        for (Continet continet : continets.values()) {
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
     */
    public Continet getContinet(String continetId) {
        return continets.get(continetId);
    }

    /**
     * Get the territory from the map
     * 
     * @param territory the territory to get
     * @return the territory, null if the territory is not in the map
     */
    public Territory getTerritory(String territoryId) {
        for (Continet continet : continets.values()) {
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
    public void removeContinet(Continet continet) {
        continets.values().remove(continet);
    }

    /**
     * Return the number of continents owned by the player
     * 
     * @param player
     * @return the number of continents owned by the player
     */
    public int getNumberOfContinent(PlayerPlaceholder player) {
        if (player == null)
            return 0;
        int ownedContinets = 0;
        for (Continet continet : continets.values()) {
            if (continet.isOwned(player)) {
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
     */
    public int getNumberOfTerritories(PlayerPlaceholder player) {
        if (player == null)
            return 0;
        int ownedTerritories = 0;
        for (Continet continet : continets.values()) {
            ownedTerritories += continet.getNumberOfTerritories(player);
        }
        return ownedTerritories;
    }

    /**
     * Return the territories owned by the player
     * 
     * @param player
     * @return the territories owned by the player
     */
    public Collection<Territory> getTerritories(PlayerPlaceholder player) {
        if (player == null)
            return new ArrayList<>();

        Collection<Territory> territories = new ArrayList<>();
        for (Continet continet : continets.values()) {
            territories.addAll(continet.getTerritories(player));
        }
        return territories;
    }

    /**
     * Return the continents owned by the player
     * 
     * @param player
     * @return the continents owned by the player
     */
    public Collection<Continet> getContinents(PlayerPlaceholder player) {
        Collection<Continet> continents = new ArrayList<>();
        if (player == null)
            return continents;

        for (Continet continet : continets.values()) {
            if (continet.isOwned(player)) {
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
     */
    public Collection<Continet> getContinents() {
        return continets.values();
    }

}
