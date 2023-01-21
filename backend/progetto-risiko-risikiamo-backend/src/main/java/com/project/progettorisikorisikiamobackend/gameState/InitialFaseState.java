package com.project.progettorisikorisikiamobackend.gameState;

import java.util.List;

import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;

import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Getter;

public class InitialFaseState implements IState {

    IContext context;
    @Getter
    int reinforcementToPlace;

    public InitialFaseState(IContext context) {
        this.context = context;
        this.reinforcementToPlace = 3;

        List<Player> playerList = context.getTurn().getInGamePlayerList();
        boolean flag = false;
        for (Player p : playerList) {
            if (p.getReinforce() > 0) {
                flag = true;
            }
        }

        if (!flag) {
            context.setState(new NewTurnState(context));
            context.getTurn().nextTurn();
        }

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        Player player = context.getTurn().getCurrentPlayer();
        List<Player> playerList = context.getTurn().getInGamePlayerList();

        boolean flag = false;

        // Logica pizzare rinforzi
        if (reinforcementToPlace > 0 && armies <= reinforcementToPlace && player.getReinforce() >= armies) {

            player.placeReinforcements(ownTerritory, armies);

            reinforcementToPlace = reinforcementToPlace - armies;

        } else {
            throw new IllegalArgumentException("Non puoi piazzare piu rinforzi di quelli che hai a disposizione");
        }
        for (Player p : playerList) {
            if (p.getReinforce() > 0) {
                flag = true;
            }
        }

        if (!flag) {
            context.getTurn().nextTurn();
            context.setState(new NewTurnState(context));
            return;
        }

        if (reinforcementToPlace <= 0 || player.getReinforce() <= 0) {
            context.getTurn().nextTurn();
            context.setState(new InitialFaseState(context));

        }

    }

}
