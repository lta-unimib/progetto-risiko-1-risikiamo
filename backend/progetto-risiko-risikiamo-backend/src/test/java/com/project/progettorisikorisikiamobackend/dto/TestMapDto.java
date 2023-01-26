package com.project.progettorisikorisikiamobackend.dto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.project.progettorisikorisikiamobackend.Cards.CardTerritory;
import com.project.progettorisikorisikiamobackend.domain.Game;

import com.project.progettorisikorisikiamobackend.services.GameService;
import com.project.progettorisikorisikiamobackend.services.mapper.CardMapper;
import com.project.progettorisikorisikiamobackend.services.mapper.PlayerMapper;
import com.project.progettorisikorisikiamobackend.services.responce.CardDto;
import com.project.progettorisikorisikiamobackend.services.responce.ContinentDto;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.MapDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;
import com.project.progettorisikorisikiamobackend.services.responce.TerritoryDto;

class TestMapDto {
    @Test
    public void testToEntity() {
        // Comprende test non solo per MapDto ma anche mi aiuta avere tutte le
        // inizializazioni in un unico test

        GameService gameService = new GameService();
        GameDto gameDto = new GameDto();

        MapDto mapDto = new MapDto();
        gameDto.setMap(mapDto);
        assertThrows(IllegalArgumentException.class, () -> gameService.postCreate(gameDto));
        ContinentDto countryDto = new ContinentDto();

        TerritoryDto territory1Dto = new TerritoryDto();
        TerritoryDto territory2Dto = new TerritoryDto();
        TerritoryDto territory3Dto = new TerritoryDto();
        TerritoryDto territory4Dto = new TerritoryDto();
        TerritoryDto territory5Dto = new TerritoryDto();

        PlayerDto playerDto = new PlayerDto();
        playerDto.setName("playerName");
        playerDto.setColor("playerColor");

        territory1Dto.setName("territoryName");
        territory1Dto.setNeighbours(new ArrayList<>(List.of("territoryName2")));

        territory2Dto.setName("territoryName2");
        territory2Dto.setNeighbours(new ArrayList<>(List.of("territoryName")));

        territory3Dto.setName("territoryName3");
        territory3Dto.setNeighbours(new ArrayList<>(List.of("territoryName2")));

        territory4Dto.setName("territoryName4");
        territory4Dto.setNeighbours(new ArrayList<>(List.of("territoryName")));

        territory5Dto.setName("territoryName5");
        territory5Dto.setNeighbours(new ArrayList<>(List.of("territoryName2")));

        countryDto.setName("countryName");
        countryDto.setTerritory(new ArrayList<>(List.of(territory1Dto, territory2Dto)));

        mapDto.setContinents(new ArrayList<>(List.of(countryDto)));
        assertThrows(IllegalArgumentException.class, () -> gameService.postCreate(gameDto));
        mapDto.setName("nome");
        assertThrows(IllegalArgumentException.class, () -> gameService.postCreate(gameDto));

        gameDto.setName("name");
        assertThrows(IllegalArgumentException.class, () -> gameService.postCreate(gameDto));
        gameDto.setSvgMap("mappa svg");
        assertThrows(IllegalArgumentException.class, () -> gameService.postCreate(gameDto));
        gameDto.setStartingArmies(1);
        assertThrows(IllegalArgumentException.class, () -> gameService.postCreate(gameDto));
        countryDto.setTerritory(
                new ArrayList<>(List.of(territory1Dto, territory2Dto, territory3Dto, territory4Dto, territory5Dto)));
        GameDto g = gameService.postCreate(gameDto);

        Game game = gameService.getGame(g.getId());

        assertEquals("name", game.getName());
        assertEquals("mappa svg", game.getSvgMap());
        assertEquals(1, game.getStartingArmies());

        PlayerDto playerDto1 = new PlayerDto();
        playerDto1.setName("playerName1");
        playerDto1.setColor("playerColor1");

        CardDto cardDto1 = new CardDto();
        cardDto1.setName("territoryName");
        cardDto1.setType("INFATERY");

        playerDto1.setCards(new ArrayList<>(List.of(cardDto1)));

        PlayerDto playerDto2 = new PlayerDto();
        playerDto2.setName("playerName2");
        playerDto2.setColor("playerColor2");

        gameService.postAddPlayer(playerDto1, g.getId());
        gameService.postAddPlayer(playerDto2, g.getId());

        gameService.putStart(g.getId());
        gameService.getWatch(g.getId());
        gameService.putEnd(g.getId());

        assertEquals(1, gameService.getGames().size());

        PlayerDto pDto = PlayerMapper.toDto(gameService.getGame(g.getId()).getTurn().getCurrentPlayer());
        assertEquals("playerName1", pDto.getName());

    }

    @Test
    void testMapperCard() {
        CardDto cardDto = new CardDto();
        cardDto.setName("territoryName");
        cardDto.setType("CAVALLO");

        CardTerritory c = CardMapper.toEntity(cardDto);

        cardDto = CardMapper.toDto(c);

        assertEquals("territoryName", cardDto.getName());
        assertEquals("CAVALLO", cardDto.getType());

    }

}
