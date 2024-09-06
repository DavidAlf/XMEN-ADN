package com.projects.xmen_adn.aplication.mapper.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.domain.model.dto.MutanteDTO;

public class MutanteDTOMaperTest {

    @InjectMocks
    private MutanteDTOMapper mutanteDTOMaper;

    private MutanteModel mutanteModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        mutanteModel = MutanteModel.builder()
                .mutanteId("123")
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .isMutante(true)
                .build();
    }

    @Test
    void modelTOdtoTest() {
        // > When
        MutanteDTO dto = mutanteDTOMaper.modelTOdto(mutanteModel);

        // > Then
        assertThat(dto).isNotNull();
        assertThat(dto.getMutanteId()).isEqualTo(mutanteModel.getMutanteId());
        assertThat(dto.getNombre()).isEqualTo(mutanteModel.getNombre());
        assertThat(dto.getApellido()).isEqualTo(mutanteModel.getApellido());
        assertThat(dto.getAdn()).isEqualTo(mutanteModel.getAdn());
    }
}
