package com.project.progettorisikorisikiamobackend.services.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.progettorisikorisikiamobackend.Turno.Turn;
import com.project.progettorisikorisikiamobackend.domain.Game;
import com.project.progettorisikorisikiamobackend.map.Continent;
import com.project.progettorisikorisikiamobackend.map.Map;
import com.project.progettorisikorisikiamobackend.map.Territory;
import com.project.progettorisikorisikiamobackend.services.responce.ContinentDto;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.MapDto;
import com.project.progettorisikorisikiamobackend.services.responce.TerritoryDto;

public class GameMapper {

    private GameMapper() {
    }

    public static Game gameDtoToGame(GameDto gameDto, String id) {

        if (gameDto == null || id.isEmpty()) {
            return null;
        }

        Game game = new Game();
        game.setId(id);
        game.setName(gameDto.getName());
        game.setGameEnded(false);
        game.setTurn(null);

        Map map = new Map(gameDto.getMap().getName());

        HashMap<String, Territory> territories = new HashMap<>();
        HashMap<String, List<String>> neigbors = new HashMap<>();
        for (int i = 0; i < gameDto.getMap().getContinents().size(); i++) {

            Continent continent = new Continent(gameDto.getMap().getContinents().get(i).getName(),
                    gameDto.getMap().getContinents().get(i).getBonus());
            map.addContinent(continent);

            for (int j = 0; j < gameDto.getMap().getContinents().get(i).getTerritory().size(); j++) {

                Territory territory = new Territory(
                        gameDto.getMap().getContinents().get(i).getTerritory().get(j).getName());

                territories.put(territory.getName(), territory);

                neigbors.put(territory.getName(), gameDto.getMap().getContinents().get(i).getTerritory().get(j)
                        .getNeighbors());

                continent.addTerritory(territory);
            }
        }

        for (String key : neigbors.keySet()) {
            for (int i = 0; i < neigbors.get(key).size(); i++) {
                territories.get(key).addNeighbor(territories.get(neigbors.get(key).get(i)));
            }
        }

        game.setMap(map);
        game.setDeckObjectives(null);
        game.setDeckTerritories(null);

        return game;
    }

    public static GameDto gameToGameDto(Game game) {

        if (game == null) {
            return null;
        }
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setName(game.getName());
        gameDto.setGameEnded(game.isGameEnded());
        Map map = game.getMap();
        MapDto mapDto = new MapDto();

        mapDto.setName(map.getMapId());

        List<ContinentDto> continentDto = new ArrayList<>();

        ArrayList<Continent> continents = new ArrayList<>(map.getContinents().values());

        for (int i = 0; i < continents.size(); i++) {

            ContinentDto continentDtoResult = new ContinentDto();
            continentDtoResult.setName(continents.get(i).getName());
            continentDtoResult.setBonus(continents.get(i).getBonus());

            ArrayList<Territory> territories = new ArrayList<>(continents.get(i).getTerritories().values());

            List<TerritoryDto> territoryDto = new ArrayList<>();

            for (int j = 0; j < territories.size(); j++) {
                TerritoryDto territoryDtoResult = new TerritoryDto();
                territoryDtoResult.setName(territories.get(j).getName());

                // ? Restituisce il nome player se si vuole l'id modificare sotto
                String owner = territories.get(j).getOwner() == null ? "null" : territories.get(j).getOwner().getName();
                territoryDtoResult.setOwner(owner);
                territoryDtoResult.setArmy(territories.get(j).getArmy());

                ArrayList<Territory> neighbors = territories.get(j).getNeighbors();

                List<String> neighborsName = new ArrayList<>();
                for (int k = 0; k < neighbors.size(); k++) {
                    neighborsName.add(neighbors.get(k).getName());
                }

                territoryDtoResult.setNeighbors(neighborsName);
                territoryDto.add(territoryDtoResult);
            }

            continentDtoResult.setTerritory(territoryDto);

            continentDto.add(continentDtoResult);

        }

        mapDto.setContinents(continentDto);

        gameDto.setMap(mapDto);

        return gameDto;
    }

}
