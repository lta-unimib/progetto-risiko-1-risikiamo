package com.project.progettorisikorisikiamobackend.Turno;

import java.util.*;

import com.project.progettorisikorisikiamobackend.obiettivi.Objective;
import com.project.progettorisikorisikiamobackend.player.Player;
import org.apache.commons.lang3.tuple.Pair;
import lombok.*;

@Getter
@AllArgsConstructor
public class Turn {

    private List<Player> inGamePlayerList;
    private List<Pair<Player, Player>> defeatedPlayerList;
    private Player currentPlayer;
    private int turnNumber;

    public Turn() {
        this(new ArrayList<>(), new ArrayList<>(), null, 0);
    }

    public Turn(List<Player> inGamePlayerList) {
        this(inGamePlayerList, new ArrayList<>(), null, 0);
    }

    public void setPlayerRandomOrder() {
        Collections.shuffle(inGamePlayerList);
        this.currentPlayer = inGamePlayerList.get(0);
    }

    public int getTurnNumberInGame() {
        return (turnNumber / (inGamePlayerList.size() + defeatedPlayerList.size()) + 1);
    }

    public void setTurnNumberInGame(int turnNumber) {
        if (turnNumber < 1)
            throw new IllegalArgumentException("Turn number can't be negative");
        this.turnNumber = (turnNumber - 1) * (inGamePlayerList.size() + defeatedPlayerList.size());
    }

    public void addPlayer(Player player) {

        if (player == null)
            throw new IllegalArgumentException("Player  null");
        if (inGamePlayerList.contains(player))
            throw new IllegalArgumentException("Player is already in game");

        if (this.turnNumber > 0)
            throw new IllegalArgumentException("game already started");

        if (inGamePlayerList.isEmpty() || currentPlayer == null)
            this.currentPlayer = player;

        for (Player p : inGamePlayerList) {
            if (p.getColor().equals(player.getColor())) {
                throw new IllegalArgumentException("Player color already used");
            }
        }
        inGamePlayerList.add(player);
        turnNumber = 0;
    }

    public void removePlayer(Player player) {
        inGamePlayerList.remove(player);
    }

    public void defeatPlayer(Player by, Player player) {
        if (player == null || by == null)
            throw new IllegalArgumentException("Player null");
        if (!inGamePlayerList.contains(player))
            throw new IllegalArgumentException("Player is not in game");
        if (!inGamePlayerList.contains(by))
            throw new IllegalArgumentException("Player can't defeat by a defeted player");

        Pair<Player, Player> pair = Pair.of(player, by);
        defeatedPlayerList.add(pair);
        inGamePlayerList.remove(player);

    }

    /**
     * Permette di passare al turno successivo
     * 
     * 
     * @throws IllegalStateException se non ci sono giocatori in gioco
     */
    public void nextTurn() {
        if (inGamePlayerList.isEmpty())
            throw new IllegalStateException("No player in game");
        if (inGamePlayerList.size() == 1) {
            currentPlayer = inGamePlayerList.get(0);
            return;
        }
        if (currentPlayer == null) {
            this.currentPlayer = inGamePlayerList.get(0);
            turnNumber = 1;
            return;
        }
        checkWinner();

        this.currentPlayer = inGamePlayerList.get(turnNumber % inGamePlayerList.size());
        this.turnNumber += 1;

        // check is there is a winner

    }

    private void checkWinner() {

        boolean isThereAWinner = true;
        for (Objective o : currentPlayer.getObiettivi()) {
            if (!o.isCompleted(currentPlayer)) {
                isThereAWinner = false;
                break;
            }

        }
        if (isThereAWinner)
            this.setWin(currentPlayer);
    }

    public boolean isTurnOfPlayer(Player player) {
        return player.equals(currentPlayer);
    }

    public Player getWinner() {
        if (inGamePlayerList.size() == 1 && currentPlayer != null)
            return inGamePlayerList.get(0);
        return null;
    }

    public void setWin(Player player) {
        if (player == null)
            throw new IllegalArgumentException("Player is null");
        if (!inGamePlayerList.contains(player))
            throw new IllegalArgumentException("Player is not in game");

        ArrayList<Player> temp = new ArrayList<>(inGamePlayerList);
        inGamePlayerList.clear();
        for (Player p : temp)
            if (!p.equals(player))
                this.defeatedPlayerList.add(Pair.of(player, p));

        this.inGamePlayerList.add(player);
        currentPlayer = player;
    }

    public Player getDefeatedBy(Player player) {
        if (player == null)
            throw new IllegalArgumentException("Player is null");
        if (defeatedPlayerList.isEmpty())
            return null;

        for (Pair<Player, Player> pair : defeatedPlayerList) {
            if (pair.getLeft().equals(player))
                return pair.getRight();
        }

        return null;
    }

    public void surrender(Player player) {
        this.defeatPlayer(player, player);
        checkWinner();

    }
}
