package com.project.progettorisikorisikiamobackend.Cards;
import com.project.progettorisikorisikiamobackend.map.Territory;
import lombok.*;

public class CardTerritory extends Card {

    private @Getter Territory territory;
    private @Getter @Setter String value;
    
    public CardTerritory(Territory territory, String value, String name, String description, String image) {
        super(name, description, image);
        this.territory = territory;
        this.value = value;
    }
    
   
}
