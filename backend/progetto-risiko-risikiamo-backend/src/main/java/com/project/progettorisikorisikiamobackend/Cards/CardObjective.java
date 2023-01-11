package com.project.progettorisikorisikiamobackend.Cards;
import com.project.progettorisikorisikiamobackend.obiettivi.Objective;
import lombok.*;
public class CardObjective  {

    private @Getter @Setter Objective objective;
    private @Getter @Setter String description;

    public CardObjective(Objective objective) {
        this.objective = objective;
        this.description =  objective.getObjDescription();
    }
    @Override
    public String toString() {
        return "Objective: "  + this.description;
    }
}