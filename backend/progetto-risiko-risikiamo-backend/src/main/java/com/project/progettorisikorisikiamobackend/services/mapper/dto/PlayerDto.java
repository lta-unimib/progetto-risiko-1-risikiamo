package com.project.progettorisikorisikiamobackend.services.mapper.dto;

import com.project.progettorisikorisikiamobackend.player.Player;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerDto {

    @NonNull
    @NotBlank
    private String id;

    public Player toPlayer() {
        return new Player(id);
    }
}
