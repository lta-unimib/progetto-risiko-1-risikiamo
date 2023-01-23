package com.project.progettorisikorisikiamobackend.player;

import com.project.progettorisikorisikiamobackend.obiettivi.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.*;

@Getter
@EqualsAndHashCode
public class Player implements IPlayer {

    @NonNull
    private String name;

    @NonNull
    private String id;

    private String color;
    @Setter
    private List<Objective> obiettivi;
    private List<CardTerritory> carte;
    private int reinforce;

    // costruttore
    public Player(String name, String color, String id) {
        this(name, color, new ArrayList<>(), new ArrayList<>(), id);

    }

    public Player(String name, String color, Objective obj, String id) {
        this(name, color, new ArrayList<>(), new ArrayList<>(), id);
        this.obiettivi.add(obj);

    }

    public Player(String name, String color, List<Objective> obiettivi, List<CardTerritory> carte, String id) {
        this.color = color;
        this.name = name;
        this.id = id;
        this.obiettivi = obiettivi;
        this.carte = carte;
        this.reinforce = 0;

    }

    public Player(String name) {
        this.name = name;
        this.id = name;
        this.color = null;
        this.obiettivi = new ArrayList<>();
        this.carte = new ArrayList<>();
        this.reinforce = 0;
    }

    public void attack(Territory owner, Territory neighbor, int army) throws IllegalArgumentException {
        if (owner.getOwner().equals(neighbor.getOwner())) {
            throw new IllegalArgumentException("Territori appartenenti allo stesso giocatore");
        }
        if (owner.getArmy() <= army) {
            throw new IllegalArgumentException("Numero di truppe insufficienti");
        }
        if (!owner.isNeighbour(neighbor)) {
            throw new IllegalArgumentException("Territori non confinanti");
        }
        if (army < 1) {
            throw new IllegalArgumentException("Numero di truppe non valide");
        }

        int i = army;
        Dice dado1 = new Dice(6);
        Dice dado2 = new Dice(6);
        while (i > 0 && neighbor.getArmy() != 0) {
            dado1.roll();
            dado2.roll();
            if (dado1.getValue() > dado2.getValue()) {
                neighbor.addArmy(-1);
            } else {
                owner.addArmy(-1);
                army--;

            }
            i--;
        }
        if (neighbor.getArmy() == 0) {
            neighbor.setOwner(owner.getOwner());
            owner.addArmy(-army);
            neighbor.addArmy(army);

        }

    }

    public void move(Territory owner, Territory neighbor, int army) throws IllegalArgumentException {
        if (!owner.isNeighbour(neighbor)) {
            throw new IllegalArgumentException("Territori non confinanti");
        }
        if (owner.getArmy() <= army) {
            throw new IllegalArgumentException("Numero di truppe insufficienti");
        }
        if (army < 1) {
            throw new IllegalArgumentException("Numero di truppe non valido");
        }
        owner.addArmy(-army);
        neighbor.addArmy(army);
    }

    public void placeReinforcements(Territory owner, int army) {
        if (owner.getOwner() != this) {
            throw new IllegalArgumentException("Territorio non appartenente al giocatore");
        }
        if (army < 1 || army > this.reinforce) {
            throw new IllegalArgumentException("Numero di truppe non valido");
        }
        owner.addArmy(army);
        this.reinforce -= army;
    }

    public void setReinforce(int armies) throws IllegalArgumentException {
        if (armies < 0)
            throw new IllegalArgumentException("Can not set reinforce below 0");

        this.reinforce = armies;
    }

    public void addObiettivo(Objective obiettivo) {
        this.obiettivi.add(obiettivo);
    }

    public void removeObiettivo(Objective obiettivo) {
        this.obiettivi.remove(obiettivo);
    }

    public void addCard(CardTerritory card) {
        this.carte.add(card);
    }

    public void removeCard(CardTerritory card) {
        this.carte.remove(card);
    }
}
