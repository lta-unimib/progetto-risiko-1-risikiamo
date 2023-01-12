package com.project.progettorisikorisikiamobackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.progettorisikorisikiamobackend.domain.Game;

@Service
public class PlayService implements IPlayerService {

    @Autowired
    private GameService gameService;

    @Override
    public void putTurn(String gameId, String playerId) {
        Game game = gameService.getGame(gameId);

        if (game != null && this.isPlayerTurn(playerId)) {
            // Comincia turno
        }

    }

    @Override
    public void putSkip(String gameId, String playerId) {
        Game game = gameService.getGame(gameId);

        if (game != null && this.isPlayerTurn(playerId)) {
            // Finisce turno
        }

    }

    @Override
    public void putSurrend(String gameId, String playerId) {
        gameService.getGame(gameId);
        // TODO abbandonare il gioco con id gameId

    }

    @Override
    public void putAttack(String gameId, String playerId) {
        gameService.getGame(gameId);
        // TODO attaccare con il giocatore con id playerId nel gioco con id gameId

    }

    @Override
    public void putMove(String gameId, String playerId) {
        gameService.getGame(gameId);
        // TODO Muovere armata

    }

    @Override
    public void putPlace(String gameId, String playerId) {
        gameService.getGame(gameId);
        // TODO Piazzare rinforzi

    }

    @Override
    public void putRenforce(String gameId, String playerId) {
        // TODO Riveve Rinforzi

    }

    @Override
    public void putPlay(String gameId, String playerId) {
        // TODO comincia la partita

    }

    private boolean isPlayerTurn(String playerId) {
        // TODO controlla se il giocatore è il turno corrente
        return true;
    }

}
