package com.projects.xmen_adn.infraestructure.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.xmen_adn.aplication.useCases.MutanteUseCase;
import com.projects.xmen_adn.domain.model.dto.MutanteDTO;
import com.projects.xmen_adn.domain.model.request.MutanteRequest;
import com.projects.xmen_adn.infrastructure.rest.controller.MutanteController;

@WebMvcTest(MutanteController.class)
public class MutanteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutanteUseCase mutanteUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private MutanteDTO mutanteDTO;
    private MutanteRequest mutanteRequest;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        mutanteDTO = MutanteDTO.builder()
                .mutanteId("123")
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .isMutante(true)
                .build();

        mutanteRequest = MutanteRequest.builder()
                .nombre("Wolverine")
                .apellido("Alfonso")
                .adn(dna)
                .build();
    }

    @Test
    void saveMutanteTest() throws Exception {
        // > Given
        given(mutanteUseCase.save(mutanteRequest)).willAnswer((invocation) -> {
            return mutanteDTO;
        });

        // > When
        ResultActions response = mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mutanteDTO)));

        // > Then
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateMutanteTest() throws Exception {
        // > Given
        String id = mutanteDTO.getMutanteId();
        given(mutanteUseCase.update(id, mutanteRequest)).willAnswer((invocation) -> {
            return mutanteDTO;
        });

        // > When
        ResultActions response = mockMvc.perform(put("/mutant/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mutanteDTO)));

        // > Then
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteMutanteTest() throws Exception {
        // > Given
        String id = mutanteDTO.getMutanteId();
        willDoNothing().given(mutanteUseCase).delete(id);

        // > When
        ResultActions response = mockMvc.perform(delete("/mutant/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        // > Then
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void listMutantesTest() throws Exception {
        // > Given
        List<MutanteDTO> list = new ArrayList<MutanteDTO>();
        list.add(mutanteDTO);

        given(mutanteUseCase.list()).willReturn(list);

        // > When
        ResultActions response = mockMvc.perform(get("/mutant"));

        // > Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void getMutanteByNameTest() throws Exception {
        // >Given
        String nombre = mutanteRequest.getNombre();

        given(mutanteUseCase.getByName(nombre)).willReturn(mutanteDTO);

        // >When
        ResultActions response = mockMvc.perform(get("/mutant/find")
                .param("nombre", nombre)
                .contentType(MediaType.APPLICATION_JSON));

        // >Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre", is(mutanteDTO.getNombre())))
                .andExpect(jsonPath("$.apellido", is(mutanteDTO.getApellido())))
                .andExpect(jsonPath("$.adn",
                        containsInAnyOrder(mutanteDTO.getAdn())));
    }
}
