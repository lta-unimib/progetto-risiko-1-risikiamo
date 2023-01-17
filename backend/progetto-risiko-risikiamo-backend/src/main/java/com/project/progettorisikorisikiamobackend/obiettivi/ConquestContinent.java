package com.project.progettorisikorisikiamobackend.obiettivi;

import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Getter;

import com.project.progettorisikorisikiamobackend.map.Continent;

@Getter
public class ConquestContinent implements Objective {
    private Continent continent;

    public ConquestContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public String getObjDescription() {
        return "Conquista il continente " + continent.getName();
    }

    @Override
    public boolean isCompleted(Player player) {
        
            if (player == null || continent.getOwner() == null) {
                return false;
            }
        return this.continent.getOwner().equals(player);
    }
}
