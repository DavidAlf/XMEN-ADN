package com.projects.xmen_adn.infraestructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.domain.model.EstadisticaModel;
import com.projects.xmen_adn.infrastructure.adapter.EstadisticaAdapter;
import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;
import com.projects.xmen_adn.infrastructure.adapter.repository.MutanteRepository;

public class EstadisticaAdapterTest {

    @Mock
    private MutanteRepository mutanteRepository;

    @InjectMocks
    private EstadisticaAdapter estadisticaAdapter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEstadistica_Success() {
        // > Given
        MutanteEntity mutante1 = MutanteEntity.builder().isMutante(true).build();
        MutanteEntity mutante2 = MutanteEntity.builder().isMutante(false).build();
        List<MutanteEntity> mutantes = List.of(mutante1, mutante2);

        given(mutanteRepository.findAll()).willReturn(mutantes);

        // > When
        Optional<EstadisticaModel> result = estadisticaAdapter.getEstadistica();

        // > Then
        assertThat(result).isPresent();
        EstadisticaModel estadistica = result.get();
        assertThat(estadistica.getCount_human_dna()).isEqualTo("1");
        assertThat(estadistica.getCount_mutant_dna()).isEqualTo("1");
        assertThat(estadistica.getRatio()).isEqualTo("1.0");
    }

    @Test
    void testGetEstadistica_NoEntities() {
        // > Given
        given(mutanteRepository.findAll()).willReturn(List.of());

        // > When
        Optional<EstadisticaModel> result = estadisticaAdapter.getEstadistica();

        // > Then
        assertThat(result).isPresent();
        EstadisticaModel estadistica = result.get();
        assertThat(estadistica.getCount_human_dna()).isEqualTo("0");
        assertThat(estadistica.getCount_mutant_dna()).isEqualTo("0");
        assertThat(estadistica.getRatio()).isEqualTo("0.0");
    }
}