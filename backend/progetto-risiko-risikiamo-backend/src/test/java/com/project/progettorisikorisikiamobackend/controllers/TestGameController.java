package com.project.progettorisikorisikiamobackend.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.progettorisikorisikiamobackend.services.GameService;
import com.project.progettorisikorisikiamobackend.services.IGameService;
import com.project.progettorisikorisikiamobackend.services.mapper.dto.PlayerDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class TestGameController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGameService gameService;

    @Test
    public void testGetWatch() throws Exception {
        String gameId = "12345";
        mockMvc.perform(get("/api/v1/game/" + gameId + "/watch"))
                .andExpect(status().isOk())
                .andExpect(content().string("Watching game"));
        verify(gameService, times(1)).getWatch(gameId);
    }

    @Test
    public void testPostAddPlayer() throws Exception {
        String gameId = "12345";
        PlayerDto playerDto = new PlayerDto();
        mockMvc.perform(post("/api/v1/game/" + gameId + "/addPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(playerDto)))
                .andExpect(status().isCreated());
        verify(gameService, times(1)).postAddPlayer(playerDto, gameId);
    }

}