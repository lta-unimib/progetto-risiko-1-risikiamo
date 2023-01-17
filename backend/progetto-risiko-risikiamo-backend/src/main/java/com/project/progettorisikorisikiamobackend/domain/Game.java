package com.project.progettorisikorisikiamobackend.domain;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Cards.DeckObjectives;
import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.InitialFaseState;
import com.project.progettorisikorisikiamobackend.gameState.NewGameState;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.Data;

@Data
public class Game implements IContext, IState {

    private String name;

    private Map map;

    private String id;

    private boolean isGameEnded;

    private Turn turn;

    private IState state;

    private DeckObjectives deckObjectives;

    private DeckTerritories deckTerritories;

    public Game(String name, Map map, String gameId) {
        this.id = gameId;
        this.name = name;
        this.map = map;
        this.turn = null;
        this.state = null;
        this.deckObjectives = null;
        this.deckTerritories = null;
        this.isGameEnded = false;
    }

    public void addPlayer(Player player) {
        if (turn != null)
            this.turn.addPlayer(player);
    }

    public void removePlayer(Player player) {
        this.turn.removePlayer(player);
    }

    public void startGame() {
        this.state = new InitialFaseState(this);
    }

    public void endGame() {
        this.isGameEnded = true;
        this.startGame();

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