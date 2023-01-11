package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.GameDto;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.PlayerDto;

public interface IGameService {

    public Game postCreate(GameDto gameDto);

    public Game getGame(String gameId);

    public void getWatch(String id);

    public void putStart(String id);

    public void putEnd(String id);

    public void postAddPlayer(PlayerDto playerDto, String gameId);

}
