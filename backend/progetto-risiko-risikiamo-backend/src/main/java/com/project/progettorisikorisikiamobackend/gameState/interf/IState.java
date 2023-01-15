package com.project.progettorisikorisikiamobackend.gameState.interf;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.player.IPlayer;

public interface IState extends IPlayer {

    public void endTurn();

    public void redeemReinforcementsCard(CardTerritory c1, CardTerritory c2, CardTerritory c3);

}
