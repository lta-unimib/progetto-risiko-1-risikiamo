package com.project.progettorisikorisikiamobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.IPlayerService;
import com.project.progettorisikorisikiamobackend.services.mapper.PlayerMapper;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

@RestController
@RequestMapping("/api/v1/game/{gameId}/play/{playerId}")

public class PlayController {

    // ? Codice non 100% RESTlike, ma per ora va bene, più facile da leggere

    @Autowired
    private IPlayerService playerService;

    @GetMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PlayerDto getPlayer(@PathVariable String gameId, @PathVariable String playerId) {
        Player player = playerService.getPlayer(gameId, playerId);
        return PlayerMapper.toDto(player);
    }

    @PutMapping("/skip")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putSkip(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.endTurn(playerId, gameId);
        return ResponseEntity.ok("Turn Skipped");
    }

    @PutMapping("/surrend")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putSurrend(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.surrend(playerId, gameId);
        return ResponseEntity.ok("Surrend");
    }

    @PutMapping("/attack")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putAttack(@PathVariable String gameId, @PathVariable String playerId, String owner,
            String neighbor, int army) {
        playerService.attack(playerId, gameId, owner, neighbor, army);
        return ResponseEntity.ok("Attack");
    }

    @PutMapping("/move")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putMove(@PathVariable String gameId, @PathVariable String playerId, String owner,
            String neighbor, int army) {
        playerService.move(playerId, gameId, owner, neighbor, army);
        return ResponseEntity.ok("Move");
    }

    @PutMapping("/place")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putPlace(@PathVariable String gameId, @PathVariable String playerId, String owner,
            int armies) {
        playerService.placeReinforcements(gameId, playerId, owner, armies);
        return ResponseEntity.ok("Piazza rinforzi");
    }

    @PutMapping("/renforce")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putRenforce(@PathVariable String gameId, @PathVariable String playerId, String c1,
            String c2, String c3) {
        playerService.redeemReinforcementsCard(gameId, playerId, c1, c2, c3);
        return ResponseEntity.ok("riscatta rinforzi");
    }

}
