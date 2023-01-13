package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Territory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewGameState implements IState {

    @Override
    public void attack(Territory owner, Territory neighbor, int army) {
        throw new UnsupportedOperationException("Non puoi attaccare in questo stato");

    }

    @Override
    public void move(Territory owner, Territory neighbor, int army) {
        throw new UnsupportedOperationException("Non puoi muovere in questo stato");

    }

    @Override
    public void passTurn() {
        throw new UnsupportedOperationException("Non puoi muovere in questo stato");

    }

    @Override
    public void redeemReinforcementsCard() {
        throw new UnsupportedOperationException("Non puoi muovere in questo stato");

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {
        throw new UnsupportedOperationException("Non puoi muovere in questo stato");

    }

}
