package com.project.progettorisikorisikiamobackend.testServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.exeptions.NotFoundExeption;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.GameService;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

public class TestGameService {

    @Test
    void testGetGame() throws NotFoundExeption {
        // arrange
        GameService gameService = new GameService();
        Game game = new Game();
        game.setId("testId");
        gameService.getGames().put("testId", game);

        // act
        Game result = gameService.getGame("testId");

        // assert
        assertEquals(game, result);
    }

    @Test
    void testGetGameNotFound() throws NotFoundExeption {
        // arrange
        GameService gameService = new GameService();
        Game game = new Game();
        game.setId("testId");
        gameService.getGames().put("testId", game);

        // act
        NotFoundExeption exception = null;
        try {
            gameService.getGame("testId2");
        } catch (NotFoundExeption e) {
            exception = e;
        }

        // assert
        assertEquals("Game not found", exception.getMessage());
    }

    @Test
    void testGetSvg() throws NotFoundExeption {
        // arrange
        GameService gameService = new GameService();
        Game game = new Game();
        game.setId("testId");
        game.setSvgMap("testSvg");
        gameService.getGames().put("testId", game);

        // act
        String result = gameService.getSvg("testId");

        // assert
        assertEquals("testSvg", result);
    }

    @Test
    void testGetSvgNotFound() throws NotFoundExeption {
        // arrange
        GameService gameService = new GameService();
        Game game = new Game();
        game.setId("testId");
        game.setSvgMap("testSvg");
        gameService.getGames().put("testId", game);

        // act
        NotFoundExeption exception = null;
        try {
            gameService.getSvg("testId2");
        } catch (NotFoundExeption e) {
            exception = e;
        }

        // assert
        assertEquals("Game not found", exception.getMessage());
    }

    @Test
    void testPostCreateExeptions() {
        // arrange
        GameService gameService = new GameService();

        assertThrowsExactly(IllegalArgumentException.class, () -> gameService.postCreate(null).getId());

    }

    @Test
    void testPostAddPlayerExeptions() {
        // arrange
        GameService gameService = new GameService();

        assertThrowsExactly(IllegalArgumentException.class, () -> gameService.postAddPlayer(null, null));

    }

    @Test
    void testIsValidGame_Null() {
        GameService gameService = new GameService();
        assertThrows(IllegalArgumentException.class, () -> gameService.isValidGame(null));
    }

    @Test
    void testIsValidGame_NoTurn() {
        Game game = new Game();
        GameService gameService = new GameService();

        assertThrows(IllegalArgumentException.class, () -> gameService.isValidGame(game));
    }

    @Test
    void testIsValidGame_NotEnoughPlayers() {
        Game game = new Game();
        game.setTurn(new Turn());
        GameService gameService = new GameService();
        assertThrows(IllegalArgumentException.class, () -> gameService.isValidGame(game));
    }

    @Test
    void testIsValidGame_NoMap() {
        Game game = new Game();
        game.setTurn(new Turn());
        GameService gameService = new GameService();
        game.getTurn().getInGamePlayerList().add(new Player("a"));
        assertThrows(IllegalArgumentException.class, () -> gameService.isValidGame(game));
    }

    @Test
    void testIsValidGame_Valid() {
        Game game = new Game();
        game.setTurn(new Turn());
        GameService gameService = new GameService();
        game.getTurn().getInGamePlayerList().add(new Player("a"));
        game.setSvgMap("test");
        assertThrows(IllegalArgumentException.class, () -> gameService.isValidGame(game));
    }

    @Test
    void testAddPlayer() {
        Game game = new Game();
        game.setTurn(new Turn());
        GameService gameService = new GameService();
        gameService.getGames().put("test", game);
        PlayerDto playerDto = new PlayerDto();

        playerDto.setName("test");
        playerDto.setColor("test");

        gameService.postAddPlayer(playerDto, "test");

        assertEquals(1, game.getTurn().getInGamePlayerList().size());

    }

    @Test
    void testGetWatchGame() {
        Game game = new Game();
        game.setTurn(new Turn());
        GameService gameService = new GameService();
        gameService.getGames().put("test", game);
        PlayerDto playerDto = new PlayerDto();

        playerDto.setName("test");
        playerDto.setColor("test");

        assertThrows(IllegalArgumentException.class, () -> gameService.getWatch("test"));

    }

}
