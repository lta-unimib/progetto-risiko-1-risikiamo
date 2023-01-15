package com.project.progettorisikorisikiamobackend.gameState;

import java.util.Set;

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
        throw new UnsupportedOperationException("Non puoi passare il turno in questo stato");
    }

    @Override
    public void redeemReinforcementsCard() {
        throw new UnsupportedOperationException("Non puoi redimere in questo stato");

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {

        Player player = context.getTurn().getCurrentPlayer();
        Set<Player> playerList = context.getTurn().getPlayerList().keySet();

        boolean flag = false;
        // Logica pizzare rinforzi
        if (reinforcementToPlace > 0 && armies <= reinforcementToPlace) {

            player.placeReinforcements(ownTerritory, armies);

            reinforcementToPlace -= armies;
        } else {
            throw new IllegalArgumentException("Non puoi piazzare piu rinforzi di quelli che hai a disposizione");
        }

        for (Player p : playerList) {
            if (p.getReinforce() > 0) {
                flag = true;
            }
        }

        if (!flag) {
            context.setState(new NewTurnState(context));
            context.getTurn().nextTurn();
        }

        if (reinforcementToPlace == 0 || player.getReinforce() == 0) {
            context.setState(new InitialFaseState(context));
            context.getTurn().nextTurn();
        }
    }

}
