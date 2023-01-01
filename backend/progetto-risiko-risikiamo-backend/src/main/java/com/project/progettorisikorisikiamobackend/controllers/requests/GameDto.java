package com.project.progettorisikorisikiamobackend.controllers.requests;

import com.project.progettorisikorisikiamobackend.domain.Game;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    Game toGame() {
        return new Game(name);
    }

}
