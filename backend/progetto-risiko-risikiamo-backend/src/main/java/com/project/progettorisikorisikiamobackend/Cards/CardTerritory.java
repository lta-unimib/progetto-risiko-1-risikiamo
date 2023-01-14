package com.project.progettorisikorisikiamobackend.cards;


import lombok.*;
@Getter 
public class CardTerritory  {
private String name;
private EnumCard type;

    
    public CardTerritory(String name , EnumCard type) {
        this.name = name;
        this.type = type;
        
    }
    
   
}
