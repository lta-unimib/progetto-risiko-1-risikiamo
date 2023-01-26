package com.project.progettorisikorisikiamobackend.services.mapper;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.Cards.EnumCard;
import com.project.progettorisikorisikiamobackend.services.responce.CardDto;

public class CardMapper {

    private CardMapper() {
    }

    public static CardDto toDto(CardTerritory card) {
        CardDto cardDto = new CardDto();
        cardDto.setType(card.getType().toString());
        cardDto.setName(card.getName());
        return cardDto;
    }

    public static CardTerritory toEntity(CardDto cardDto) {

        String type = cardDto.getType();
        String name = cardDto.getName();
        return new CardTerritory(name, Enum.valueOf(EnumCard.class, type));

    }

}
