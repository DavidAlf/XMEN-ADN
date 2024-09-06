package com.projects.xmen_adn.domain.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.infrastructure.adapter.exception.ApiException;

public class DetectarMutanteLogicTest {

    @InjectMocks
    private DetectarMutanteLogic detectarMutanteLogic;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsMutante_Success() {
        // Given
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        // When
        boolean result = detectarMutanteLogic.isMutante(dna);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testIsMutante_NoMutante() {
        // Given
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCGCTA",
                "TCACTG"
        };

        // When
        boolean result = detectarMutanteLogic.isMutante(dna);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testIsMutante_EmptyArray() {
        // Given
        String[] dna = {};

        // When
        ApiException thrown = catchThrowableOfType(
                () -> detectarMutanteLogic.isMutante(dna),
                ApiException.class);

        // Then
        assertThat(thrown).isNull();
    }

    @Test
    void testIsMutante_NullArray() {
        // Given
        String[] dna = null;

        // When
        ApiException thrown = catchThrowableOfType(
                () -> detectarMutanteLogic.isMutante(dna),
                ApiException.class);

        // Then
        assertThat(thrown).isNull();
    }

    @Test
    void testCountSequences_ConsecutiveCharacters() {
        // Given
        String sequence = "AAAA";

        // When
        int count = detectarMutanteLogic.countSequences(sequence);

        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void testCountSequences_NoConsecutiveCharacters() {
        // Given
        String sequence = "AGCT";

        // When
        int count = detectarMutanteLogic.countSequences(sequence);

        // Then
        assertThat(count).isEqualTo(0);
    }

    @Test
    void testGetColumn() {
        // Given
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        // When
        String column = detectarMutanteLogic.getColumn(dna, 0);

        // Then
        assertThat(column).isEqualTo("ACTACT");
    }

    @Test
    void testCountDiagonalSequences() {
        // Given
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        // When
        int count = detectarMutanteLogic.countDiagonalSequences(dna, dna.length);

        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void testReverseArray() {
        // Given
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        // When
        String[] reversed = detectarMutanteLogic.reverseArray(dna);

        // Then
        assertThat(reversed[0]).isEqualTo("AGCGTA");
        assertThat(reversed[1]).isEqualTo("CGTGAC");
        assertThat(reversed[2]).isEqualTo("TGTATT");
    }
}
