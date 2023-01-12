package com.project.progettorisikorisikiamobackend.gameState;

import java.util.ArrayList;
import java.util.List;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.*;
import com.project.progettorisikorisikiamobackend.player.Player;

public class StartState extends GameState {
    Map map;

    public StartState(GameStateManager gameStateManager, String name, Turn turno, Map map) {
        super(gameStateManager, name, turno);
        this.map = map;

    }

    public void move() {

        System.out.println("non è possibile effettuare spostamenti in questo stato");
    }

    public void attack() {

        System.out.println("non è possibile effettuare attacchi in questo stato");
    }

    public void renforce() {

        List<Player> players = super.getTurno().getPlayerList();
        for (Player p : players) {
            while (p.getReinforce() > 0) {
                p.placeReinforcements(3);
                p.setReinforce(p.getReinforce() - 3);
            }

        }
        super.getGameStateManager().changeState("attacca");

    }

    public void endTurn() {
        super.getGameStateManager().changeState("fineTurno");
    }
}
