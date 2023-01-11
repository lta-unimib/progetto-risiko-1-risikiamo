package com.project.progettorisikorisikiamobackend.obiettivi;

import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;

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
    public boolean isCompleted(PlayerPlaceholder player) {
        
            if (player == null || continent.getOwner() == null) {
                return false;
            }
        return this.continent.getOwner().equals(player);
    }
}
