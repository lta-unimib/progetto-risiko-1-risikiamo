package com.project.progettorisikorisikiamobackend.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.progettorisikorisikiamobackend.Turno.Dice;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.mapper.GameMapper;
import com.project.progettorisikorisikiamobackend.services.mapper.PlayerMapper;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GameService implements IGameService {

    /* List of all active game */

    public Map<String, Game> games = new HashMap<>();

    public Game getGame(String gameId) {

        return games.get(gameId);

    }

    @Override
    public GameDto postCreate(GameDto gameDto) {

        String gameId = generateGameId();

        Game game = GameMapper.toEntity(gameDto, gameId);

        games.put(gameId, game);
        return GameMapper.toDto(game);

    }

    @Override
    public String postAddPlayer(PlayerDto playerDto, String gameId) {
        Player player = PlayerMapper.toEntity(playerDto);
        games.get(gameId).addPlayer(player);

        return player.getId();

    }

    @Override
    public GameDto getWatch(String gameId) {

        // Dovrebbe ritornare le modifiche in base alla data
        Game game = games.get(gameId);
        return GameMapper.toDto(game);

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
