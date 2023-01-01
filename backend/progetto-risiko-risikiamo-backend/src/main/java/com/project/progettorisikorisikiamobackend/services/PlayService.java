package com.project.progettorisikorisikiamobackend.services;

import org.springframework.stereotype.Service;

@Service
public class PlayService implements IPlayerService {

    @Override
    public void putTurn(String gameId, String playerId) {
        // TODO cominciare il turno del giocaore con id playerId nel gioco con id gameId

    }

    @Override
    public void putSkip(String gameId, String playerId) {
        // TODO saltare il turno del giocaore con id playerId nel gioco con id gameId

    }

    @Override
    public void putSurrend(String gameId, String playerId) {
        // TODO abbandonare il gioco con id gameId

    }

    @Override
    public void putAttack(String gameId, String playerId) {
        // TODO attaccare con il giocatore con id playerId nel gioco con id gameId

    }

    @Override
    public void putMove(String gameId, String playerId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void putPlace(String gameId, String playerId) {
        // TODO Auto-generated method stub

    }

}
