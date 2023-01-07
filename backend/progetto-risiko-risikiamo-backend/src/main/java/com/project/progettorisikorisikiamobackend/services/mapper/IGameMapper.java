package com.project.progettorisikorisikiamobackend.services.mapper;

import org.mapstruct.Mapper;

import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.GameDto;

@Mapper(componentModel = "spring")
public interface IGameMapper {

    public GameDto toDto(Game game);

    public Game fromDto(GameDto gameDto);

}
