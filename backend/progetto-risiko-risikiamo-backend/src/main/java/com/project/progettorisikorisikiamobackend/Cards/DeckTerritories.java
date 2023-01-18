package com.project.progettorisikorisikiamobackend.Cards;

import java.util.List;
import com.project.progettorisikorisikiamobackend.player.*;
import java.util.Collections;
import java.util.EnumMap;
import lombok.*;

import com.project.progettorisikorisikiamobackend.Turno.Dice;
import com.project.progettorisikorisikiamobackend.map.*;
import java.util.ArrayList;

@Getter
@Setter
public class DeckTerritories {
    private List<CardTerritory> deck;
    private Map map;

    public DeckTerritories(Map map) {
        Dice d = new Dice(3);
        this.deck = new ArrayList<>();
        this.map = map;
        for (Continent c : map.getContinents().values()) {
            for (Territory t : c.getTerritories().values()) {
                switch (d.roll()) {
                    case 1:
                        deck.add(new CardTerritory(t.getName(), EnumCard.FANTE));
                        break;
                    case 2:
                        deck.add(new CardTerritory(t.getName(), EnumCard.CAVALLO));
                        break;
                    case 3:
                        deck.add(new CardTerritory(t.getName(), EnumCard.CANNONE));
                        break;
                    default:
                        break;

                }

            }
        }
    }

    public CardTerritory draw() {
        if (deck.isEmpty())
            return null;
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
        if (card1.getType() == card2.getType() && card2.getType() == card3.getType()) {
            return cardValues.get(card1.getType());
        }
        return 0;
    }

    public int reedemCards(CardTerritory card1, CardTerritory card2, CardTerritory card3, Player giocatoreDiTurno)
            throws IllegalArgumentException {

        if (card1 == null || card2 == null || card3 == null) {
            throw new IllegalArgumentException("Carte non valide");
        }
        int combo = calculateCombo(card1, card2, card3);
        if (combo == 0) {
            return 0;
        }
        List<Territory> control = new ArrayList<>(map.getTerritories(giocatoreDiTurno));

        if (map.isTerritoryInMap(card1.getName()) && map.isTerritoryInMap(card2.getName())
                && map.isTerritoryInMap(card3.getName())) {
            if (!(this.getDeck().contains(card1) && this.getDeck().contains(card2) && this.getDeck().contains(card3))) {
                if (control.contains(map.getTerritory(card1.getName())))
                    combo += 2;
                if (control.contains(map.getTerritory(card2.getName())))
                    combo += 2;
                if (control.contains(map.getTerritory(card3.getName())))
                    combo += 2;

            } else {
                throw new IllegalArgumentException("Carte duplicate");
            }

        } else {
            throw new IllegalArgumentException("I territori non esistono");
        }
        this.deck.add(card1);
        this.deck.add(card2);
        this.deck.add(card3);

        return combo;
    }
}
