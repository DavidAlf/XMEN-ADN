package com.projects.xmen_adn.infraestructure.adapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projects.xmen_adn.infrastructure.adapter.mapper.AdnMapper;

public class AdnMapperTest {

    private AdnMapper adnMapper;

    @BeforeEach
    void setUp() {
        adnMapper = new AdnMapper();
    }

    @Test
    void convertTest() {
        // >Given
        String[] array = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        // >When
        String result = adnMapper.convert(array);

        // >Then
        assertThat(result).isEqualTo("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG");
    }

    @Test
    void unconvertTest() {
        // >Given
        String csv = "ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG";

        // >When
        String[] result = adnMapper.unconvert(csv);

        // >Then
        assertThat(result).hasSize(6);
        assertThat(result).containsExactly("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
    }
}
