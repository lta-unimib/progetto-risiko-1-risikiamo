package com.project.progettorisikorisikiamobackend.obiettivi;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Getter;

@Getter
public class OpponentDefeated implements Objective {
    private Player opponent;

    public OpponentDefeated(Player opponent) {
        this.opponent = opponent;

    }

    @Override
    public String getObjDescription() {
        return "Sconfiggi il giocatore " + opponent.getName();
    }

    @Override
    public boolean isCompleted(Player player) throws NullPointerException{
        
        if (player == null) {
            return false;
        }
        // TODO: implementare metodo isDefeatedBy(Player player)
        return true;
    }
    
}
