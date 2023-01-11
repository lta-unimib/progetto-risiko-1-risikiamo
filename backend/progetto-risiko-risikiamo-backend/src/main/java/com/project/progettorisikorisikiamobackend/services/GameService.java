package com.project.progettorisikorisikiamobackend.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.progettorisikorisikiamobackend.Turno.Dice;
import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.GameDto;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.PlayerDto;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GameService implements IGameService {

    /* List of all active game */
    private HashMap<String, Game> games = new HashMap<>();

    @Override
    public void postCreate(GameDto gameDto) {

        games.put(generateGameId(), new Game());

    }

    @Override
    public void postAddPlayer(PlayerDto playerDto, String gameId) {
        // TODO Add a player to a game

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
