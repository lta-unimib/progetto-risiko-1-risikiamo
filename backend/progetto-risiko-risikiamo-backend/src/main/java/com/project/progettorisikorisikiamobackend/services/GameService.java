package com.project.progettorisikorisikiamobackend.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.progettorisikorisikiamobackend.Turno.Dice;
import com.project.progettorisikorisikiamobackend.controllers.requests.GameDto;
import com.project.progettorisikorisikiamobackend.controllers.requests.PlayerDto;
import com.project.progettorisikorisikiamobackend.domain.Game;

import io.github.uinnn.spigot.annotations.Service;

@Service
public class GameService implements IGameService {

    private HashMap<String, Game> games = new HashMap<String, Game>();

    @Override
    public void postCreate(GameDto gameDto) {
        gameDto.toGame();
        games.put(generateGameId(), gameDto.toGame());

    }

    @Override
    public Game getGame(String gameId) {
        return games.get(gameId);

    }

    @Override
    public void getWatch(String gameId) {
        // TODO ritorna stato del gioco

    }

    @Override
    public void putStart(String gameId) {
        // TODO Start a game

    }

    @Override
    public void putEnd(String gameId) {
        // TODO conclude il gioco

    }

    private String generateGameId() {

        // generate a string to use for id using string builder
        StringBuilder sb = new StringBuilder();
        Dice dice = new Dice(10);

        while (sb.toString().length() == 0 || games.containsKey(sb.toString())) {

            for (int i = 0; i < 10; i++) {
                sb.append(dice.roll());
            }
        }

        return sb.toString();

    }

}
