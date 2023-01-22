package com.project.progettorisikorisikiamobackend.testControllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.project.progettorisikorisikiamobackend.controllers.GameController;
import com.project.progettorisikorisikiamobackend.exeptions.NotFoundExeption;
import com.project.progettorisikorisikiamobackend.services.IGameService;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import com.project.progettorisikorisikiamobackend.services.responce.PlayerDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGameService gameService;

    @Test
    void testGetWatch() throws Exception {
        String gameId = "123";
        GameDto gameDto = new GameDto();
        gameDto.setId(gameId);
        gameDto.setName("Test Game");

        when(gameService.getWatch(gameId)).thenReturn(gameDto);

        mockMvc.perform(get("/api/v1/game/{gameId}/watch", gameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(gameId)))
                .andExpect(jsonPath("$.name", is("Test Game")));
    }

    @Test
    void testPostAddPlayer() throws Exception {
        String gameId = "123";
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName("Player 1");
        String playerId = "456";

        when(gameService.postAddPlayer(playerDto, gameId)).thenReturn(playerId);

        mockMvc.perform(post("/api/v1/game/{gameId}/addPlayer", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Player 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(playerId));
    }

    @Test
    void testPostCreate() throws Exception {
        String gameId = "123";
        GameDto gameDto = new GameDto();
        gameDto.setId(gameId);
        gameDto.setName("Test Game");
        when(gameService.postCreate(gameDto)).thenReturn(gameDto);

        mockMvc.perform(post("/api/v1/game/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Game\",\"id\":\"123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(gameId)))
                .andExpect(jsonPath("$.name", is("Test Game")));
    }

    @Test
    void testPutStart() throws Exception {
        String gameId = "123";

        mockMvc.perform(put("/api/v1/game/{gameId}/start", gameId))
                .andExpect(status().isOk())
                .andExpect(content().string("Game started"));

        verify(gameService, times(1)).putStart(gameId);
    }

    @Test
    void testHandleException() throws Exception {
        String message = "Invalid input";
        doThrow(new IllegalArgumentException(message)).when(gameService).putStart("invalid_id");

        mockMvc.perform(put("/api/v1/game/invalid_id/start"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(message));
    }

    @Test
    void testHandleNotFound() throws Exception {
        String message = "Game not found";
        doThrow(new NotFoundExeption(message)).when(gameService).putEnd("not_found_id");

        mockMvc.perform(put("/api/v1/game/not_found_id/end"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetSvg() throws Exception {
        String gameId = "123";
        String svg = "<svg></svg>";

        when(gameService.getSvg(gameId)).thenReturn(svg);

        mockMvc.perform(get("/api/v1/game/{gameId}/svg", gameId))
                .andExpect(status().isOk())
                .andExpect(content().string(svg));

        verify(gameService, times(1)).getSvg(gameId);
    }

}
