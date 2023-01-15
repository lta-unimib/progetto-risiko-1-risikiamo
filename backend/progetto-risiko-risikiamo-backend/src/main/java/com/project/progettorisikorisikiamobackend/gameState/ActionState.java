package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Getter;

@Getter
public class ActionState implements IState {

    private IContext context;
    private boolean haveConquest;

    public ActionState(IContext context) {
        this.context = context;
        this.haveConquest = false;
    }

    @Override
    public void attack(Territory owner, Territory neighbor, int army) {

        Player p = context.getTurn().getCurrentPlayer();

        p.attack(owner, neighbor, army);

        if (neighbor.getOwner() == p) {

            this.haveConquest = true;
        }

    }

    @Override
    public void move(Territory owner, Territory neighbor, int army) {

        Player p = context.getTurn().getCurrentPlayer();

        p.move(owner, neighbor, army);

        this.endTurn();

    }

    @Override
    public void endTurn() {

        if (this.haveConquest && !context.getDeckTerritories().getDeck().isEmpty()) {
            Player p = context.getTurn().getCurrentPlayer();

            CardTerritory t = context.getDeckTerritories().draw();

            p.addCard(t);
        }

        context.getTurn().nextTurn();
        context.setState(new NewTurnState(context));

    }

    @Override
    public void redeemReinforcementsCard(CardTerritory c1, CardTerritory c2, CardTerritory c3) {

        throw new UnsupportedOperationException("Non puoi muovere in questo stato");

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        throw new UnsupportedOperationException("Non puoi muovere in questo stato");

    }

}
