package com.project.progettorisikorisikiamobackend.cards;

import com.project.progettorisikorisikiamobackend.obiettivi.Objective;
import lombok.*;
public class CardObjective extends Card {

    private @Getter @Setter Objective objective;
    

    public CardObjective(Objective objective, String name, String image) {
        super(name,objective.getObjDescription(), image);
        this.objective = objective;
       
       
        
    }


   
    
}