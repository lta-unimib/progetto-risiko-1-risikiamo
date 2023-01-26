package com.project.progettorisikorisikiamobackend.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Cards.DeckObjectives;
import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.InitialFaseState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.obiettivi.Objective;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game implements IContext, IState {

    private String name;

    private int startingArmies;

    private Map map;

    private String id;

    private boolean isGameEnded;
    private boolean isGameStarted;

    private Turn turn;

    private IState state;

    private DeckObjectives deckObjectives;

    private DeckTerritories deckTerritories;

    private String svgMap;

    public Game(String name, Map map, String gameId) {
        this.id = gameId;
        this.name = name;
        this.map = map;
        this.turn = null;
        this.state = null;
        this.deckObjectives = null;
        this.deckTerritories = null;
        this.isGameEnded = false;
        this.isGameStarted = false;
    }

    public void addPlayer(Player player) {
        if (turn != null) {
            if (this.turn.getInGamePlayerList().size() >= 6)
                throw new IllegalArgumentException("You can't add more than 6 players");
            this.turn.addPlayer(player);
        } else {

            this.turn = new Turn();
            this.turn.addPlayer(player);
        }
    }

    public void removePlayer(Player player) {
        this.turn.removePlayer(player);
    }

    public void startGame() {

        this.deckObjectives = new DeckObjectives(this.map, this.turn, this.turn.getInGamePlayerList());
        this.deckTerritories = new DeckTerritories(this.map);
        for (Player player : this.turn.getInGamePlayerList()) {
            List<Objective> objectives = new ArrayList<>();
            objectives.add(this.deckObjectives.draw(player));
            player.setObiettivi(objectives);
        }

        // List of all territory
        ArrayList<Territory> territories = new ArrayList<>();

        for (Continent continent : this.map.getContinents().values()) {
            territories.addAll(continent.getTerritories().values());
        }

        Collections.shuffle(territories);

        int numberOfTerritories = territories.size() / this.turn.getInGamePlayerList().size() + 1;

        for (Player player : this.turn.getInGamePlayerList()) {
            for (int i = 0; i < numberOfTerritories && !territories.isEmpty(); i++) {
                Territory territory = territories.get(0);
                territory.setOwner(player);
                territory.addArmy(1);
                territories.remove(0);

            }

        }

        List<Player> players = this.turn.getInGamePlayerList();

        for (Player player : players) {
            player.setReinforce(this.startingArmies);

        }

        this.state = new InitialFaseState(this);
        this.turn.nextTurn();
        this.isGameStarted = true;
    }

    public void endGame() {
        this.isGameEnded = true;

    }

    public void surrender(Player player) {
        this.turn.surrender(player);
    }

    // IState methods
    @Override
    public void endTurn() {
        this.state.endTurn();
    }

    @Override
    public void redeemReinforcementsCard(CardTerritory c1, CardTerritory c2, CardTerritory c3) {
        this.state.redeemReinforcementsCard(c1, c2, c3);
    }

    @Override
    public void placeReinforcements(Territory ownTerritory, int armies) {
        this.state.placeReinforcements(ownTerritory, armies);
    }

    @Override
    public void attack(Territory owner, Territory neighbor, int army) {
        this.state.attack(owner, neighbor, army);
    }

    @Override
    public void move(Territory owner, Territory neighbor, int army) {
        this.state.move(owner, neighbor, army);
    }

}