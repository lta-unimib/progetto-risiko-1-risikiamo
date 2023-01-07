package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.services.mapper.dto.PlayerDto;

public interface IPlayerService {

    // * put Indica il tipo di risposta (get, post, put, delete)

    public void postAddPlayer(PlayerDto playerDto, String gameId);

    public void putTurn(String gameId, String playerId);

    public void putSkip(String gameId, String playerId);

    public void putSurrend(String gameId, String playerId);

    public void putAttack(String gameId, String playerId);

    public void putMove(String gameId, String playerId);

    public void putPlace(String gameId, String playerId);

    public void putRenforce(String gameId, String playerId);

    public void putPlay(String gameId, String playerId);

}
