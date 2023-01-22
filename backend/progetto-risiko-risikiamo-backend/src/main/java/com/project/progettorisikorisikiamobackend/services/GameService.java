package com.project.progettorisikorisikiamobackend.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.project.progettorisikorisikiamobackend.Turno.Dice;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.exeptions.NotFoundExeption;
import com.project.progettorisikorisikiamobackend.gameState.NewGameState;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.mapper.GameMapper;
import com.project.progettorisikorisikiamobackend.services.mapper.PlayerMapper;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GameService implements IGameService {

    /* List of all active game */

    @Getter
    private Map<String, Game> games = new HashMap<>();

    public Game getGame(String gameId) throws NotFoundExeption {

        Game game = games.get(gameId);
        if (game == null) {
            throw new NotFoundExeption("Game not found");
        }
        return games.get(gameId);

    }

    @Override
    public String getSvg(String gameId) {

        return this.getGame(gameId).getSvgMap();

    }

    @Override
    public GameDto postCreate(GameDto gameDto) {

        String gameId = generateGameId();

        Game game = GameMapper.toEntity(gameDto, gameId);

        games.put(gameId, game);
        return GameMapper.toDto(game);

    }

    @Override
    public String postAddPlayer(PlayerDto playerDto, String gameId) throws NotFoundExeption {

        Player player = PlayerMapper.toEntity(playerDto);
        this.getGame(gameId).addPlayer(player);
        return player.getId();

    }

    @Override
    public GameDto getWatch(String gameId) throws NotFoundExeption {

        // Dovrebbe ritornare le modifiche in base alla data

        Game game = this.getGame(gameId);
        return GameMapper.toDto(game);

    }

    @Override
    public void putStart(String gameId) throws NotFoundExeption {

        Game game = this.getGame(gameId);

        isValidGame(game);
        game.startGame();

    }

    @Override
    public void putEnd(String gameId) throws NotFoundExeption {

        this.getGame(gameId).endGame();

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

    public void isValidGame(Game game) {

        if (game == null)
            throw new IllegalArgumentException("Game not found");
        if (game.getTurn() == null)
            throw new IllegalArgumentException("no player in game");
        if (game.getTurn().getInGamePlayerList().size() < 2)
            throw new IllegalArgumentException("Not enough players");
        if (game.getMap() == null)
            throw new IllegalArgumentException("Map not initialized");
        if (!(game.getState() instanceof NewGameState || game.getState() == null))
            throw new IllegalArgumentException("Not enough players");

        if (game.getTurn().getWinner() != null)
            throw new IllegalArgumentException("Game already ended");

    }

}
