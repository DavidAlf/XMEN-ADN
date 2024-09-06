package com.projects.xmen_adn.infraestructure.adapter.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;
import com.projects.xmen_adn.infrastructure.adapter.repository.MutanteRepository;

public class MutanteRepositoryTest {

    @Mock
    private MutanteRepository mutanteRepository;

    private MutanteEntity mutanteEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        mutanteEntity = MutanteEntity.builder()
                .mutanteId("123")
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .isMutante(true)
                .build();
    }

    @Test
    void testSave() {
        // Given
        when(mutanteRepository.save(mutanteEntity)).thenReturn(mutanteEntity);

        // When
        var entitySaved = mutanteRepository.save(mutanteEntity);

        // Then
        assertThat(entitySaved).isNotNull();
        assertThat(entitySaved.getMutanteId()).isEqualTo(mutanteEntity.getMutanteId());

        // Verify
        verify(mutanteRepository, times(1)).save(mutanteEntity);
    }

    @Test
    void testFindByNombre() {
        // Given
        when(mutanteRepository.findByNombre("David")).thenReturn(Optional.of(mutanteEntity));

        // When
        var result = mutanteRepository.findByNombre("David");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("David");

        // Verify
        verify(mutanteRepository, times(1)).findByNombre("David");
    }
}
