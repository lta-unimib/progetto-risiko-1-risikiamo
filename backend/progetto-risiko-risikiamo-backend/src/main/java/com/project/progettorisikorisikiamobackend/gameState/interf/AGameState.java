package com.project.progettorisikorisikiamobackend.gameState.interf;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.GameStateManager;
import com.project.progettorisikorisikiamobackend.player.IPlayer;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.*;

@Getter
@Setter
public abstract class AGameState implements IPlayer, IGameState {

    private IStateManager gameStateManager;
    private Player player;

    public AGameState(IStateManager gameStateManager, Player player) {
        this.gameStateManager = gameStateManager;
        this.player = player;
    }

}
