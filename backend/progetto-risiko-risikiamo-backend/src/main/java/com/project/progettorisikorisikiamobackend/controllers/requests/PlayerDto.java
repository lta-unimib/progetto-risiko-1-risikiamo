package com.project.progettorisikorisikiamobackend.controllers.requests;

import com.project.progettorisikorisikiamobackend.map.PlayerPlaceholder;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class PlayerDto {

    @NonNull
    @NotBlank
    private String name;

    public PlayerPlaceholder toPlayer() {
        return new PlayerPlaceholder(name);
    }

}
