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

        Player p2 = neighbor.getOwner();

        p.attack(owner, neighbor, army);

        if (neighbor.getOwner() == p) {

            this.haveConquest = true;
        }

        int nTer = context.getMap().getNumberOfTerritories(p2);

        if (nTer == 0) {
            context.getTurn().defeatPlayer(p, p2);
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
            CardTerritory t2 = context.getDeckTerritories().draw();
            CardTerritory t3 = context.getDeckTerritories().draw();
            CardTerritory t4 = context.getDeckTerritories().draw();
            CardTerritory t5 = context.getDeckTerritories().draw();
            CardTerritory t6 = context.getDeckTerritories().draw();
            CardTerritory t7 = context.getDeckTerritories().draw();
            CardTerritory t8 = context.getDeckTerritories().draw();
            CardTerritory t9 = context.getDeckTerritories().draw();
            CardTerritory t10 = context.getDeckTerritories().draw();
            CardTerritory t11 = context.getDeckTerritories().draw();
            CardTerritory t12 = context.getDeckTerritories().draw();

            p.addCard(t);
            p.addCard(t2);
            p.addCard(t3);
            p.addCard(t4);
            p.addCard(t5);
            p.addCard(t6);
            p.addCard(t7);
            p.addCard(t8);
            p.addCard(t9);
            p.addCard(t10);
            p.addCard(t11);
            p.addCard(t12);
        }

        context.getTurn().nextTurn();
        context.setState(new NewTurnState(context));

    }
}
