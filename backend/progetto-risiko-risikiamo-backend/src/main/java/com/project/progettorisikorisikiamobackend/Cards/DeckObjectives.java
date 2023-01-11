package com.project.progettorisikorisikiamobackend.Cards;
import java.util.Map;

public class DeckObjectives extends Deck {
    public DeckObjectives(Map<CardObjective, String> Obiectives) {
        super(Obiectives);
    }
  
    @Override
    public CardObjective draw() {
        return getCards().keySet().toArray(new CardObjective[0])[0];
    }
}