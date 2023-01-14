package com.project.progettorisikorisikiamobackend.Cards;

import java.util.List;
import com.project.progettorisikorisikiamobackend.player.*;
import java.util.Collections;
import java.util.EnumMap;
import lombok.Getter;
import com.project.progettorisikorisikiamobackend.map.*;

@Getter
public class DeckTerritories  {
    private  List<CardTerritory> deck;
    private List <Player> players;
    private Map map;
    public DeckTerritories(List<CardTerritory> deck, Map map, List<Player> players) {
        this.deck = deck;
        this.map = map;
        this.players = players;
    }
    public CardTerritory draw() {
        Collections.shuffle(deck);
        CardTerritory pescata = deck.get(0);
           deck.remove(pescata);
         return pescata;

     }
  
    private static final EnumMap<EnumCard, Integer> cardValues = new EnumMap<>(EnumCard.class);
    static {
        cardValues.put(EnumCard.FANTE, 6);
        cardValues.put(EnumCard.CAVALLO, 8);
        cardValues.put(EnumCard.CANNONE, 4);
        cardValues.put(EnumCard.JOLLY, 12);
    }
    
    private int calculateCombo(CardTerritory card1, CardTerritory card2, CardTerritory card3) {
        if(card1.getType() == card2.getType() && card2.getType() == card3.getType()) {
            return cardValues.get(card1.getType());
        }
        return 0;
    }
    
   
        
    

     public int reedemCards(List <CardTerritory> cards, Player giocatoreDiTurno) {
         int combo = calculateCombo(cards.get(0), cards.get(1), cards.get(2));
         if(combo == 0) {
             return 0;
         }
         
         for(Continent c : map.getContinents()){
            for (CardTerritory card : cards) {
                Territory t = c.getTerritory(card.getName());
                if(t.getOwner() == giocatoreDiTurno) {   
                                 combo += 2;
         }

       

         }
         

     }
     for(CardTerritory card : cards) {
        deck.add(card);
        cards.remove(card);
    }
     return combo;
    }
}
     


