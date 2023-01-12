package com.project.progettorisikorisikiamobackend.gameState;

import java.util.ArrayList;
import java.util.List;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.gameState.interf.AGameState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IStateManager;
import com.project.progettorisikorisikiamobackend.map.*;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StartState extends AGameState {

    public StartState(IStateManager gameStateManager, Player player) {
        super(gameStateManager, player);

    }

    @Override
    public void attack(Territory owner, Territory neighbor, int army) {
        throw new UnsupportedOperationException("Can't attack in at this moment"); // To change body of generated
                                                                                   // methods, choose Tools | Templates.
    }

    @Override
    public void move(Territory owner, Territory neighbor, int army) {
        // TODO Auto-generated method stub

    }

    @Override
    public void passTurn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void redeemReinforcementsCard() {
        // TODO Auto-generated method stub

    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init() {
        // Ottiene i rinforzi
        this.player.setReinforcements(player.getReinforcements() + player.getTerritories().size() / 3);

    }

}
