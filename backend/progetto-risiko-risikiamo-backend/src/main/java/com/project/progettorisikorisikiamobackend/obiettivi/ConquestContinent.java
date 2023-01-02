package com.project.progettorisikorisikiamobackend.obiettivi;

import jakarta.annotation.Nonnull;

@Nonnull

public class ConquestContinent implements Objective {
    private ContinentPlaceholder continent;

    public ConquestContinent(ContinentPlaceholder continent) {
        this.continent = continent;
    }

    @Override
    public String getObjDescription() {
        return "Ottieni il continente " + continent.getName();
    }

    @Override
    public boolean isCompleted(PlayerPlaceholder player) throws NullPointerException {
        try {
            if (player.getName() == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Il continente non ha un proprietario");
            return false;
        }
        return this.continent.getOwner().equals(player);
    }

    

    public ContinentPlaceholder getContinent() {
        return continent;
    }

    public void setContinent(ContinentPlaceholder continent) {
        this.continent = continent;
    }
    
}
