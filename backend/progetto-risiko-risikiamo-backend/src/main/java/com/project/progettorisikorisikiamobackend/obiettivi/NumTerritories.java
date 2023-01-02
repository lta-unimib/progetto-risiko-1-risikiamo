package com.project.progettorisikorisikiamobackend.obiettivi;

import jakarta.annotation.Nonnull;

@Nonnull

public class NumTerritories implements Objective {
    private int numberTerritories;


    public NumTerritories(int numTerritories) {
        this.numberTerritories = numTerritories;
  
    }

    @Override
    public String getObjDescription() {
        return "Ottieni " + numberTerritories + " territori";
    }

    @Override
    public boolean isCompleted(PlayerPlaceholder player) throws NullPointerException{
        try {
            if (player.getName() == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Il Player non esiste");
            return false;
        }

        return this.numberTerritories <= player.TerritoriesOwned();
    }
    

    public int getNumTerritories() {
        return numberTerritories;
    }

    public void setNumTerritories(int numTerritories) {
        this.numberTerritories = numTerritories;
    }
    
}
