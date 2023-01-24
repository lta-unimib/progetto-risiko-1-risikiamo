package com.project.progettorisikorisikiamobackend.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Continent class
 * 
 * @author - Mauro Zorzin
 * @version 1.0
 */
@Getter
@EqualsAndHashCode
public class Continent {

    private String name;
    private int bonus;
    private HashMap<String, Territory> territories;

    // Constructor
    public Continent(String name, int bonus) {
        this.name = name;
        this.bonus = bonus;
        territories = new HashMap<>();
    }

    /**
     * Get the owner of the continent
     * 
     * @aurhor Mauro Zozin
     * @return the owner of the continent, null if the continent is not owned
     */
    public Player getOwner() {
        if (territories.isEmpty()) {
            return null;
        }
        Player owner = territories.values().iterator().next().getOwner();
        if (owner == null) {
            return null;
        }
        for (Territory territory : territories.values()) {
            if (territory.getOwner() != owner) {
                return null;
            }
        }
        return owner;
    }

    /**
     * Check if the continent is owned by the player
     * 
     * @aurhor Mauro Zozin
     * @param player the player to check
     * @return true if the continent is owned by the player, false otherwise
     * 
     */
    public boolean isOwnedBy(Player player) {
        return getOwner() == player;
    }

    /**
     * Check if the continent is owned
     * 
     * @aurhor Mauro Zozin
     * @return true if the continent is owned, false otherwise
     */
    public boolean isOwned() {
        return getOwner() != null;
    }

    /**
     * Check if the territory is in the continent
     * 
     * @aurhor Mauro Zozin
     * @param territory the territory to check
     * @return true if the territory is in the continent, false otherwise
     * 
     */
    public boolean isTerritoryInContinent(Territory territory) {
        if (territory == null)
            return false;
        return territories.containsValue(territory);
    }

    /**
     * Check if the territory is in the continent
     * 
     * @aurhor Mauro Zozin
     * @param territoryId the id of the territory to check
     * @return true if the territory is in the continent, false otherwise
     * 
     */
    public boolean isTerritoryInContinent(String territoryId) {
        if (territoryId == null || territoryId.isEmpty() || territoryId.isBlank())
            return false;
        return territories.containsKey(territoryId);
    }

    /**
     * Get the territory from the continent
     * 
     * @aurhor Mauro Zozin
     * @param territoryId the id of the territory to get
     * @return the territory, null if the territory is not in the continent
     * 
     */
    public Territory getTerritory(String territoryId) {
        return territories.get(territoryId);
    }

    /**
     * Add a territory to the continent
     * 
     * @aurhor Mauro Zozin
     * @param territory the territory to add
     * 
     */
    public void addTerritory(Territory territory) throws IllegalArgumentException {
        if (territory == null) {
            throw new IllegalArgumentException("The territory is null");
        }
        if (territory.getName().isEmpty() || territory.getName().isBlank()) {
            throw new IllegalArgumentException("The territory id is empty");
        }
        if (territories.containsKey(territory.getName())) {
            throw new IllegalArgumentException(
                    "The territory id is already in the continent " + territory.getName() + "  " + name);
        }
        if (territories.containsValue(territory)) {
            throw new IllegalArgumentException(
                    "The territory is already in the continent" + territory.getName() + "" + name);
        }

        territories.put(territory.getName(), territory);
    }

    /**
     * Remove a territory from the continent
     * 
     * @aurhor Mauro Zozin
     * @param territoryId the id of the territory to remove
     * @return the territory removed, null if the territory is not in the continent
     * 
     */
    public Territory removeTerritory(String territoryId) {
        return territories.remove(territoryId);
    }

    /**
     * Remove a territory from the continent
     * 
     * @aurhor Mauro Zozin
     * @param territory the territory to remove
     * @return the territory removed, null if the territory is not in the continent
     * 
     */
    public Territory removeTerritory(Territory territory) {
        return removeTerritory(
                territories.keySet().stream().filter(key -> territories.get(key) == territory).findFirst()
                        .orElse(null));
    }

    /**
     * Get the number of territories in the continent
     * 
     * @aurhor Mauro Zozin
     * @return the number of territories in the continent
     * 
     */
    public int getNumberOfTerritories() {
        return territories.size();
    }

    /**
     * Get the number of territories owned by the player
     * 
     * @aurhor Mauro Zozin
     * @param player the player to check
     * @return the number of territories owned by the player
     * 
     */
    public int getNumberOfTerritories(Player player) {
        if (player == null) {
            return 0;
        }
        return (int) territories.values().stream().filter(territory -> territory.getOwner() == player).count();
    }

    /**
     * Get the territories owned by the player
     * 
     * @aurhor Mauro Zozin
     * @param player the player to check
     * @return the territories owned by the player
     * 
     */
    public Collection<Territory> getTerritories(Player player) {
        if (player == null) {
            return new ArrayList<>();
        }
        Collection<Territory> territoriesTemporaneo = new ArrayList<>();
        for (Territory territory : this.territories.values()) {
            if (territory.getOwner() == player) {
                territoriesTemporaneo.add(territory);
            }
        }
        return territoriesTemporaneo;
    }

    /**
     * Set all the territories in the continent to the player
     * 
     * 
     * @aurhor Mauro Zozin
     * @param player the player to set
     * @return
     * @throws IllegalArgumentException if the territory is already owned by someone
     * 
     */
    public void setOwner(Player owner) throws IllegalArgumentException {
        for (Territory territory : territories.values()) {
            territory.setOwner(owner);
        }
    }
}
