package com.project.progettorisikorisikiamobackend.Turno;

import java.util.*;

import com.project.progettorisikorisikiamobackend.player.Player;

import lombok.*;

@Getter
public class Turn {

    private HashMap<Player, EnumTurn> playerList;
    private Player currentPlayer;
    private int turnNumber;

    public Turn(List<Player> playerList) {
        this.playerList = new HashMap<>();
        for (Player player : playerList) {
            this.playerList.put(player, EnumTurn.INGAME);
        }
        this.turnNumber = 0;
        this.currentPlayer = playerList.get(0);

    }

    public void setPlayerRandomOrder() {
        List<Player> newPlayerList = new ArrayList<>(this.playerList.keySet());
        Collections.shuffle(newPlayerList);
        HashMap<Player, EnumTurn> newPlayerListMap = new HashMap<>();

        for (Player player : newPlayerList) {
            newPlayerListMap.put(player, this.playerList.get(player));
        }

        this.playerList = newPlayerListMap;

    }

    public void setStatusPlayer(Player player, EnumTurn status) {
        if (playerList.computeIfPresent(player, (k, v) -> status) == null) {
            throw new IllegalArgumentException("Player not found");
        }

        if (status == EnumTurn.WIN) {
            for (Player p : playerList.keySet()) {
                if (p != player) {
                    playerList.put(p, EnumTurn.LOST);
                }
            }
        }

    }

    public void nextTurn() {
        if (!existPlayerWithStatus(EnumTurn.INGAME))
            throw new IllegalStateException("No player in game");

        int currentPlayerIndex = new ArrayList<>(playerList.keySet()).indexOf(currentPlayer);
        if (currentPlayerIndex == playerList.size() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }
        this.currentPlayer = new ArrayList<>(playerList.keySet()).get(currentPlayerIndex);

        if (playerList.get(currentPlayer) == EnumTurn.DEFETED) {
            nextTurn();
        }
        this.turnNumber++;
    }

    public boolean isTurnOfPlayer(Player player) {
        return player.equals(currentPlayer);
    }

    public boolean existPlayerWithStatus(EnumTurn status) {
        return playerList.values().stream().anyMatch(v -> v == status);
    }

}
