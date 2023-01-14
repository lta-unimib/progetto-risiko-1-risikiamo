package com.project.progettorisikorisikiamobackend.cards;

import com.project.progettorisikorisikiamobackend.player.*;

import lombok.*;
public abstract class Card {
   @Getter@Setter private String name;
   @Getter@Setter private String description;
   @Getter@Setter private String image;
   @Getter @Setter private Player owner;
   

    Card(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

   @Override
   public String toString() {
       
       return this.toString();
   }
   
   
    
}
