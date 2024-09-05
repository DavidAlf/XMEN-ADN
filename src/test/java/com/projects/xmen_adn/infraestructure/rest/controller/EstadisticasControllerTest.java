package com.projects.xmen_adn.infraestructure.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.projects.xmen_adn.aplication.useCases.EstadisticasUseCase;
import com.projects.xmen_adn.domain.model.dto.EstadisticaDTO;
import com.projects.xmen_adn.infrastructure.rest.controller.EstadisticasController;

@WebMvcTest(EstadisticasController.class)
public class EstadisticasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstadisticasUseCase estadisticasUseCase;

    private EstadisticaDTO estadisticaDTO;

    @BeforeEach
    void setup() {

        estadisticaDTO = EstadisticaDTO.builder()
                .count_human_dna("1")
                .count_mutant_dna("2")
                .ratio("0.5")
                .build();
    }

    @Test
    void getEstadisticaTest() throws Exception {
        // >Given
        given(estadisticasUseCase.getEstadistica()).willReturn(estadisticaDTO);

        // >When
        ResultActions response = mockMvc.perform(get("/stats")
                .contentType(MediaType.APPLICATION_JSON));

        // >Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.count_human_dna", is(estadisticaDTO.getCount_human_dna())))
                .andExpect(jsonPath("$.count_mutant_dna", is(estadisticaDTO.getCount_mutant_dna())))
                .andExpect(jsonPath("$.ratio", is(estadisticaDTO.getRatio())));
    }
}
