package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.GameDto;

public interface IGameService {

    public void postCreate(GameDto gameDto);

    public Game getGame(String gameId);

    public void getWatch(String id);

    public void putStart(String id);

    public void putEnd(String id);

}
