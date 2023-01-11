package com.project.progettorisikorisikiamobackend.Cards;

import java.util.Map;
import lombok.*;
public  abstract class Deck {
    private @Getter Map<?, String> cards;
    Deck(Map< ? , String> cards){
        this.cards = cards;
    }
    
    public abstract Object draw();
   
}
