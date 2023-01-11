package com.project.progettorisikorisikiamobackend.Turno;
import lombok.Getter;
import lombok.Setter;

public class Player  {
   @Getter @Setter private String playerName;
    @Getter private String color;
    @Getter @Setter private int playerId;
    @Getter  @Setter private int armies;
    @Getter private int Obiettivo;
    
    @Getter private int statoObiettivo;
//costruttore
    public Player(String playerName, String color, int Obiettivo, int statoObiettivo, int playerId, int armies) {
        this.playerName = playerName;
        this.color = color;
        this.Obiettivo = Obiettivo;
        this.statoObiettivo = statoObiettivo;
        this.armies = armies;
        this.playerId = playerId;

        

    }
    //costruttore vuoto
    public Player() {
        this.playerName = "";
        this.color = "";
        this.Obiettivo = 0;
        this.statoObiettivo = 0;
        this.armies = 25;
        this.playerId = 0;
    
    }

/**utility methods */
    public void attack(){}
    public void moveArmies(){}
    public void placeReinforcements(int armies){}
    public void drawCard(){}
}
