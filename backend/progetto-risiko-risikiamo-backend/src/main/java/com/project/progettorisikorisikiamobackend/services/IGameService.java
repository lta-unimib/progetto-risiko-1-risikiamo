package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.controllers.requests.GameDto;
import com.project.progettorisikorisikiamobackend.controllers.requests.PlayerDto;
import com.project.progettorisikorisikiamobackend.domain.Game;

public interface IGameService {

    public void postCreate(GameDto gameDto);

    public Game getGame(String gameId);

    public void getWatch(String id);

    public void putStart(String id);

    public void putEnd(String id);

}
