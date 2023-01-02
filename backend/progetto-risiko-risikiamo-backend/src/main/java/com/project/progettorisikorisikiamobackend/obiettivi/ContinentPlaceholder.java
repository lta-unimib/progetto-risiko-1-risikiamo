package com.project.progettorisikorisikiamobackend.obiettivi;

import java.util.HashMap;

public class ContinentPlaceholder {
    

        private String name;
        private int bonus;
        private HashMap<String, TerritoryPlaceholder> territories;
    
        // Constructor
        public ContinentPlaceholder(String name, int bonus) {
            this.name = name;
            this.bonus = bonus;
            territories = new HashMap<>();

        }
        public int getNumberOfTerritories(PlayerPlaceholder player) {
            if (player == null) {
                return 0;
            }
            return (int) territories.values().stream().filter(territory -> territory.getOwner() == player).count();
        }
        public PlayerPlaceholder getOwner() {
            if (territories.isEmpty()) {
                return null;
            }
            PlayerPlaceholder owner = territories.values().iterator().next().getOwner();
            if (owner == null) {
                return null;
            }
            for (TerritoryPlaceholder territory : territories.values()) {
                if (territory.getOwner() != owner) {
                    return null;
                }
            }
            return owner;
        }
        public String getName() {
            return this.name;
        }
    
}
