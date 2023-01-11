package com.project.progettorisikorisikiamobackend.Cards;
import java.util.Map;



public class DeckTerritories extends Deck {
    
    public DeckTerritories(Map<CardTerritory, String> territories) {
        super(territories);
    }
    
    @Override
    public CardTerritory draw() {
        return getCards().keySet().toArray(new CardTerritory[0])[0];
    
    }
}
    

