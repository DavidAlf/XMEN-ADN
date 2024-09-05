package com.projects.xmen_adn.aplication.mapper.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.domain.model.EstadisticaModel;
import com.projects.xmen_adn.domain.model.dto.EstadisticaDTO;

public class EstadisticaDTOMapperTest {

    @InjectMocks
    private EstadisticaDTOMapper estadisticaDTOMapper;

    private EstadisticaModel estadisticaModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        estadisticaModel = EstadisticaModel.builder()
                .count_human_dna("1")
                .count_mutant_dna("2")
                .ratio("0.5")
                .build();
    }

    @Test
    void modelTOdtoTest() {
        // > When
        EstadisticaDTO dto = estadisticaDTOMapper.modelTOdto(estadisticaModel);

        // > Then
        assertThat(dto).isNotNull();
        assertThat(dto.getCount_human_dna()).isEqualTo(estadisticaModel.getCount_human_dna());
        assertThat(dto.getCount_mutant_dna()).isEqualTo(estadisticaModel.getCount_mutant_dna());
        assertThat(dto.getRatio()).isEqualTo(estadisticaModel.getRatio());
    }
}
