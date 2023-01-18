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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.progettorisikorisikiamobackend.services.IGameService;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private IGameService gameService;

    @ResponseBody
    @GetMapping("/{gameId}/watch")
    @ResponseStatus(HttpStatus.OK)
    public GameDto getWatch(@PathVariable String gameId) {

        return gameService.getWatch(gameId);

    }

    @ResponseBody
    @PostMapping("/{gameId}/addPlayer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> postAddPlayer(@Valid @RequestBody PlayerDto playerDto,
            @PathVariable String gameId) {

        String id = gameService.postAddPlayer(playerDto, gameId);
        return new ResponseEntity<>(id, HttpStatus.CREATED);

    }

    @ResponseBody
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public GameDto postCreate(@Valid @RequestBody GameDto gameDto) {

        return gameService.postCreate(gameDto);

    }

    @PutMapping("/{gameId}/start")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putStart(@Valid @PathVariable String gameId) {

        gameService.putStart(gameId);
        return ResponseEntity.ok("Game started");

    }

    @PutMapping("/{gameId}/end")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> putEnd(@Valid @PathVariable String gameId) {

        gameService.putEnd(gameId);
        return ResponseEntity.ok("Game ended");

    }

}
