package com.project.progettorisikorisikiamobackend.gameState;

//import
import com.project.progettorisikorisikiamobackend.Turno.*;
import java.util.Scanner;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RenforceState implements IState {

    private IContext context;

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
        context.setState(new NewTurnState(context));
        context.getTurn().nextTurn();
    }

    @Override
    public void redeemReinforcementsCard() {
        throw new UnsupportedOperationException("Non puoi muovere in questo stato");
    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        Player player = context.getTurn().getCurrentPlayer();
        player.placeReinforcements(ownTerritory, armies);

        if (player.getReinforce() == 0) {
            context.setState(new ActionState(context));
        }
    }

}
