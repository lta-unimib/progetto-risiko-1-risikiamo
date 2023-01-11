package com.project.progettorisikorisikiamobackend.obiettivi;
import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;

import lombok.Getter;

@Getter
public class OpponentDefeated implements Objective {
    private PlayerPlaceholder opponent;

    public OpponentDefeated(PlayerPlaceholder opponent) {
        this.opponent = opponent;

    }

    @Override
    public String getObjDescription() {
        return "Sconfiggi il giocatore " + opponent.getName();
    }

    @Override
    public boolean isCompleted(PlayerPlaceholder player) throws NullPointerException{
        
        if (player == null) {
            return false;
        }
        // TODO: implementare metodo isDefeatedBy(PlayerPlaceholder player)
        return true;
    }
    
}
