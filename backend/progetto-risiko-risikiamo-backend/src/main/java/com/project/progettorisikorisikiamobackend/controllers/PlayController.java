package com.project.progettorisikorisikiamobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.progettorisikorisikiamobackend.services.IPlayerService;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.PlayerDto;

import jakarta.validation.Valid;

@RestController("/api/v1/game/{gameId}/play/{playerId}")
public class PlayController {

    // ? Codice non 100% RESTlike, ma per ora va bene, più facile da leggere

    @Autowired
    private IPlayerService playerService;

    @ResponseBody
    @PostMapping("/{gameId}/addPlayer")
    public ResponseEntity<Object> postAddPlayer(@Valid @RequestBody PlayerDto playerDto,
            @PathVariable String gameId) {

        playerService.postAddPlayer(playerDto, gameId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/turn")
    public ResponseEntity<String> putTurn(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putTurn(playerId, gameId);
        return ResponseEntity.ok("Start Turn of player " + playerId);
    }

    @PutMapping("/skip")
    public ResponseEntity<String> putSkip(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putSkip(playerId, gameId);
        return ResponseEntity.ok("Turn Skipped");
    }

    @PutMapping("/surrend")
    public ResponseEntity<String> putSurrend(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putSurrend(playerId, gameId);
        return ResponseEntity.ok("Surrend");
    }

    @PutMapping("/attack")
    public ResponseEntity<String> putAttack(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putAttack(playerId, gameId);
        return ResponseEntity.ok("Attack");
    }

    @PutMapping("/move")
    public ResponseEntity<String> putMove(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putMove(playerId, gameId);
        return ResponseEntity.ok("Move");
    }

    @PutMapping("/place")
    public ResponseEntity<String> putPlace(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putPlace(playerId, gameId);
        return ResponseEntity.ok("Piazza rinforzi");
    }

    @PutMapping("/renforce")
    public ResponseEntity<String> putRenforce(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.putRenforce(playerId, gameId);
        return ResponseEntity.ok("riscatta rinforzi");
    }

}
