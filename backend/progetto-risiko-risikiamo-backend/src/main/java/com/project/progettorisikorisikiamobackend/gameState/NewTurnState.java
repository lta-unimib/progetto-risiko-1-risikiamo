package com.project.progettorisikorisikiamobackend.gameState;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.*;
import com.project.progettorisikorisikiamobackend.player.Player;

public class NewTurnState implements IState {

    private IContext context;

    public NewTurnState(IContext context) {
        this.context = context;

        Player p = context.getTurn().getCurrentPlayer();
        Map m = context.getMap();

        p.setReinforce(p.getReinforce() + (m.getNumberOfTerritories(p) / 3));

        // claculate the ammount of bousus armies
        int bonus = 0;
        for (Continent c : m.getContinents().values()) {
            if (c.isOwnedBy(p)) {
                bonus += c.getBonus();
            }
        }
        p.setReinforce(p.getReinforce() + bonus);

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

        p.setReinforce(p.getReinforce() + r);

        p.removeCard(c1);
        p.removeCard(c2);
        p.removeCard(c3);

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        Player p = context.getTurn().getCurrentPlayer();

        if (context.getTurn().getCurrentPlayer().getReinforce() <= 0) {
            context.setState(new ActionState(context));
        }

        p.placeReinforcements(ownTerritory, armies);
        if (context.getTurn().getCurrentPlayer().getReinforce() <= 0) {
            context.setState(new ActionState(context));
        } else {
            context.setState(new RenforceState(context));
        }

    }

}
