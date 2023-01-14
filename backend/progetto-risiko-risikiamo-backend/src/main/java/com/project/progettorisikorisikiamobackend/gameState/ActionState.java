package com.project.progettorisikorisikiamobackend.gameState;

import javax.swing.Icon;

import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

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

            // TODO : Pesco la carta territorio, fa niente se sarebbe a fine turno

            this.haveConquest = true;
        }

    }

    @Override
    public void move(Territory owner, Territory neighbor, int army) {

        Player p = context.getTurn().getCurrentPlayer();

        p.move(owner, neighbor, army);

        context.setState(new NewTurnState(context));

    }

    @Override
    public void passTurn() {

        context.getTurn().nextTurn();
        context.setState(new NewTurnState(context));

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
