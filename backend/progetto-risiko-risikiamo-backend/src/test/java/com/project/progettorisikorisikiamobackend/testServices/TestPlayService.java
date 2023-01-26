package com.project.progettorisikorisikiamobackend.testServices;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Cards.DeckTerritories;
import com.project.progettorisikorisikiamobackend.Cards.EnumCard;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.exeptions.NotFoundExeption;
import com.project.progettorisikorisikiamobackend.gameState.ActionState;
import com.project.progettorisikorisikiamobackend.gameState.NewTurnState;
import com.project.progettorisikorisikiamobackend.gameState.RenforceState;
import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.GameService;
import com.project.progettorisikorisikiamobackend.services.PlayService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestPlayService {

    @Mock
    private GameService gameService;

    @InjectMocks
    private PlayService playService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testGetPlayer_PlayerFoundInGame() throws NotFoundExeption {
        String gameId = "game1";
        String playerId = "player1";
        Game game = mock(Game.class);
        Turn turn = mock(Turn.class);
        Player player = mock(Player.class);

        List<Player> playersInGame = new ArrayList<>();
        playersInGame.add(player);

        when(game.getTurn()).thenReturn(turn);
        when(turn.getInGamePlayerList()).thenReturn(playersInGame);
        when(gameService.getGame(gameId)).thenReturn(game);
        when(player.getId()).thenReturn(playerId);

        Player result = playService.getPlayer(gameId, playerId);

        verify(gameService).getGame(gameId);
        verify(game).getTurn();
        verify(turn).getInGamePlayerList();
        Assertions.assertEquals(result, player);
    }

    @Test
    public void testGetPlayer_PlayerFoundDefeated() throws NotFoundExeption {
        String gameId = "game1";
        String playerId = "player1";
        Game game = mock(Game.class);
        Turn turn = mock(Turn.class);
        Player player = mock(Player.class);

        List<Pair<Player, Player>> playersDefeated = new ArrayList<>();
        playersDefeated.add(Pair.of(player, mock(Player.class)));

        when(game.getTurn()).thenReturn(turn);
        when(turn.getDefeatedPlayerList()).thenReturn(playersDefeated);
        when(gameService.getGame(gameId)).thenReturn(game);
        when(player.getId()).thenReturn(playerId);

        Player result = playService.getPlayer(gameId, playerId);

        verify(gameService).getGame(gameId);
        verify(game).getTurn();
        verify(turn).getDefeatedPlayerList();
        Assertions.assertEquals(result, player);
    }

    @Test
    void tesSurrend() throws NotFoundExeption {
        Game game = new Game("test", new Map("a"), "test");
        Turn turn = new Turn();

        game.setTurn(turn);
        Player p1 = new Player("test", "test", "test");
        Player p2 = new Player("test2", "test2", "test2");
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.startGame();
        when(gameService.getGame(anyString())).thenReturn(game);
        playService.surrend("test", "test");

        assertEquals(p1, turn.getDefeatedPlayerList().get(0).getLeft());
    }

    @Test
    void testAttackEccezione() {
        Game game = mock(Game.class);
        when(gameService.getGame("gameId")).thenReturn(game);
        Map map = mock(Map.class);
        when(game.getMap()).thenReturn(map);

        when(map.getTerritory("owner")).thenReturn(null);
        when(map.getTerritory("target")).thenReturn(null);
        assertThrows(NotFoundExeption.class, () -> {
            playService.attack("gameId", "playerId", "owner", "target", 10);
        });

    }

    @Test
    void testAttack() {
        Turn turn = new Turn();
        Map map = new Map("map");
        Player p1 = new Player("p1", "p1", "p1");
        Player p2 = new Player("p2", "p2", "p2");
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");

        Continent c = new Continent("c", 1);

        t1.addNeighbour(t2);
        t2.addNeighbour(t1);

        t1.setOwner(p1);
        t2.setOwner(p2);

        t1.addArmy(10);
        t2.addArmy(1);

        c.addTerritory(t1);
        c.addTerritory(t2);

        map.addContinent(c);

        turn.addPlayer(p1);
        turn.addPlayer(p2);

        Game game = new Game("game", map, "game");
        game.setTurn(turn);

        game.setState(new ActionState(game));

        when(gameService.getGame("game")).thenReturn(game);

        playService.attack("game", "p1", "t1", "t2", 9);

        assertEquals(p1, t2.getOwner());

    }

    @Test
    void testMove() {
        Turn turn = new Turn();
        Map map = new Map("map");
        Player p1 = new Player("p1", "p1", "p1");
        Player p2 = new Player("p2", "p2", "p2");
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");

        Continent c = new Continent("c", 1);

        t1.addNeighbour(t2);
        t2.addNeighbour(t1);

        t1.setOwner(p1);
        t2.setOwner(p1);

        t1.addArmy(10);
        t2.addArmy(1);

        c.addTerritory(t1);
        c.addTerritory(t2);

        map.addContinent(c);

        turn.addPlayer(p1);
        turn.addPlayer(p2);

        Game game = new Game("game", map, "game");
        game.setTurn(turn);

        game.setState(new ActionState(game));

        when(gameService.getGame("game")).thenReturn(game);

        playService.move("game", "p1", "t1", "t2", 9);

        assertEquals(10, t2.getArmy());

    }

    @Test
    void testPlace() {
        Turn turn = new Turn();
        Map map = new Map("map");
        Player p1 = new Player("p1", "p1", "p1");
        Player p2 = new Player("p2", "p2", "p2");
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");

        Continent c = new Continent("c", 1);

        t1.addNeighbour(t2);
        t2.addNeighbour(t1);

        t1.setOwner(p1);
        t2.setOwner(p2);

        t1.addArmy(1);
        t2.addArmy(1);

        c.addTerritory(t1);
        c.addTerritory(t2);

        map.addContinent(c);

        turn.addPlayer(p1);
        turn.addPlayer(p2);

        p1.setReinforce(1);

        Game game = new Game("game", map, "game");
        game.setTurn(turn);

        game.setState(new RenforceState(game));

        when(gameService.getGame("game")).thenReturn(game);

        playService.placeReinforcements("game", "p1", "t1", 1);

        assertEquals(2, t1.getArmy());

    }

    @Test
    void testRedeemCards() {
        Turn turn = new Turn();
        Map map = new Map("map");
        Player p1 = new Player("p1", "p1", "p1");
        Player p2 = new Player("p2", "p2", "p2");
        Territory t1 = new Territory("t1");
        Territory t2 = new Territory("t2");
        Territory t3 = new Territory("t3");

        Continent c = new Continent("c", 1);

        t1.addNeighbour(t2);
        t2.addNeighbour(t1);
        t3.addNeighbour(t1);

        t1.setOwner(p1);
        t2.setOwner(p2);
        t3.setOwner(p1);

        t1.addArmy(1);
        t2.addArmy(1);
        t3.addArmy(1);

        c.addTerritory(t1);
        c.addTerritory(t2);
        c.addTerritory(t3);
        map.addContinent(c);

        turn.addPlayer(p1);
        turn.addPlayer(p2);

        p1.setReinforce(1);

        CardTerritory card1 = new CardTerritory(t1.getName(), EnumCard.CAVALLO);
        CardTerritory card2 = new CardTerritory(t2.getName(), EnumCard.CAVALLO);
        CardTerritory card3 = new CardTerritory(t3.getName(), EnumCard.CAVALLO);

        DeckTerritories deck = new DeckTerritories(map);

        deck.draw();
        deck.draw();
        deck.draw();

        Game game = new Game("game", map, "game");
        game.setDeckTerritories(deck);
        game.setTurn(turn);

        p1.addCard(card1);
        p1.addCard(card2);
        p1.addCard(card3);
        game.setState(new NewTurnState(game));

        when(gameService.getGame("game")).thenReturn(game);

        playService.redeemReinforcementsCard("game", "p1", t1.getName(), t2.getName(), t2.getName());

        assertEquals(11, p1.getReinforce());

    }

}