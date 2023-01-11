package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.domain.Player;
import com.project.progettorisikorisikiamobackend.map.Map;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameDto {

    @NonNull
    @NotBlank
    private List<PlayerDto> players;

    @NonNull
    @NotBlank
    private MapDto map;

    public Game toGame(String id) {

        Map mapReturn = this.map.toMap();

        ArrayList<Player> playersReturn = new ArrayList<>();

        for (PlayerDto player : this.players) {
            playersReturn.add(player.toPlayer());
        }

        return new Game(playersReturn, mapReturn, id);

    }

}
