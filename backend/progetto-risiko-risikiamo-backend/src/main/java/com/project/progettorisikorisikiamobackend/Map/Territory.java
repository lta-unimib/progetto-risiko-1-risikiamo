package com.project.progettorisikorisikiamobackend.Map;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//Risiko Territory
@EqualsAndHashCode
@ToString
@Getter
public class Territory {

    private final String name;
    private int army;
    private final Territory[] neighbors;
    @Nullable
    private PlayerPlaceholder owner;

    private Territory(String name, int army, Territory[] neighbors, PlayerPlaceholder owner) {
        this.name = name;
        this.army = army;
        this.neighbors = neighbors;
        this.owner = owner;
    }

    public Territory(String name, Territory[] neighbors) {
        this(name, 0, neighbors, null);
    }

    public void setOwner(PlayerPlaceholder owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner can't be null");
        }
        if (this.owner != null && this.army > 0) {
            throw new IllegalArgumentException("Territory already have an owner");
        }
        this.owner = owner;
    }

    public void addArmy(int army) {

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

    public boolean isNeighbor(Territory territory) {
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.equals(territory)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNeighbor(String territoryName) {
        for (Territory neighborTemporanei : this.neighbors) {
            if (neighborTemporanei.getName().equals(territoryName)) {
                return true;
            }
        }
        return false;
    }

    public Territory[] notOwnedNeighbor() {
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

    public Territory[] ownedNeighbor() {
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

}
