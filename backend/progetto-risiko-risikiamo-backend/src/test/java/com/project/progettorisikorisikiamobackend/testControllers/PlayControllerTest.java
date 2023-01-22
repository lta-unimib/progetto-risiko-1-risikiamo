package com.project.progettorisikorisikiamobackend.testControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.progettorisikorisikiamobackend.controllers.PlayController;
import com.project.progettorisikorisikiamobackend.exeptions.NotFoundExeption;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.IPlayerService;
import com.project.progettorisikorisikiamobackend.services.mapper.PlayerMapper;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

public class PlayControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IPlayerService playerService;

    @InjectMocks
    private PlayController playController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playController).build();
    }

    @Test
    public void testGetPlayer() throws Exception {
        String gameId = "game1";
        String playerId = "player1";
        Player player = new Player(playerId);

        when(playerService.getPlayer(gameId, playerId)).thenReturn(player);

        PlayerDto playerDto = PlayerMapper.toDto(player);
        String playerDtoJson = objectMapper.writeValueAsString(playerDto);

        mockMvc.perform(get("/api/v1/game/{gameId}/play/{playerId}/", gameId, playerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(playerDtoJson));
    }

    @Test
    public void testPutSkip() throws Exception {
        String gameId = "game1";
        String playerId = "player1";

        mockMvc.perform(put("/api/v1/game/{gameId}/play/{playerId}/skip", gameId, playerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Turn Skipped"));
        verify(playerService).endTurn(gameId, playerId);
    }

    @Test
    public void testPutSurrend() throws Exception {
        String gameId = "game1";
        String playerId = "player1";

        mockMvc.perform(put("/api/v1/game/{gameId}/play/{playerId}/surrend", gameId, playerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Surrend"));
        verify(playerService).surrend(gameId, playerId);
    }

    @Test
    public void testPutAttack() throws Exception {
        String gameId = "game1";
        String playerId = "player1";
        String owner = "country1";
        String neighbor = "country2";
        int army = 10;

        mockMvc.perform(put("/api/v1/game/{gameId}/play/{playerId}/attack", gameId, playerId)
                .param("owner", owner)
                .param("neighbor", neighbor)
                .param("army", String.valueOf(army))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Attack"));
        verify(playerService).attack(gameId, playerId, owner, neighbor, army);
    }

    @Test
    public void testPutMove() throws Exception {
        String gameId = "game1";
        String playerId = "player1";
        String owner = "country1";
        String neighbor = "country2";
        int army = 10;

        mockMvc.perform(put("/api/v1/game/{gameId}/play/{playerId}/move", gameId, playerId)
                .param("owner", owner)
                .param("neighbor", neighbor)
                .param("army", String.valueOf(army))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Move"));
        verify(playerService).move(gameId, playerId, owner, neighbor, army);
    }

    @Test
    void testHandleNotFound() throws Exception {
        String message = "Game not found";
        doThrow(new NotFoundExeption(message)).when(playerService).endTurn("not_found_id", "not_found_id");
        ;

        mockMvc.perform(put("/api/v1/game/not_found_id/play/not_found_id/skip"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testHandleException() throws Exception {
        String message = "Invalid input";
        doThrow(new IllegalArgumentException(message)).when(playerService).attack("invalid_id", "invalid_id",
                "invalid_id", "invalid_id", -10);

        mockMvc.perform(put("/api/v1/game/123/play/123/attack"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutPlace() {
        // arrange
        String gameId = "game1";
        String playerId = "player1";
        String owner = "country1";
        int army = 5;
        doNothing().when(playerService).placeReinforcements(gameId, playerId, owner, army);

        // act
        ResponseEntity<String> response = playController.putPlace(gameId, playerId, owner, army);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Piazza rinforzi", response.getBody());
        verify(playerService, times(1)).placeReinforcements(gameId, playerId, owner, army);
    }

    @Test
    public void testPutRenforce() {
        String gameId = "game1";
        String playerId = "player1";
        String c1 = "c1";
        String c2 = "c2";
        String c3 = "c3";

        playController.putRenforce(gameId, playerId, c1, c2, c3);

        verify(playerService).redeemReinforcementsCard(gameId, playerId, c1, c2, c3);
    }
}
