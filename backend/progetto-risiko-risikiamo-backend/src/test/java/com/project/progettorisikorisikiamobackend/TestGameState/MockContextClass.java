package com.project.progettorisikorisikiamobackend.TestGameState;

import com.project.progettorisikorisikiamobackend.Cards.DeckObjectives;
import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.gameState.interf.IContext;
import com.project.progettorisikorisikiamobackend.gameState.interf.IState;
import com.project.progettorisikorisikiamobackend.map.Map;

public class MockContextClass implements IContext {

    Turn turn;

    Map map;
    IState state;

    DeckObjectives deckObjectives;

    DeckTerritories deckTerritories;

    public MockContextClass(Turn turn, Map map, IState state) {
        this.turn = turn;
        this.map = map;
        this.state = state;

        this.deckObjectives = null;
        this.deckTerritories = null;
    }

    public MockContextClass(Turn turn, Map map, IState state, DeckObjectives deckObjectives,
            DeckTerritories deckTerritories) {
        this.turn = turn;
        this.map = map;
        this.state = state;
        this.deckObjectives = deckObjectives;
        this.deckTerritories = deckTerritories;
    }

    @Override
    public Turn getTurn() {

        return turn;
    }

    @Override
    public Map getMap() {

        return map;
    }

    @Override
    public void setState(IState state) {
        this.state = state;

    }

    public IState getState() {
        return state;
    }

    @Override
    public DeckObjectives getDeckObjectives() {

        return deckObjectives;
    }

    @Override
    public DeckTerritories getDeckTerritories() {

        return deckTerritories;
    }

    @Override
    public void setGameEnded(boolean gameEnded) {

    }
}