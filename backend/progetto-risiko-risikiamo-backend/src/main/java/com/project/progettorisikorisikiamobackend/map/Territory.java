package com.project.progettorisikorisikiamobackend.map;

import java.util.ArrayList;

import com.project.progettorisikorisikiamobackend.player.Player;

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
    private ArrayList<Territory> neighbours;
    private Player owner;

    private Territory(String name, int army, ArrayList<Territory> neighbours, Player owner) {

        this.name = name;
        this.army = army;
        this.neighbours = neighbours;
        this.owner = owner;
    }

    public Territory(String name) {
        this(name, 0, new ArrayList<>(), null);
    }

    public Territory(String name, Player owner) {
        this(name, 0, new ArrayList<>(), owner);
    }

    /**
     * Set the owner of the territory, if the army is = 0
     * 
     * @author - Mauro Zorzin
     * @param owner
     * @throws IllegalArgumentException if owner is null
     * @throws IllegalArgumentException if territory have an owner and army > 0
     */
    public void setOwner(Player owner) throws IllegalArgumentException {
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
     * @return
     * @throws IllegalArgumentException if owner is null
     * @throws IllegalArgumentException if army become negative
     */
    public void addArmy(int army) throws IllegalArgumentException {

        if (this.owner == null) {
            throw new IllegalArgumentException("Territory don't have an owner");
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
     * @return true if the territory is a neighbour of this territory
     */
    public boolean isNeighbour(Territory territory) {

        for (Territory neighbourTemporanei : this.neighbours) {
            if (neighbourTemporanei.equals(territory)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author - Mauro Zorzin
     * @param territoryName
     * @return true if the territory is a neighbour of this territory
     */
    public boolean isNeighbour(String territoryName) {

        for (Territory neighbourTemporanei : this.neighbours) {
            if (neighbourTemporanei.getName().equals(territoryName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author - MauroZorzin
     * @return the neighbours of this territory not owned by the owner of this
     *         territory
     */
    public Territory[] getNotOwnedNeighbour() {
        int count = 0;
        for (Territory neighbourTemporanei : this.neighbours) {
            if (neighbourTemporanei.getOwner() != this.owner) {
                count++;
            }
        }
        Territory[] notOwnedTerritories = new Territory[count];
        count = 0;
        for (Territory neighbourTemporanei : this.neighbours) {
            if (neighbourTemporanei.getOwner() != this.owner) {
                notOwnedTerritories[count] = neighbourTemporanei;
                count++;
            }
        }
        return notOwnedTerritories;
    }

    /**
     * @author - Mauro Zorzin
     * @return the neighbours of this territory owned by the owner of this territory
     */
    public Territory[] getOwnedNeighbour() {
        int count = 0;
        for (Territory neighbourTemporanei : this.neighbours) {
            if (neighbourTemporanei.getOwner() == this.owner) {
                count++;
            }
        }
        Territory[] ownedTerritories = new Territory[count];
        count = 0;
        for (Territory neighbourTemporanei : this.neighbours) {
            if (neighbourTemporanei.getOwner() == this.owner) {
                ownedTerritories[count] = neighbourTemporanei;
                count++;
            }
        }
        return ownedTerritories;
    }

    /**
     * Add a neighbour to the neighbours list
     * 
     * @author - Mauro Zorzin
     * @param territory the neighbour to add
     */
    public void addNeighbour(Territory territory) throws IllegalArgumentException {
        if (territory == this)
            throw new IllegalArgumentException("Neighbour Territory can't be itself " + territory.getName());
        if (territory == null)
            throw new IllegalArgumentException("Neighbour Territory can't be null " + this.getName());
        if (this.isNeighbour(territory)) {
            throw new IllegalArgumentException("Neighbour Territory already a neighbour " + territory.getName());
        }
        this.neighbours.add(territory);
    }

    /**
     * Remove a neighbour from the neighbours list
     * 
     * @author - Mauro Zorzin
     * @param territory the neighbour to remove
     */
    public void removeNeighbour(Territory territory) {
        this.neighbours.remove(territory);
    }

}
