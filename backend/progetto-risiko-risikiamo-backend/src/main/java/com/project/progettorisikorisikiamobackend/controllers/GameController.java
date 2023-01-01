package com.project.progettorisikorisikiamobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.progettorisikorisikiamobackend.controllers.requests.GameDto;
import com.project.progettorisikorisikiamobackend.controllers.requests.PlayerDto;
import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;
import com.project.progettorisikorisikiamobackend.services.IGameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private IGameService gameService;

    @ResponseBody
    @GetMapping("/{gameId}/watch")
    public ResponseEntity<String> getWatch(@PathVariable String id) {

        gameService.getWatch(id);
        return ResponseEntity.ok("Watching game");

    }

    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity<String> postCreate(@Valid @RequestBody GameDto gameDto) {

        gameService.postCreate(gameDto);
        return ResponseEntity.ok("Game created");
    }

    @ResponseBody
    @PostMapping("/{gameId}/addPlayer")
    public ResponseEntity<PlayerPlaceholder> postAddPlayer(@Valid @RequestBody PlayerDto playerDto,
            @PathVariable String id) {

        gameService.postAddPlayer(playerDto, id);
        return new ResponseEntity<>(playerDto.toPlayer(), HttpStatus.CREATED);

    }

    @PutMapping("/{gameId}/start")
    @ResponseBody
    public ResponseEntity<String> putStart(@PathVariable String id) {

        gameService.putStart(id);
        return ResponseEntity.ok("Game started");

    }

    @PutMapping("/{gameId}/end")
    @ResponseBody
    public ResponseEntity<String> putEnd(@PathVariable String id) {

        gameService.putEnd(id);
        return ResponseEntity.ok("Game ended");

    }

    @PutMapping("/{gameId}/play/{playerId}")
    @ResponseBody
    public ResponseEntity<String> putPlay(@PathVariable String gameId, @PathVariable String playerId) {

        gameService.putPlay(gameId, playerId);
        return ResponseEntity.ok("Notify system that player is playing");

    }

}
