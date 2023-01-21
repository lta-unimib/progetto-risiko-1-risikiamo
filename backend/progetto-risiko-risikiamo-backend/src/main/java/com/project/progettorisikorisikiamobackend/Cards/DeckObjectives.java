package com.project.progettorisikorisikiamobackend.Cards;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.map.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeckObjectives {
    private List<Objective> deck;
    private Map map;
    private List<Player> players;

    public DeckObjectives(Map map, Turn turn, List<Player> players) {
        this.map = map;
        this.players = players;
        this.deck = new ArrayList<>();
        List<Objective> deckObj = new ArrayList<>();
        for (Player p : players) {
            deckObj.add(new OpponentDefeated(p, turn, map));

        }
        for (Continent c : map.getContinents().values()) {
            deckObj.add(new ConquestContinent(c));

        }
        int numTerritories = 0;
        for (Continent c : map.getContinents().values()) {
            numTerritories += c.getNumberOfTerritories();
        }
        numTerritories = numTerritories * (58 / 100);
        if (numTerritories < 4)
            numTerritories = 4;
        for (int i = 0; i < players.size(); i++) {
            deckObj.add(new TotTerritories(numTerritories, map));
            deckObj.add(new TotTerritories(numTerritories, map));
        }
        deck.addAll(deckObj);
    }

    public Objective draw(Player drawerPlayer) {
        Collections.shuffle(deck);
        Objective pescata = deck.get(0);
        if (pescata.getClass() == OpponentDefeated.class &&
                ((OpponentDefeated) pescata).getOpponent() == drawerPlayer) {
            pescata = deck.get(1);
        }
        deck.remove(pescata);
        return pescata;

    }
}
