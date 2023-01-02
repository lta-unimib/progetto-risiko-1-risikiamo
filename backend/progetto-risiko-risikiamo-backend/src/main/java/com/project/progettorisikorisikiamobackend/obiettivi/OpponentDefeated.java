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
    public boolean isCompleted(ContinentPlaceholder continent) throws NullPointerException{
        try {
            if (continent.getOwner() == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Il continente non ha un proprietario");
            return completed;
        }
        if (continent.getNumberOfTerritories(opponent) == 0) {
            setCompleted();
        }

        return completed;
    }

    @Override
    public void setCompleted() {
        this.completed = true;
    }

    public PlayerPlaceholder getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerPlaceholder opponent) {
        this.opponent = opponent;
    }
    
}
