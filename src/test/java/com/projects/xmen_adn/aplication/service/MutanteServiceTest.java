package com.projects.xmen_adn.aplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.aplication.mapper.dto.MutanteDTOMapper;
import com.projects.xmen_adn.aplication.mapper.request.MutanteRequestMapper;
import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.domain.model.dto.MutanteDTO;
import com.projects.xmen_adn.domain.model.request.MutanteRequest;
import com.projects.xmen_adn.domain.port.MutantePort;

public class MutanteServiceTest {

    @InjectMocks
    private MutanteService mutanteService;

    @Mock
    private MutantePort mutantePort;

    @Mock
    private MutanteRequestMapper mutanteRequestMapper;

    @Mock
    private MutanteDTOMapper mutanteDTOMapper;

    private MutanteModel mutanteModel;
    private MutanteDTO mutanteDTO;
    private MutanteRequest mutanteRequest;

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

        mutanteDTO = MutanteDTO.builder()
                .mutanteId("123")
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .isMutante(true)
                .build();

        mutanteRequest = MutanteRequest.builder()
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .build();

    }

    @Test
    void testSave() {
        // > Given
        given(mutanteRequestMapper.requestTOmodel(mutanteRequest)).willReturn(mutanteModel);
        given(mutantePort.save(mutanteModel)).willReturn(mutanteModel);
        given(mutanteDTOMapper.modelTOdto(mutanteModel)).willReturn(mutanteDTO);

        // > When
        MutanteDTO savedDTO = mutanteService.save(mutanteRequest);

        // > Then
        assertThat(savedDTO).isEqualTo(mutanteDTO);
        verify(mutanteRequestMapper).requestTOmodel(mutanteRequest);
        verify(mutantePort).save(mutanteModel);
        verify(mutanteDTOMapper).modelTOdto(mutanteModel);
    }

    @Test
    void testUpdate() {
        // > Given
        String id = mutanteDTO.getMutanteId();
        given(mutanteRequestMapper.requestTOmodel(mutanteRequest)).willReturn(mutanteModel);
        given(mutantePort.update(id, mutanteModel)).willReturn(mutanteModel);
        given(mutanteDTOMapper.modelTOdto(mutanteModel)).willReturn(mutanteDTO);

        // > When
        MutanteDTO updateDTO = mutanteService.update(id, mutanteRequest);

        // > Then
        assertThat(updateDTO).isEqualTo(mutanteDTO);
        verify(mutanteRequestMapper).requestTOmodel(mutanteRequest);
        verify(mutantePort).update(id, mutanteModel);
        verify(mutanteDTOMapper).modelTOdto(mutanteModel);
    }

    @Test
    void testDelete() {
        // > Given
        String id = mutanteDTO.getMutanteId();

        // > When
        mutanteService.delete(id);

        // > Then
        verify(mutantePort).delete(id);
    }

    @Test
    void testList() {
        // > Given
        List<MutanteModel> modelList = List.of(mutanteModel);
        List<MutanteDTO> dtoList = List.of(mutanteDTO);
        given(mutantePort.list()).willReturn(modelList);
        given(mutanteDTOMapper.modelTOdto(mutanteModel)).willReturn(mutanteDTO);

        // > When
        List<MutanteDTO> dto = mutanteService.list();

        // > Then
        assertThat(dto).isEqualTo(dtoList);
        verify(mutantePort).list();
        verify(mutanteDTOMapper).modelTOdto(mutanteModel);
    }

    @Test
    void testGetByName() {
        // > Given
        String nombre = mutanteDTO.getNombre();
        given(mutantePort.getByName(nombre)).willReturn(Optional.of(mutanteModel));
        given(mutanteDTOMapper.modelTOdto(mutanteModel)).willReturn(mutanteDTO);

        // > When
        MutanteDTO mutanteDTOByName = mutanteService.getByName(nombre);

        // > Then
        assertThat(mutanteDTOByName).isEqualTo(mutanteDTO);
        verify(mutantePort).getByName(nombre);
        verify(mutanteDTOMapper).modelTOdto(mutanteModel);
    }
}
