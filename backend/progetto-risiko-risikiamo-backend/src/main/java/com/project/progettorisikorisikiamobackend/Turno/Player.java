package com.project.progettorisikorisikiamobackend.Turno;
import java.util.*;
import com.project.progettorisikorisikiamobackend.Cards.*;
import lombok.Getter;
import lombok.Setter;

public class Player  {
   @Getter @Setter private String playerName;
    @Getter private String color;
    @Getter @Setter private int playerId;
    @Getter  @Setter private int armies;
    @Getter private int Obiettivo;
    @Getter @Setter private List <CardTerritory> cardsOnHand;
    @Getter @Setter private List <CardObjective> CardObjective;
    
    @Getter private int statoObiettivo;
//costruttore
    public Player(String playerName, String color, int Obiettivo, int statoObiettivo, int playerId, int armies) {
        this.playerName = playerName;
        this.color = color;
        this.Obiettivo = Obiettivo;
        this.statoObiettivo = statoObiettivo;
        this.armies = armies;
        this.playerId = playerId;
        this.cardsOnHand = new ArrayList<>();
        this.CardObjective = new ArrayList<>();

        

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
    public int discardCard(List <CardTerritory> cardTerritories){
        int renforce = 0;
        for (CardTerritory cardTerritory : cardTerritories) {
            renforce += Integer.parseInt(cardTerritory.getValue());
            this.cardsOnHand.remove(cardTerritory);
            
        }
        return renforce;
    }
}
