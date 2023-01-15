package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.*;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewTurnState implements IState {

    private IContext context;

    @Override
    public void attack(Territory owner, Territory neighbor, int army) {
        throw new UnsupportedOperationException("Can't attack in at this moment");
    }

    @Override
    public void move(Territory owner, Territory neighbor, int army) {
        throw new UnsupportedOperationException("Can't move in at this moment");

    }

    @Override
    public void passTurn() {
        context.getTurn().nextTurn();
        context.setState(new NewTurnState(context));

    }

    @Override
    public void redeemReinforcementsCard() {

        Player p = context.getTurn().getCurrentPlayer();
        p.redeemReinforcementsCard();

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        Player p = context.getTurn().getCurrentPlayer();
        p.placeReinforcements(ownTerritory, armies);
        context.setState(new RenforceState(context));
    }

}
