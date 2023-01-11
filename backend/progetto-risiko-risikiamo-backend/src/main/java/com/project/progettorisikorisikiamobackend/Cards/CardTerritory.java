package com.project.progettorisikorisikiamobackend.Cards;
import com.project.progettorisikorisikiamobackend.map.Territory;
import lombok.*;

public class CardTerritory  {

    private @Getter Territory territory;
    private @Getter @Setter String value;
    
    public CardTerritory(Territory territory, String value) {
        this.territory = territory;
        this.value = value;
    }

    public String toString() {
        return "Territory: " + territory.getName() + " Value: " + value;
    }
}