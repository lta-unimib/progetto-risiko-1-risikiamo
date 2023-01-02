package com.project.progettorisikorisikiamobackend.obiettivi;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;




public class TotTerritories implements Objective {
    private int numberTerritories;
    private Map map;

    public TotTerritories(int numTerritories, Map map) {
        this.numberTerritories = numTerritories;
        this.map = map;
  
    }

    @Override
    public String getObjDescription() {
        return "Conquista " + numberTerritories + " territori";
    }

    @Override
    public boolean isCompleted(PlayerPlaceholder player){
            if (player.getName() == null) {
                return false;
            }

        return this.numberTerritories <= map.getNumberOfTerritories(player);
    }
    

    public int getTotTerritories() {
        return numberTerritories;
    }

    public void setTotTerritories(int numTerritories) {
        this.numberTerritories = numTerritories;
    }
    
}
