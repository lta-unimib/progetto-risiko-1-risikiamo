package com.project.progettorisikorisikiamobackend.obiettivi;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;




public class NumTerritories implements Objective {
    private int numberTerritories;
    private Map map;

    public NumTerritories(int numTerritories, Map map) {
        this.numberTerritories = numTerritories;
        this.map = map;
  
    }

    @Override
    public String getObjDescription() {
        return "Ottieni " + numberTerritories + " territori";
    }

    @Override
    public boolean isCompleted(PlayerPlaceholder player){
            if (player.getName() == null) {
                return false;
            }

        return this.numberTerritories <= map.getNumberOfTerritories(player);
    }
    

    public int getNumTerritories() {
        return numberTerritories;
    }

    public void setNumTerritories(int numTerritories) {
        this.numberTerritories = numTerritories;
    }
    
}
