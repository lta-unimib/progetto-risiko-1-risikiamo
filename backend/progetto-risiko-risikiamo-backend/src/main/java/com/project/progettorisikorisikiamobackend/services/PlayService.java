package com.project.progettorisikorisikiamobackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.player.Player;

@Service
public class PlayService implements IPlayerService {

    @Autowired
    private GameService gameService;

    @Override
    public void turn(String gameId, String playerId) {

    }

    @Override
    public void endTurn(String gameId, String playerId) {

        this.isPlayerTurn(gameId, playerId);
        gameService.getGame(gameId).endTurn();

    }

    @Override
    public void surrend(String gameId, String playerId) {

        this.isPlayerTurn(gameId, playerId);
        List<Player> player = gameService.getGame(gameId).getTurn().getInGamePlayerList();
        Turn turn = gameService.getGame(gameId).getTurn();
        Player ply = null;
        for (Player p : player) {
            if (p.getId().equals(playerId)) {
                ply = p;
            }
        }
        if (ply == null) {
            throw new RuntimeException("Player not found");
        }

        turn.surrender(ply);

    }

    @Override
    public void attack(String gameId, String playerId, String owner, String target, int army) {

        Territory tOwner = gameService.getGame(gameId).getMap().getTerritory(owner);
        Territory tTarget = gameService.getGame(gameId).getMap().getTerritory(target);

        this.isPlayerTurn(gameId, playerId);
        gameService.getGame(gameId).attack(tOwner, tTarget, army);

    }

    @Override
    public void move(String gameId, String playerId, String owner, String target, int army) {
        Territory tOwner = gameService.getGame(gameId).getMap().getTerritory(owner);
        Territory tTarget = gameService.getGame(gameId).getMap().getTerritory(target);

        this.isPlayerTurn(gameId, playerId);
        gameService.getGame(gameId).move(tOwner, tTarget, army);

    }

    @Override
    public void placeReinforcements(String gameId, String playerId, String target, int army) {

        Territory tTarget = gameService.getGame(gameId).getMap().getTerritory(target);

        this.isPlayerTurn(gameId, playerId);
        gameService.getGame(gameId).placeReinforcements(tTarget, army);

    }

    @Override
    public void redeemReinforcementsCard(String gameId, String playerId, String c1, String c2, String c3) {

        this.isPlayerTurn(gameId, playerId);
        Player player = gameService.getGame(gameId).getTurn().getCurrentPlayer();

        CardTerritory card1 = null;
        CardTerritory card2 = null;
        CardTerritory card3 = null;

        for (CardTerritory card : player.getCarte()) {

            if (card.getName().equals(c1)) {
                card1 = card;
            }
            if (card.getName().equals(c2)) {
                card2 = card;
            }
            if (card.getName().equals(c3)) {
                card3 = card;
            }
        }

        if (card1 == null || card2 == null || card3 == null) {
            throw new RuntimeException("Carta non valida");
        }

        gameService.getGame(gameId).redeemReinforcementsCard(card1, card2, card3);

    }

    @Override
    public void play(String gameId, String playerId) {
        // TODO comincia la partita

    }

    private void isPlayerTurn(String gameId, String playerId) {
        Game game = gameService.getGame(gameId);

        if (game == null)
            throw new RuntimeException("Game not found");

        if (!game.getTurn().getCurrentPlayer().getId().equals(playerId))
            throw new RuntimeException("Not your turn");

    }

}
