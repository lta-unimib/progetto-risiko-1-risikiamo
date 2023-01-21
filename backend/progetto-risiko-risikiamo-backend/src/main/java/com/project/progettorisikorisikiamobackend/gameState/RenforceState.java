package com.project.progettorisikorisikiamobackend.gameState;

//import
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class RenforceState implements IState {

    private IContext context;

    @Override
    public void endTurn() {
        context.setState(new NewTurnState(context));
        context.getTurn().nextTurn();
    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        Player player = context.getTurn().getCurrentPlayer();
        if (player.getReinforce() <= 0) {
            context.setState(new ActionState(context));
        }
        player.placeReinforcements(ownTerritory, armies);

        if (player.getReinforce() <= 0) {
            context.setState(new ActionState(context));
        }
    }

}
