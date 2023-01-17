package com.project.progettorisikorisikiamobackend.obiettivi;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Getter;

@Getter
public class OpponentDefeated implements Objective {
    private Player opponent;
    private Turn turn;
    private Map map;
    private Objective alterObjective;

    public OpponentDefeated(Player opponent, Turn turn, Map map) {
        this.opponent = opponent;
        this.turn = turn;
        this.map = map;
        this.alterObjective = new TotTerritories(map.getNumberOfTerritories(opponent), map);

    }

    @Override
    public String getObjDescription() {
        if (turn != null && !turn.getInGamePlayerList().contains(opponent)) {
            return this.alterObjective.getObjDescription();
        } else {
            return "Sconfiggi il giocatore " + opponent.getName();
        }
    }

    @Override
    public boolean isCompleted(Player player) {

        if (player == null || this.opponent == null || this.turn == null || this.map == null) {
            return false;
        }
        if (turn.getDefeatedBy(opponent) != player && !turn.getInGamePlayerList().contains(opponent))
            return this.alterObjective.isCompleted(player);
        else
            return turn.getDefeatedBy(opponent) == player;

    }

}
