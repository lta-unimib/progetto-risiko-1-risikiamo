package com.project.progettorisikorisikiamobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.project.progettorisikorisikiamobackend.services.IPlayerService;

@RestController
@RequestMapping("/api/v1/game/{gameId}/play/{playerId}")
public class PlayController {

    // ? Codice non 100% RESTlike, ma per ora va bene, più facile da leggere

    @Autowired
    private IPlayerService playerService;

    @PutMapping("/turn")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putTurn(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putTurn(playerId, gameId);
        return ResponseEntity.ok("Start Turn of player " + playerId);
    }

    @PutMapping("/skip")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putSkip(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putSkip(playerId, gameId);
        return ResponseEntity.ok("Turn Skipped");
    }

    @PutMapping("/surrend")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putSurrend(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putSurrend(playerId, gameId);
        return ResponseEntity.ok("Surrend");
    }

    @PutMapping("/attack")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putAttack(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putAttack(playerId, gameId);
        return ResponseEntity.ok("Attack");
    }

    @PutMapping("/move")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putMove(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putMove(playerId, gameId);
        return ResponseEntity.ok("Move");
    }

    @PutMapping("/place")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putPlace(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putPlace(playerId, gameId);
        return ResponseEntity.ok("Piazza rinforzi");
    }

    @PutMapping("/renforce")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putRenforce(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putRenforce(playerId, gameId);
        return ResponseEntity.ok("riscatta rinforzi");
    }

}
