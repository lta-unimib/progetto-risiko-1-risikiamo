package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
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
    public void endTurn() {
        context.getTurn().nextTurn();
        context.setState(new NewTurnState(context));

    }

    @Override
    public void redeemReinforcementsCard(CardTerritory c1, CardTerritory c2, CardTerritory c3) {

        Player p = context.getTurn().getCurrentPlayer();

        DeckTerritories deck = context.getDeckTerritories();
        int r = deck.reedemCards(c1, c2, c3, p);

        p.setReinforce(r);

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        Player p = context.getTurn().getCurrentPlayer();
        p.placeReinforcements(ownTerritory, armies);
        context.setState(new RenforceState(context));
    }

}
