package com.project.progettorisikorisikiamobackend.Cards;
import java.util.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import lombok.*;

public class DeckTerritories extends Deck {
    
    public DeckTerritories(Map<Territory, String> territories) {
        super(territories);
    }
    @Override
    public void description(Object territory) {
        territory = (Territory) territory;
        getCards().get(territory);
        }
    @Override
    public Object draw() {
        return getCards().keySet().toArray()[0];
    
    }
}
    

