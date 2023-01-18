package com.project.progettorisikorisikiamobackend.services.mapper;

import java.util.ArrayList;
import java.util.List;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.responce.CardDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

public class PlayerMapper {

    private PlayerMapper() {
    }

    public static PlayerDto toDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName(player.getName());
        playerDto.setRenforcements(player.getReinforce());

        List<CardTerritory> cards = player.getCarte();
        List<CardDto> cardsDto = new ArrayList<>();

        for (CardTerritory card : cards) {
            cardsDto.add(CardMapper.toDto(card));
        }

        playerDto.setCards(cardsDto);
        return playerDto;
    }

    public static Player toEntity(PlayerDto playerDto) {
        return new Player(playerDto.getName(), playerDto.getColor(), playerDto.getName());

    }
}
