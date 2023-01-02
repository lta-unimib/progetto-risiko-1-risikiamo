package com.project.progettorisikorisikiamobackend.obiettivi;

import java.util.ArrayList;

public class TerritoryPlaceholder {
    private final String name;
    private int army;
    private ArrayList<TerritoryPlaceholder> neighbors;
    private PlayerPlaceholder owner;

    private TerritoryPlaceholder(String name, int army, ArrayList<TerritoryPlaceholder> neighbors, PlayerPlaceholder owner) {

        this.name = name;
        this.army = army;
        this.neighbors = neighbors;
        this.owner = owner;
    }

    public PlayerPlaceholder getOwner() {
        return this.owner;
    }

}
