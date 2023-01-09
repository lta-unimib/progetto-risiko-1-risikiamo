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

import com.project.progettorisikorisikiamobackend.services.IGameService;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.GameDto;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.PlayerDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private IGameService gameService;

    @ResponseBody
    @GetMapping("/{gameId}/watch")
    public ResponseEntity<String> getWatch(@PathVariable String gameId) {

        gameService.getWatch(gameId);
        return ResponseEntity.ok("Watching game");

    }

    @ResponseBody
    @PostMapping("/{gameId}/addPlayer")
    public ResponseEntity<Object> postAddPlayer(@Valid @RequestBody PlayerDto playerDto,
            @PathVariable String gameId) {

        gameService.postAddPlayer(playerDto, gameId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity<String> postCreate(@Valid @RequestBody GameDto gameDto) {

        gameService.postCreate(gameDto);
        return ResponseEntity.ok("Game created");
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

}
