package com.project.progettorisikorisikiamobackend.testControllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.project.progettorisikorisikiamobackend.controllers.GameController;
import com.project.progettorisikorisikiamobackend.services.IGameService;
import com.project.progettorisikorisikiamobackend.services.responce.GameDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
