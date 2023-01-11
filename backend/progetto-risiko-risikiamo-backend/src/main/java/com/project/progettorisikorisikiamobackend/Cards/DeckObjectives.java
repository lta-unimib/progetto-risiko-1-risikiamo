package com.project.progettorisikorisikiamobackend.Cards;
import java.util.Map;

import com.project.progettorisikorisikiamobackend.obiettivi.Objective;
public class DeckObjectives extends Deck {
    public DeckObjectives(Map<Objective, String> Obiectives) {
        super(Obiectives);
    }
    @Override
    public void description(Object objective) {
        objective = (Objective) objective;
        getCards().get(objective);
    }
    @Override
    public Object draw() {
        return getCards().keySet().toArray()[0];
    }
}