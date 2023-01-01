package com.project.progettorisikorisikiamobackend.services;

import com.project.progettorisikorisikiamobackend.controllers.requests.GameDto;
import com.project.progettorisikorisikiamobackend.controllers.requests.PlayerDto;

import io.github.uinnn.spigot.annotations.Service;

@Service
public class GameService implements IGameService {

    @Override
    public void postCreate(GameDto gameDto) {
        // TODO Create a game

    }

    @Override
    public void postAddPlayer(PlayerDto playerDto, String id) {
        // TODO Add a player to a game

    }

    @Override
    public void getWatch(String id) {
        // TODO ritorna stato del gioco

    }

    @Override
    public void putStart(String id) {
        // TODO Start a game

    }

    @Override
    public void putEnd(String id) {
        // TODO conclude il gioco

    }

    @Override
    public void putPlay(String gameId, String playerId) {
        // TODO comincia la partita

    }

}
