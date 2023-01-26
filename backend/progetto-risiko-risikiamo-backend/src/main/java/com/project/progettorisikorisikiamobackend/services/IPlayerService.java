package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.player.Player;

public interface IPlayerService {

    // * put Indica il tipo di risposta (get, post, put, delete)

    public Player getPlayer(String gameId, String playerId);

    public void surrend(String gameId, String playerId);

    public void endTurn(String gameId, String playerId);

    public void redeemReinforcementsCard(String gameId, String playerId, String c1, String c2, String c3);

    public void placeReinforcements(String gameId, String playerId, String ownTerritory, int armies);

    public void attack(String gameId, String playerId, String owner, String neighbor, int army);

    public void move(String gameId, String playerId, String owner, String neighbor, int army);

}
