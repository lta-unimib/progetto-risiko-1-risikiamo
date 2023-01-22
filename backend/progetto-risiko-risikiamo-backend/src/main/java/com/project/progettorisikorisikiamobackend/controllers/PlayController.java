package com.project.progettorisikorisikiamobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.project.progettorisikorisikiamobackend.exeptions.NotFoundExeption;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.IPlayerService;
import com.project.progettorisikorisikiamobackend.services.mapper.PlayerMapper;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

@CrossOrigin(origins = "http://localhost:8080")
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
        playerService.endTurn(gameId, playerId);
        return ResponseEntity.ok("Turn Skipped");
    }

    @PutMapping("/surrend")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putSurrend(@PathVariable String gameId, @PathVariable String playerId) {
        playerService.surrend(gameId, playerId);
        return ResponseEntity.ok("Surrend");
    }

    @PutMapping("/attack")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putAttack(@PathVariable String gameId, @PathVariable String playerId,
            @RequestParam String owner,
            @RequestParam String neighbor,
            @RequestParam int army) {
        playerService.attack(gameId, playerId, owner, neighbor, army);
        return ResponseEntity.ok("Attack");
    }

    @PutMapping("/move")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putMove(@PathVariable String gameId, @PathVariable String playerId, String owner,
            String neighbor, int army) {
        playerService.move(gameId, playerId, owner, neighbor, army);
        return ResponseEntity.ok("Move");
    }

    @PutMapping("/place")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putPlace(@PathVariable String gameId, @PathVariable String playerId,
            @RequestParam String owner,
            @RequestParam int army) {
        playerService.placeReinforcements(gameId, playerId, owner, army);
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

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler(value = { NotFoundExeption.class })
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }
}
