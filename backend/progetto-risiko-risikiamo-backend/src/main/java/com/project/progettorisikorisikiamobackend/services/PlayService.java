package com.project.progettorisikorisikiamobackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.progettorisikorisikiamobackend.services.mapper.IPlayerMapper;

@Service
public class PlayService implements IPlayerService {

    @Autowired
    private GameService gameService;

    @Autowired(required = false)
    private IPlayerMapper playerMapper;

    @Override
    public void putTurn(String gameId, String playerId) {
        gameService.getGame(gameId);
        // TODO cominciare il turno del giocaore con id playerId nel gioco con id gameId

    }

    @Override
    public void putSkip(String gameId, String playerId) {
        gameService.getGame(gameId);
        // TODO saltare il turno del giocaore con id playerId nel gioco con id gameId

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

}
