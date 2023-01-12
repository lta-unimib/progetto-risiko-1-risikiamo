package com.project.progettorisikorisikiamobackend.player;

import com.project.progettorisikorisikiamobackend.obiettivi.*;

import lombok.Getter;

import com.project.progettorisikorisikiamobackend.Turno.*;
import com.project.progettorisikorisikiamobackend.map.*;
@Getter


public class Player {
    private String name;
    private String color;
    private int id;
    private boolean isInGame;
    private Objective obiettivo;
    private boolean statoObiettivo;
    
    //costruttore
    public Player(String name, String color, Objective obiettivo, int id) {
        this.color=color;
        this.name=name;
        this.id=id;
        isInGame=true;
        this.obiettivo=obiettivo;
        this.statoObiettivo=false;
    }
    public void attack(Territory owner, Territory neighbor, int army) throws IllegalArgumentException {
        if(owner.getOwner().equals(neighbor.getOwner())) {
            throw new IllegalArgumentException("Territori appartenenti allo stesso giocatore");
        }
        if(owner.getArmy()<=army) {
            throw new IllegalArgumentException("Numero di truppe insufficienti");
        }
        if(!owner.isNeighbor(neighbor)) {
            throw new IllegalArgumentException("Territori non confinanti");
        }
        if(army<1) {
            throw new IllegalArgumentException("Numero di truppe non valide");
        }
        if(neighbor.getArmy()==0) {
            neighbor.setOwner(owner.getOwner());
            neighbor.addArmy(army);
            owner.addArmy(-army);
        }
        else {
            int i=0;
            Dice dado1=new Dice(6);
            Dice dado2=new Dice(6);
            while(i<army) {
                dado1.roll();
                dado2.roll();
                if(dado1.getValue() > dado2.getValue()) {
                    neighbor.addArmy(-1);
                }
                else {
                    owner.addArmy(-1);
                }
                i++;
            }
            if(neighbor.getArmy()==0) {
                neighbor.setOwner(owner.getOwner());
                neighbor.addArmy(army);
                owner.addArmy(-army);
            }
        }
      }

    public void move(Territory owner, Territory neighbor, int army) throws IllegalArgumentException{
            if(!owner.isNeighbor(neighbor)) {
                throw new IllegalArgumentException("Territori non confinanti");
            }
            if(owner.getArmy()<=army) {
                throw new IllegalArgumentException("Numero di truppe insufficienti");
            }
            if(army<1) {
                throw new IllegalArgumentException("Numero di truppe non valido");
            }
            owner.addArmy(-army);
            neighbor.addArmy(army);
        }
    public void reinforce(Territory owner, int army) {
            if(owner.getOwner()!=this) {
                throw new IllegalArgumentException("Territorio non appartenente al giocatore");
            }
            if(army<1) {
                throw new IllegalArgumentException("Numero di truppe non valido");
            }
            owner.addArmy(army);
        }
    public Player defeat() {
        //TODO: questa deve essere fatta dopo il refactoring di Turn
        return null;
    }
    public void passTurn() {
        //TODO: questa deve essere fatta dopo il refactoring di Turn
    }
    public void surrend(){
        //TODO: questa deve essere fatta dopo il refactoring di Turn
    }
}
