package com.project.progettorisikorisikiamobackend.player;

import com.project.progettorisikorisikiamobackend.obiettivi.*;

import io.micrometer.common.lang.NonNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
    private Objective obiettivo;
    private int reinforce;
    // Carte

    // costruttore
    public Player(String name, String color, Objective obiettivo, String id) {
        this.color = color;
        this.name = name;
        this.id = id;
        this.obiettivo = obiettivo;
        this.reinforce = 0;

    }

    public Player(String name) {
        this.name = name;
        this.id = null;
        this.color = null;
        this.obiettivo = null;
        this.reinforce = 0;
    }

    public void attack(Territory ownTerritory, Territory neighbor, int army) throws IllegalArgumentException {
        if (ownTerritory.getOwner().equals(neighbor.getOwner())) {
            throw new IllegalArgumentException("Territori appartenenti allo stesso giocatore");
        }
        if (ownTerritory.getArmy() <= army) {
            throw new IllegalArgumentException("Numero di truppe insufficienti");
        }
        if (!ownTerritory.isNeighbor(neighbor)) {
            throw new IllegalArgumentException("Territori non confinanti");
        }
        if (army < 1) {
            throw new IllegalArgumentException("Numero di truppe non valide");
        }
        if (neighbor.getArmy() == 0) {
            neighbor.setOwner(ownTerritory.getOwner());
            neighbor.addArmy(army);
            ownTerritory.addArmy(-army);
        } else {
            int i = 0;
            Dice dado1 = new Dice(6);
            Dice dado2 = new Dice(6);
            while (i < army) {
                dado1.roll();
                dado2.roll();
                if (dado1.getValue() > dado2.getValue()) {
                    neighbor.addArmy(-1);
                } else {
                    ownTerritory.addArmy(-1);
                }
                i++;
            }
            if (neighbor.getArmy() == 0) {
                neighbor.setOwner(ownTerritory.getOwner());
                neighbor.addArmy(army);
                ownTerritory.addArmy(-army);
            }
        }
    }

    public void move(Territory ownTerritory, Territory neighbor, int army) throws IllegalArgumentException {
        if (!ownTerritory.isNeighbor(neighbor)) {
            throw new IllegalArgumentException("Territori non confinanti");
        }
        if (ownTerritory.getArmy() <= army) {
            throw new IllegalArgumentException("Numero di truppe insufficienti");
        }
        if (army < 1) {
            throw new IllegalArgumentException("Numero di truppe non valido");
        }
        ownTerritory.addArmy(-army);
        neighbor.addArmy(army);
    }

    public void reinforce(Territory ownTerritory, int army) {
        if (ownTerritory.getOwner() != this) {
            throw new IllegalArgumentException("Territorio non appartenente al giocatore");
        }
        if (army < 1 || army > this.reinforce) {
            throw new IllegalArgumentException("Numero di truppe non valido");
        }
        this.reinforce -= army;
        ownTerritory.addArmy(army);
    }

    public Player defeat() {

        return null;
    }

    public void passTurn() {

    }

    public void surrend() {

    }

    public void redeemReinforcementsCard() {
    }

    public void placeReinforcements(Territory ownTerritory, int armies) {
    }

    public void setReinforce(int armies) throws IllegalArgumentException {
        if (armies < 0)
            throw new IllegalArgumentException("Can not set reinforce below 0");

        this.reinforce = armies;
    }
}
