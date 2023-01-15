package com.project.progettorisikorisikiamobackend.gameState.interf;

import com.project.progettorisikorisikiamobackend.player.IPlayer;

public interface IState extends IPlayer {

    public void passTurn();

    public void redeemReinforcementsCard();

}
