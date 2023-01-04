package com.project.progettorisikorisikiamobackend.obiettivi;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Getter;


@Getter

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
    public boolean isCompleted(Player player){
            if (player == null) {
                return false;
            }

        return this.numberTerritories <= map.getNumberOfTerritories(player);
    }
    
}
