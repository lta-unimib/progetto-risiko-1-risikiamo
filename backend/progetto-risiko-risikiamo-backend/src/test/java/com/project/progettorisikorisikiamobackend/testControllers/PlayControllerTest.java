package com.project.progettorisikorisikiamobackend.testControllers;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.progettorisikorisikiamobackend.controllers.PlayController;
import com.project.progettorisikorisikiamobackend.player.Player;
import com.project.progettorisikorisikiamobackend.services.IPlayerService;
import com.project.progettorisikorisikiamobackend.services.mapper.PlayerMapper;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;
import static org.mockito.Mockito.when;

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
}
