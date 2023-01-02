package com.project.progettorisikorisikiamobackend.obiettivi;
import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;


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
        
            if (player.getName() == null) {
            return false;
            }
        // TODO: implementare metodo isDefeatedBy(PlayerPlaceholder player)
        return true;
    }

    public PlayerPlaceholder getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerPlaceholder opponent) {
        this.opponent = opponent;
    }
    
}
