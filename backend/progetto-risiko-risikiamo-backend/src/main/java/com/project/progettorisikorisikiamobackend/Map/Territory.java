package com.project.progettorisikorisikiamobackend.Map;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

/**
 * Territory class
 * 
 * @author - Mauro Zorzin
 * @version 1.0
 */
@EqualsAndHashCode
@Getter
public class Territory {

    @NonNull
    private final String name;
    private int army;
    private ArrayList<Territory> neighbors;
    private PlayerPlaceholder owner;

    private Territory(String name, int army, ArrayList<Territory> neighbors, PlayerPlaceholder owner) {

        this.name = name;
        this.army = army;
        this.neighbors = neighbors;
        this.owner = owner;
    }

    public Territory(String name) {
        this(name, 0, new ArrayList<>(), null);
    }

    /**
     * Set the owner of the territory, if the army is = 0
     * 
     * @author - Mauro Zorzin
     * @param owner
     * @throws IllegalArgumentException if owner is null
     * @throws IllegalArgumentException if territory have an owner and army > 0
     */
    public void setOwner(PlayerPlaceholder owner) throws IllegalArgumentException {
        if (owner == null) {
            throw new IllegalArgumentException("Owner can't be null");
        }
        if (this.owner != null && this.army > 0) {
            throw new IllegalArgumentException("Territory already have an owner");
        }
        this.owner = owner;
    }

    /**
     * Add army to the territory, if the army become = 0 the owner is set null
     * 
     * @author - Mauro Zorzin
     * @param army
     * @throws IllegalArgumentException if owner is null
     * @throws IllegalArgumentException if army become negative
     */
    public void addArmy(int army) throws IllegalArgumentException {

        if (this.owner == null) {
            throw new IllegalArgumentException("Territory must have an owner");
        }
        if (this.army + army < 0) {
            throw new IllegalArgumentException("Army can't be negative");
        } else if (this.army + army == 0) {
            this.owner = null;
        }

        this.army += army;
    }

    /**
     * @author - Mauro Zorzin
     * @param territory
     * @return true if the territory is a neighbor of this territory
     */
    public boolean isNeighbor(Territory territory) {
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.equals(territory)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author - Mauro Zorzin
     * @param territoryName
     * @return true if the territory is a neighbor of this territory
     */
    public boolean isNeighbor(String territoryName) {
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.getName().equals(territoryName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author - MauroZorzin
     * @return the neighbors of this territory not owned by the owner of this
     *         territory
     */
    public Territory[] getNotOwnedNeighbor() {
        int count = 0;
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.getOwner() != this.owner) {
                count++;
            }
        }
        Territory[] notOwnedTerritories = new Territory[count];
        count = 0;
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.getOwner() != this.owner) {
                notOwnedTerritories[count] = neighborTemporanei;
                count++;
            }
        }
        return notOwnedTerritories;
    }

    /**
     * @author - Mauro Zorzin
     * @return the neighbors of this territory owned by the owner of this territory
     */
    public Territory[] getOwnedNeighbor() {
        int count = 0;
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.getOwner() == this.owner) {
                count++;
            }
        }
        Territory[] ownedTerritories = new Territory[count];
        count = 0;
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.getOwner() == this.owner) {
                ownedTerritories[count] = neighborTemporanei;
                count++;
            }
        }
        return ownedTerritories;
    }

    /**
     * Add a neighbor to the neighbors list
     * 
     * @author - Mauro Zorzin
     * @param territory the neighbor to add
     */
    public void addNeighbor(Territory territory) throws IllegalArgumentException {
        if (territory == this)
            throw new IllegalArgumentException("Territory can't be itself");
        if (territory == null)
            throw new IllegalArgumentException("Territory can't be null");
        if (this.isNeighbor(territory)) {
            throw new IllegalArgumentException("Territory already a neighbor");
        }
        this.neighbors.add(territory);
    }

    /**
     * Remove a neighbor from the neighbors list
     * 
     * @author - Mauro Zorzin
     * @param territory the neighbor to remove
     */
    public void removeNeighbor(Territory territory) {
        this.neighbors.remove(territory);
    }

}
