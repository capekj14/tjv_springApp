package cz.cvut.fit.tjv.capekj14.semestral.project.api;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import cz.cvut.fit.tjv.capekj14.semestral.project.api.controller.PlayerController;
import cz.cvut.fit.tjv.capekj14.semestral.project.bussines.PlayerService;
import cz.cvut.fit.tjv.capekj14.semestral.project.domain.Player;


@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @MockBean
    PlayerService playerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllPlayers() throws Exception {
        Player karel = new Player(158 ,"karel", "karel", 15);
        Player franta = new Player(898 ,"franta", "franta", 65);
        List<Player> players = List.of(karel, franta);

        Mockito.when(playerService.findAll()).thenReturn(players);

        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].idPlayer", Matchers.is(158)))
                .andExpect(jsonPath("$[1].idPlayer", Matchers.is(898)));
    }

    @Test
    void getPlayerById() throws Exception {
        Player karel = new Player (158 ,"karel", "karel", 15);

        Mockito.when(playerService.findById(158)).thenReturn(Optional.of(karel));
        mockMvc.perform(get("/players/158"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPlayer", Matchers.is(158)));


        mockMvc.perform(get("/players/1000000"))
                .andExpect(status().isNotFound());

    }

    @Test
    void postPlayer() throws Exception {
        mockMvc.perform(post("/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name" : "karel",
                          "surname" : "karlovic",
                          "rating" : 1
                        }""")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("karel")))
                .andExpect(jsonPath("$.surname", Matchers.is("karlovic")))
                .andExpect(jsonPath("$.rating", Matchers.is(1)));

    }
}
