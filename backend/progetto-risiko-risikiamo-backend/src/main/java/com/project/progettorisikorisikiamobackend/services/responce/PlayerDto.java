package com.project.progettorisikorisikiamobackend.services.responce;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerDto {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String color;

    private List<String> objectives;

    private int renforcements;

    private List<CardDto> cards;

}
