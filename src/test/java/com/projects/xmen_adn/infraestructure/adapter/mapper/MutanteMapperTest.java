package com.projects.xmen_adn.infraestructure.adapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;
import com.projects.xmen_adn.infrastructure.adapter.mapper.MutanteMapper;

public class MutanteMapperTest {

    @InjectMocks
    private MutanteMapper mutanteMapper;

    private MutanteModel mutanteModel;

    private MutanteEntity mutanteEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        mutanteModel = MutanteModel.builder()
                .mutanteId("1")
                .nombre("Logan")
                .apellido("Howlett")
                .adn(dna)
                .isMutante(true)
                .build();

        mutanteEntity = MutanteEntity.builder()
                .mutanteId("1")
                .nombre("Logan")
                .apellido("Howlett")
                .adn(dna)
                .isMutante(true)
                .build();
    }

    @Test
    void modelTOentityTest() {
        // >Given

        // >When
        MutanteEntity result = mutanteMapper.modelTOentity(mutanteModel);

        // >Then
        assertThat(result).isNotNull();
        assertThat(result.getMutanteId()).isEqualTo(mutanteModel.getMutanteId());
        assertThat(result.getNombre()).isEqualTo(mutanteModel.getNombre());
        assertThat(result.getApellido()).isEqualTo(mutanteModel.getApellido());
        assertThat(result.getAdn()).containsExactly(mutanteModel.getAdn());
        assertThat(result.isMutante()).isEqualTo(mutanteModel.isMutante());
    }

    @Test
    void entityTOmodelTest() {
        // >Given

        // >When
        MutanteModel result = mutanteMapper.entityTOmodel(mutanteEntity);

        // >Then
        assertThat(result).isNotNull();
        assertThat(result.getMutanteId()).isEqualTo(mutanteEntity.getMutanteId());
        assertThat(result.getNombre()).isEqualTo(mutanteEntity.getNombre());
        assertThat(result.getApellido()).isEqualTo(mutanteEntity.getApellido());
        assertThat(result.getAdn()).containsExactly(mutanteEntity.getAdn());
        assertThat(result.isMutante()).isEqualTo(mutanteEntity.isMutante());
    }
}