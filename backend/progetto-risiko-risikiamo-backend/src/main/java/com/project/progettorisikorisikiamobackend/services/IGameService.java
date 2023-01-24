package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.exeptions.NotFoundExeption;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

public interface IGameService {

    public GameDto postCreate(GameDto gameDto);

    public GameDto getWatch(String id) throws NotFoundExeption;

    public void putStart(String id) throws NotFoundExeption;

    public void putEnd(String id) throws NotFoundExeption;

    public String postAddPlayer(PlayerDto playerDto, String gameId) throws NotFoundExeption;

    public String getSvg(String gameId);

}
