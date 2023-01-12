package com.project.progettorisikorisikiamobackend.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.progettorisikorisikiamobackend.Turno.Dice;
import com.project.progettorisikorisikiamobackend.Turno.Player;
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
    public Game postCreate(GameDto gameDto) {

        Game game = gameDto.toGame(generateGameId());
        games.put(generateGameId(), game);
        return game;

    }

    @Override
    public String postAddPlayer(PlayerDto playerDto, String gameId) {
        com.project.progettorisikorisikiamobackend.domain.Player player = playerDto.toPlayer();
        games.get(gameId).addPlayer(player);

        return player.getId();

    }

    @Override
    public Game getGame(String gameId) {
        return games.get(gameId);

    }

    @Override
    public Game getWatch(String gameId) {

        // Dovrebbe ritornare le modifiche in base alla data
        return games.get(gameId);

    }

    @Override
    public void putStart(String gameId) {

        games.get(gameId).startGame();

    }

    @Override
    public void putEnd(String gameId) {

        games.get(gameId).endGame();

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
