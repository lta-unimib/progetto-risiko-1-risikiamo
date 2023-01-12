package com.project.progettorisikorisikiamobackend.Cards;

import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import com.project.progettorisikorisikiamobackend.Turno.*;

public class DeckTerritories extends Deck {
    private @Getter List <CardTerritory> discartedCards;

    public DeckTerritories(List<Card> territoriesCards, Turn turno) {
       super(territoriesCards, turno);
        this.discartedCards = new ArrayList<>();
    }
    
    @Override
    public void draw(){
        if(this.getCards().isEmpty()) {
            for (Card cardTerritory : discartedCards) {
                 this.getCards().add(cardTerritory);
                 this.discartedCards.remove(cardTerritory);
            }
             this.discartedCards.clear();
         
         CardTerritory pescata = (CardTerritory) getCards().get(0);
         
         getCards().remove(pescata);
         pescata.setOwner(( this.getTurno().getCurrentPlayer()));
        this.getTurno().getCurrentPlayer().getCardsOnHand().add(pescata);
         
        }
}
}

