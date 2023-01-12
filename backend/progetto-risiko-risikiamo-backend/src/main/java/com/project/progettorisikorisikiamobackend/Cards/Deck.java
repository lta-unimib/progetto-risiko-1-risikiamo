package com.project.progettorisikorisikiamobackend.Cards;

import java.util.*;
import lombok.*;
import com.project.progettorisikorisikiamobackend.Turno.*;
public  abstract class Deck {
    private @Getter @Setter List<Card> cards;
    private @Getter @Setter Turn turno;

    Deck(List<Card> cards, Turn turno) {
        this.cards = cards;
        this.turno = turno;
    }
    
    public  abstract void draw();
        
}

