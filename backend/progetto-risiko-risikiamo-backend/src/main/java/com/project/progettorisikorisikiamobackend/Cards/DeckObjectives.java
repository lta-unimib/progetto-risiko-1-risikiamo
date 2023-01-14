package com.project.progettorisikorisikiamobackend.Cards;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import com.project.progettorisikorisikiamobackend.obiettivi.*;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.map.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeckObjectives  {
  private List<Objective> deck;
  private Map map;
  private List<Player> players;
   
    public DeckObjectives(Map map, List<Player> players) {
        this.map = map;
        this.players = players;
        this.deck = new ArrayList<>();
        for (Player player : players) {
            this.deck.add(new OpponentDefeated(player));
            
            
        }
        for (Continent c : map.getContinents()) {
            this.deck.add(new ConquestContinent(c));
            
        }
       int numTerritories = 0;
       for (Continent c : map.getContinents()) {
           numTerritories += c.getNumberOfTerritories();
        }
         numTerritories = numTerritories * (58 / 100);
        for(int i = 0; i < players.size(); i++) {
            this.deck.add(new TotTerritories(numTerritories, map));
         this.deck.add(new TotTerritories(numTerritories, map));
        }
    }
    
  
    
    public Objective draw(Player drawerPlayer) {
        Collections.shuffle(deck);
        Objective pescata = deck.get(0);
        if(pescata.getClass() == OpponentDefeated.class && 
        ((OpponentDefeated) pescata).getOpponent() == drawerPlayer){
            pescata = deck.get(1);
        }
           deck.remove(pescata);
         return pescata;

     }
    }

