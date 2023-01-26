package com.project.progettorisikorisikiamobackend.gameState.interf;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.IPlayer;

public interface IState extends IPlayer {

    public default void endTurn() {
        throw new IllegalStateException("Can't end turn in at this moment");
    }

    public default void redeemReinforcementsCard(CardTerritory c1, CardTerritory c2, CardTerritory c3) {
        throw new IllegalStateException("Can't redeem reinforcements card in at this moment");
    }

    @Override
    public default void placeReinforcements(Territory ownTerritory, int armies) {
        throw new IllegalStateException("Can't place reinforcements in at this moment");
    }

    @Override
    public default void attack(Territory owner, Territory neighbor, int army) {
        throw new IllegalStateException("Can't attack in at this moment");
    }

    @Override
    public default void move(Territory owner, Territory neighbor, int army) {
        throw new IllegalStateException("Can't move in at this moment");
    }

}
