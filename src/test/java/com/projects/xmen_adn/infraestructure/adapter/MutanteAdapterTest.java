package com.projects.xmen_adn.infraestructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projects.xmen_adn.domain.logic.DetectarMutanteLogic;
import com.projects.xmen_adn.domain.model.MutanteModel;
import com.projects.xmen_adn.infrastructure.adapter.MutanteAdapter;
import com.projects.xmen_adn.infrastructure.adapter.entityes.MutanteEntity;
import com.projects.xmen_adn.infrastructure.adapter.exception.ApiException;
import com.projects.xmen_adn.infrastructure.adapter.mapper.MutanteMapper;
import com.projects.xmen_adn.infrastructure.adapter.repository.MutanteRepository;

public class MutanteAdapterTest {

    @Mock
    private MutanteRepository mutanteRepository;

    @Mock
    private DetectarMutanteLogic detectarMutanteLogic;

    @Mock
    private MutanteMapper mutanteMapper;

    @InjectMocks
    private MutanteAdapter mutanteAdapter;

    MutanteModel mutanteModel;
    MutanteEntity mutanteEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        mutanteModel = MutanteModel.builder()
                .mutanteId("123")
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .isMutante(true)
                .build();

        mutanteEntity = MutanteEntity.builder()
                .mutanteId("123")
                .nombre("David")
                .apellido("Alfonso")
                .adn(dna)
                .isMutante(true)
                .build();
    }

    @Test
    void testSave_Success() {
        // > Given

        given(mutanteMapper.modelTOentity(mutanteModel)).willReturn(mutanteEntity);
        given(detectarMutanteLogic.isMutante(mutanteEntity.getAdn())).willReturn(true);
        given(mutanteRepository.save(mutanteEntity)).willReturn(mutanteEntity);
        given(mutanteMapper.entityTOmodel(mutanteEntity)).willReturn(mutanteModel);

        // > When
        MutanteModel result = mutanteAdapter.save(mutanteModel);

        // > Then
        assertThat(result).isEqualTo(mutanteModel);
        verify(mutanteRepository, times(1)).save(mutanteEntity);
    }

    @Test
    void testUpdate_Success() {
        // > Given
        String id = mutanteEntity.getMutanteId();

        given(mutanteMapper.modelTOentity(mutanteModel)).willReturn(mutanteEntity);
        given(mutanteRepository.findById(id)).willReturn(Optional.of(mutanteEntity));
        given(detectarMutanteLogic.isMutante(mutanteEntity.getAdn())).willReturn(true);
        given(mutanteRepository.save(mutanteEntity)).willReturn(mutanteEntity);
        given(mutanteMapper.entityTOmodel(mutanteEntity)).willReturn(mutanteModel);

        // > When
        MutanteModel result = mutanteAdapter.update(id, mutanteModel);

        // > Then
        assertThat(result).isEqualTo(mutanteModel);
        verify(mutanteRepository, times(1)).findById(id);
        verify(mutanteRepository, times(1)).save(mutanteEntity);
    }

    @Test
    void testDelete_Success() {
        // > Given
        String id = mutanteEntity.getMutanteId();

        given(mutanteRepository.findById(id)).willReturn(Optional.of(mutanteEntity));

        // > When
        mutanteAdapter.delete(id);

        // > Then
        verify(mutanteRepository, times(1)).findById(id);
        verify(mutanteRepository, times(1)).delete(mutanteEntity);
    }

    @Test
    void testDelete_NotFound() {
        // > Given
        String id = mutanteEntity.getMutanteId();

        given(mutanteRepository.findById(id)).willReturn(Optional.empty());

        // > When
        mutanteAdapter.delete(id);

        // > Then
        verify(mutanteRepository, times(1)).findById(id);
        verify(mutanteRepository, never()).delete(any());
    }

    @Test
    void testList_Success() {
        // > Given
        MutanteEntity mutanteEntity = MutanteEntity.builder().build();
        MutanteModel mutanteModel = MutanteModel.builder().build();
        List<MutanteEntity> mutanteEntities = List.of(mutanteEntity);

        given(mutanteRepository.findAll()).willReturn(mutanteEntities);
        given(mutanteMapper.entityTOmodel(mutanteEntity)).willReturn(mutanteModel);

        // > When
        List<MutanteModel> result = mutanteAdapter.list();

        // > Then
        assertThat(result).containsExactly(mutanteModel);
        verify(mutanteRepository, times(1)).findAll();
    }

    @Test
    void testGetByName_Success() {
        // > Given
        String nombre = mutanteEntity.getNombre();
        MutanteEntity mutanteEntity = MutanteEntity.builder().nombre(nombre).build();
        MutanteModel mutanteModel = MutanteModel.builder().nombre(nombre).build();

        given(mutanteRepository.findByNombre(nombre)).willReturn(Optional.of(mutanteEntity));
        given(mutanteMapper.entityTOmodel(mutanteEntity)).willReturn(mutanteModel);

        // > When
        Optional<MutanteModel> result = mutanteAdapter.getByName(nombre);

        // > Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(mutanteModel);
    }

    @Test
    void testGetByName_NotFound() {
        // > Given
        String nombre = mutanteEntity.getNombre();

        given(mutanteRepository.findByNombre(nombre)).willReturn(Optional.empty());

        // > When
        ApiException thrown = catchThrowableOfType(
                () -> mutanteAdapter.getByName(nombre),
                ApiException.class);

        // > Then
        assertThat(thrown).isNull();
    }

    @Test
    void testGetById_Success() {
        // > Given
        String id = mutanteEntity.getMutanteId();

        given(mutanteRepository.findById(id)).willReturn(Optional.of(mutanteEntity));
        given(mutanteMapper.entityTOmodel(mutanteEntity)).willReturn(mutanteModel);

        // > When
        Optional<MutanteModel> result = mutanteAdapter.getById(id);

        // > Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(mutanteModel);
    }

    @Test
    void testGetById_NotFound() {
        // > Given
        String id = "5555";

        given(mutanteRepository.findById(id)).willReturn(Optional.empty());

        // > When
        ApiException thrown = catchThrowableOfType(
                () -> mutanteAdapter.getById(id),
                ApiException.class);

        // > Then
        assertThat(thrown).isNull();
    }
}