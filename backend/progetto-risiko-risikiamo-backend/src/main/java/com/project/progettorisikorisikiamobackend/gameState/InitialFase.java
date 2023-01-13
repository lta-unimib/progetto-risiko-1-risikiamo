package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;

import com.project.progettorisikorisikiamobackend.map.Territory;

import lombok.AllArgsConstructor;

public class InitialFase implements IState {

    IContext context;
    int reinforcementToPlace;

    public InitialFase(IContext context) {
        this.context = context;
        this.reinforcementToPlace = 3;
    }

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

    }

    @Override
    public void redeemReinforcementsCard() {
        throw new UnsupportedOperationException("Non puoi redimere in questo stato");

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        // Logica pizzare rinforzi
        if (reinforcementToPlace > 0 && armies <= reinforcementToPlace) {
            ownTerritory.addArmy(ownTerritory.getArmy() + armies);
            reinforcementToPlace -= armies;
        }

        if (reinforcementToPlace == 0) {
            context.setState(new InitialFase(context));
        }
    }

}
