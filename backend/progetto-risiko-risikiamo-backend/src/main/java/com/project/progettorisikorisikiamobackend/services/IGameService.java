package com.project.progettorisikorisikiamobackend.services;

import org.springframework.http.ResponseEntity;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

public interface IGameService {

    public GameDto postCreate(GameDto gameDto);

    public GameDto getWatch(String id);

    public void putStart(String id);

    public void putEnd(String id);

    public String postAddPlayer(PlayerDto playerDto, String gameId);

}
