package com.project.progettorisikorisikiamobackend.Cards;
import java.util.List;





import lombok.Getter;
import lombok.Setter;
import com.project.progettorisikorisikiamobackend.Turno.*;
public class DeckObjectives extends Deck {
   @Getter @Setter private List <Player> players;
   
    public DeckObjectives(List<Card> Obiectives, List <Player> players, Turn turno) {
        super(Obiectives, turno);
        this.players = players;
        
    }
  
    @Override
    public void draw() {
        CardObjective pescata;
        for (Player p : players) {
         pescata = (CardObjective) getCards().get(0);
        getCards().remove(pescata);
        pescata.setOwner(p);
        this.getTurno().getCurrentPlayer().getCardObjective().add(pescata);
        p.getCardObjective().add(pescata);
           }
        
    }
}