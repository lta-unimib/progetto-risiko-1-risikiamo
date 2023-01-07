package com.project.progettorisikorisikiamobackend.services.mapper;

import org.mapstruct.Mapper;

import com.project.progettorisikorisikiamobackend.domain.Player;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.PlayerDto;

@Mapper(componentModel = "spring")
public interface IPlayerMapper {

    public PlayerDto toDto(Player player);

    public Player fromDto(PlayerDto playerDto);

}
