package com.project.progettorisikorisikiamobackend.obiettivi;

import jakarta.annotation.Nonnull;

@Nonnull

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
        try {
            if (player.getName() == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Player non esiste");
            return false;
        }
        // TODO: implementare metodo isDefeatedBy(PlayerPlaceholder player)
        return false;
    }

    public PlayerPlaceholder getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerPlaceholder opponent) {
        this.opponent = opponent;
    }
    
}
