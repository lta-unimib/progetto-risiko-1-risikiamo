package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.controllers.requests.GameDto;
import com.project.progettorisikorisikiamobackend.controllers.requests.PlayerDto;

public interface IGameService {

    public void postCreate(GameDto gameDto);

    public void postAddPlayer(PlayerDto playerDto, String id);

    public void getWatch(String id);

    public void putStart(String id);

    public void putEnd(String id);

    public void putPlay(String gameId, String playerId);

}
