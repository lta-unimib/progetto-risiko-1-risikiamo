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
        deck.add(new CardTerritory("Jolly1", EnumCard.JOLLY));
        deck.add(new CardTerritory("Jolly2", EnumCard.JOLLY));

        Collections.shuffle(deck);
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

    }

    private int calculateCombo(CardTerritory card1, CardTerritory card2, CardTerritory card3) {
        List<CardTerritory> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        if (card1.getType() == card2.getType() && card2.getType() == card3.getType()) {
            return cardValues.get(card1.getType());
        }
        if (comboJolly(cards)) {
            return 12;
        }
        if (comboAllDifferent(cards)) {
            return 10;
        }
        return 0;
    }

    private boolean comboJolly(List<CardTerritory> cards) {
        for (CardTerritory c : cards) {
            if (c.getType() == EnumCard.JOLLY) {
                cards.remove(c);
                for (int i = 0; i < cards.size(); i++) {
                    for (int j = 0; j < cards.size(); j++) {
                        if (cards.get(i).getType() == cards.get(j).getType()) {
                            return true;
                        }
                    }

                }
            }

        }
        return false;
    }

    private boolean comboAllDifferent(List<CardTerritory> cards) {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (!cards.get(i).equals(cards.get(j))) {

                    if ((cards.get(i).getType() == cards.get(j).getType())
                            || cards.get(i).getType() == EnumCard.JOLLY) {
                        return false;
                    }
                }
            }

        }
        return true;
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
        List<CardTerritory> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        List<Territory> control = new ArrayList<>(map.getTerritories(giocatoreDiTurno));
        int jolly = reedemJolly(cards, combo, control);
        if (jolly > 0) {
            return jolly;
        }

        if (map.isTerritoryInMap(card1.getName()) && map.isTerritoryInMap(card2.getName())
                && map.isTerritoryInMap(card3.getName())) {
            if (!(this.getDeck().contains(card1) && this.getDeck().contains(card2) && this.getDeck().contains(card3))) {
                for (CardTerritory c : cards) {
                    if (control.contains(map.getTerritory(c.getName()))) {
                        combo += 2;
                    }
                }

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

    private int reedemJolly(List<CardTerritory> cards, int combo, List<Territory> control) {
        List<CardTerritory> aux = new ArrayList<>();
        if (combo == 12) {
            for (CardTerritory c : cards) {
                if (c.getType() != EnumCard.JOLLY) {
                    aux.add(c);
                }
            }
            for (CardTerritory card : aux) {
                if (control.contains(map.getTerritory(card.getName()))) {
                    combo += 2;
                }
            }
            return combo;
        }
        return -1;
    }

}
