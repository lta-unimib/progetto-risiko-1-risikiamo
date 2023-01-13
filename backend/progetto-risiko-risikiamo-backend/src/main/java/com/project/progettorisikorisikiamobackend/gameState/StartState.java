package com.project.progettorisikorisikiamobackend.gameState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.*;
import com.project.progettorisikorisikiamobackend.player.Player;

public class StartState extends GameState {
    // ? Mappa ???????????????????
    Map map;

    public StartState(GameStateManager gameStateManager, String name, Map map) {
        super(gameStateManager, name);
        this.map = map;

    }

    public void move() {

        System.out.println("non è possibile effettuare spostamenti in questo stato");
    }

    public void attack() {

        System.out.println("non è possibile effettuare attacchi in questo stato");
    }

    public void renforce() {
        // ! Niente logica, lo state serve solo a orzare operazioni in un ordine preciso
       
       for (Continent c : map.getContinents()){
        HashMap t = c.getTerritories();
           for (Territory territory : (Collection<Territory>) t){
               territory.setArmies(1);
           }
       }
        
        // super.getGameStateManager().changeState("attacca");

    }

    public void endTurn() {
        // super.getGameStateManager().changeState("fineTurno");
    }
}
