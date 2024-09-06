package com.projects.xmen_adn.aplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.aplication.mapper.dto.EstadisticaDTOMapper;
import com.projects.xmen_adn.domain.model.EstadisticaModel;
import com.projects.xmen_adn.domain.model.dto.EstadisticaDTO;
import com.projects.xmen_adn.domain.port.EstadisticaPort;

public class EstadisticaServiceTest {

    @InjectMocks
    private EstadisticaService estadisticaService;

    @Mock
    private EstadisticaPort estadisticaPort;

    @Mock
    private EstadisticaDTOMapper estadisticaDTOMapper;

    private EstadisticaModel estadisticaModel;
    private EstadisticaDTO estadisticaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        estadisticaModel = EstadisticaModel.builder()
                .count_human_dna("1")
                .count_mutant_dna("2")
                .ratio("0.5")
                .build();

        estadisticaDTO = EstadisticaDTO.builder()
                .count_human_dna("1")
                .count_mutant_dna("2")
                .ratio("0.5")
                .build();

    }

    @Test
    void getEstadistica() {
        // > Given
        given(estadisticaPort.getEstadistica()).willReturn(Optional.of(estadisticaModel));
        given(estadisticaDTOMapper.modelTOdto(estadisticaModel)).willReturn(estadisticaDTO);

        // > When
        EstadisticaDTO dto = estadisticaService.getEstadistica();

        // > Then
        assertThat(dto).isEqualTo(estadisticaDTO);
        verify(estadisticaPort).getEstadistica();
        verify(estadisticaDTOMapper).modelTOdto(estadisticaModel);
    }

}
